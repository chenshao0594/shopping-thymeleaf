package com.shoppay.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppay.app.repository.CountryRepository;
import com.shoppay.common.service.impl.AbstractDomainServiceImpl;
import com.shoppay.core.catalog.service.impl.ProductOptionServiceImpl;
import com.shoppay.core.common.Country;
import com.shoppay.service.CountryService;

@Service
@Transactional
public class CountryServiceImpl extends AbstractDomainServiceImpl<Country, Long> implements CountryService {
	private final Logger LOGGER = LoggerFactory.getLogger(ProductOptionServiceImpl.class);

	private CountryRepository countryRepository;

	public CountryServiceImpl(CountryRepository repository) {
		super(repository, null);
		this.countryRepository = repository;
	}

}
