package com.smart.shopping.core.common;

import static org.dozer.loader.api.TypeMappingOptions.mapEmptyString;
import static org.dozer.loader.api.TypeMappingOptions.mapNull;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;

import com.smart.shopping.domain.common.BusinessDomain;

public class ApplicationMapper {

	public Mapper getMapper() {
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.addMapping(beanMappingBuilder());
		return mapper;
	}

	private BeanMappingBuilder beanMappingBuilder() {
		return new BeanMappingBuilder() {
			@Override
			protected void configure() {
				mapping(BusinessDomain.class, BusinessDomain.class, mapNull(false), mapEmptyString(false));
			}
		};
	}

}
