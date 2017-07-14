/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shoppay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.shoppay.common.constants.AppConstants;
import com.shoppay.interceptor.AdminInterceptor;
import com.shoppay.interceptor.ShopInterceptor;

/**
 * Configures View-related items.
 *
 * @author Arnaldo Piccinelli
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/access").setViewName("access");
		registry.addViewController(AppConstants.ADMIN_PREFIX + "/login").setViewName("login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AdminInterceptor()).addPathPatterns(AppConstants.ADMIN_PREFIX + "/**");
		registry.addInterceptor(shopInterceptor()).excludePathPatterns(AppConstants.ADMIN_PREFIX + "/**");
	}

	@Bean
	public ShopInterceptor shopInterceptor() {
		return new ShopInterceptor();
	}

	/*
	 * @Bean public CommonsMultipartResolver multipartResolver() { final
	 * CommonsMultipartResolver commonsMultipartResolver = new
	 * CommonsMultipartResolver();
	 * commonsMultipartResolver.setMaxUploadSize(-1); return
	 * commonsMultipartResolver; }
	 */
}
