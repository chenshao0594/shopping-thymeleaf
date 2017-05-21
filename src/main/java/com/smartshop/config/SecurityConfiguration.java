package com.smartshop.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CorsFilter;

import com.smartshop.constants.AppConstants;
import com.smartshop.security.AuthoritiesConstants;

import io.github.jhipster.config.JHipsterProperties;
import io.github.jhipster.security.AjaxAuthenticationFailureHandler;
import io.github.jhipster.security.AjaxAuthenticationSuccessHandler;
import io.github.jhipster.security.AjaxLogoutSuccessHandler;
import io.github.jhipster.security.Http401UnauthorizedEntryPoint;

@Configuration
@EnableWebSecurity
@Order(1)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	private final UserDetailsService userDetailsService;

	private final JHipsterProperties jHipsterProperties;

	private final RememberMeServices rememberMeServices;

	private final CorsFilter corsFilter;

	public SecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder,
			UserDetailsService userDetailsService, JHipsterProperties jHipsterProperties,
			RememberMeServices rememberMeServices, CorsFilter corsFilter) {

		this.authenticationManagerBuilder = authenticationManagerBuilder;
		this.userDetailsService = userDetailsService;
		this.jHipsterProperties = jHipsterProperties;
		this.rememberMeServices = rememberMeServices;
		this.corsFilter = corsFilter;
	}

	@PostConstruct
	public void init() {
		try {
			authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		} catch (Exception e) {
			throw new BeanInitializationException("Security configuration failed", e);
		}
	}

	@Bean
	public AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler() {
		return new AjaxAuthenticationSuccessHandler();
	}

	@Bean
	public AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler() {
		return new AjaxAuthenticationFailureHandler();
	}

	@Bean
	public AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler() {
		return new AjaxLogoutSuccessHandler();
	}

	@Bean
	public Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint() {
		return new Http401UnauthorizedEntryPoint();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/app/**/*.{js,html}").antMatchers("/i18n/**")
				.antMatchers("/content/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().authorizeRequests()
				.antMatchers(AppConstants.ADMIN_PREFIX + "/**").hasAuthority(AuthoritiesConstants.ADMIN).and()
				.formLogin().loginPage("/login").permitAll().and().logout().logoutUrl("/logout").permitAll().and()
				.headers().frameOptions().disable().and().exceptionHandling().accessDeniedPage("/access?error").and()
				.rememberMe().rememberMeServices(rememberMeServices).rememberMeParameter("remember-me")
				.key(jHipsterProperties.getSecurity().getRememberMe().getKey()).and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/?logout").and()
				.sessionManagement().maximumSessions(1).expiredUrl("/login?expired");
		// .antMatchers("/authenticate").permitAll()
		// .antMatchers("/account/reset_password/init").permitAll()
		// .antMatchers("/account/reset_password/finish").permitAll()
		// .antMatchers("/profile-info").permitAll()
		// .antMatchers("/api/**").authenticated()
		// .antMatchers("/websocket/tracker").hasAuthority(AuthoritiesConstants.ADMIN)
		// .antMatchers("/websocket/**").permitAll()
		// .antMatchers("/management/health").permitAll()
		// .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN);

	}

	@Bean
	public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
		return new SecurityEvaluationContextExtension();
	}
}
