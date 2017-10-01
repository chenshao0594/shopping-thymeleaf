package com.shoppay;


import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.MetricFilterAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import com.shoppay.common.config.ApplicationProperties;
import com.shoppay.common.constants.ApplicationConstants;
import com.shoppay.config.DefaultProfileUtil;
import com.shoppay.core.payment.gateway.paypal.PaypalConfiguration;

import io.github.jhipster.config.JHipsterConstants;

@SpringBootApplication
@ComponentScan(basePackages = { ApplicationConstants.BASE_PACKAGE })
@Import(PaypalConfiguration.class)
@EnableAutoConfiguration(exclude = { MetricFilterAutoConfiguration.class, MetricRepositoryAutoConfiguration.class })
@EnableConfigurationProperties({ ApplicationProperties.class })
public class EshopApplication {

	private static final Logger log = LoggerFactory.getLogger(EshopApplication.class);

	private final Environment env;

	public EshopApplication(Environment env) {
		this.env = env;
	}

	@PostConstruct
	public void initApplication() {
		Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
		if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
				&& activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_PRODUCTION)) {
			log.error("You have misconfigured your application! It should not run "
					+ "with both the 'dev' and 'prod' profiles at the same time.");
		}
		if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
				&& activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_CLOUD)) {
			log.error("You have misconfigured your application! It should not"
					+ "run with both the 'dev' and 'cloud' profiles at the same time.");
		}
	}

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(EshopApplication.class);
		DefaultProfileUtil.addDefaultProfile(app);
		Environment env = app.run(args).getEnvironment();
		String protocol = "http";
		if (env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}
	}

}
