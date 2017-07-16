package com.shoppay.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppay.common.reference.Country;
import com.shoppay.common.repository.CountryRepository;
import com.shoppay.common.service.CountryService;
import com.shoppay.core.catalog.service.impl.ProductOptionServiceImpl;

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
