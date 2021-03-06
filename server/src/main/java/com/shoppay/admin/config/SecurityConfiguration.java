package com.shoppay.admin.config;

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

import com.shoppay.core.security.AdminUserAuthenticationSuccessHandler;
import com.shoppay.core.security.AuthoritiesConstants;

import io.github.jhipster.config.JHipsterProperties;

@Configuration
@EnableWebSecurity
@Order(1)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private static final String[] UNSECURED_RESOURCE_LIST = new String[] { "/resources/**", "/assets/**", "/css/**",
			"/webjars/**", "/images/**", "/img/**", "/js/**" };


	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	private final UserDetailsService userDetailsService;

	private final JHipsterProperties applicationProperties;

	private final RememberMeServices rememberMeServices;

	private final CorsFilter corsFilter;

	public SecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder,
			UserDetailsService userDetailsService, JHipsterProperties applicationProperties,
			RememberMeServices rememberMeServices, CorsFilter corsFilter) {

		this.authenticationManagerBuilder = authenticationManagerBuilder;
		this.userDetailsService = userDetailsService;
		this.applicationProperties = applicationProperties;
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
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(UNSECURED_RESOURCE_LIST);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().authorizeRequests()
				.antMatchers( "/**").hasAuthority(AuthoritiesConstants.ADMIN).and()
				.formLogin().loginPage( "/login").permitAll()
				.successHandler(new AdminUserAuthenticationSuccessHandler())
				.and().headers().frameOptions().disable()
				.and().exceptionHandling().accessDeniedPage( "/access?error").and()
				.rememberMe().rememberMeServices(rememberMeServices).rememberMeParameter("remember-me")
				.key(applicationProperties.getSecurity().getRememberMe().getKey()).and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher( "/logout"))
				.logoutSuccessUrl( "/?logout").and().sessionManagement().maximumSessions(1)
				.expiredUrl( "/login?expired");
	}

	@Bean
	public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
		return new SecurityEvaluationContextExtension();
	}
}
