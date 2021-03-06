package com.shoppay.common.service;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements ApplicationContextAware {
	
	private  static MessageService service = new MessageService();

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

	
	public static MessageService getInstance(){
		return service;
	}
	
	
	public String getMessage(String key, Object args[],Locale locale) {
		return applicationContext.getMessage(key, args, locale);
	}

	public String getMessage(String key, Locale locale, String defaultValue) {
		try {
			return applicationContext.getMessage(key, null, locale);
		} catch (Exception ignore) {
		}
		return defaultValue;
	}

	public String getMessage(String key, String[] args, Locale locale) {
		return applicationContext.getMessage(key, args, locale);
	}

}
