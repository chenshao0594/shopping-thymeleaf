package com.smart.shopping.config;

import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeleafConfiguration {

	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(ThymeleafConfiguration.class);

	@Bean
	@Description("Thymeleaf template")
	public ClassLoaderTemplateResolver TemplateResolver() {
		ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
		emailTemplateResolver.setPrefix("templates/");
		emailTemplateResolver.setSuffix(".html");
		emailTemplateResolver.setTemplateMode("HTML");
		emailTemplateResolver.setCharacterEncoding(CharEncoding.UTF_8);
		emailTemplateResolver.setCacheable(false);
		emailTemplateResolver.setOrder(1);
		return emailTemplateResolver;
	}

	// @Bean
	// public SpringDataDialect springDataDialect() {
	// return new SpringDataDialect();
	// }

	/*
	 * @Bean public SpringTemplateEngine templateDialogEngine() {
	 * SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	 * templateEngine.getDialects(); templateEngine.addDialect(new
	 * LayoutDialect()); return templateEngine; }
	 */

}