package com.smartshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartshop.app.repository.CountryRepository;
import com.smartshop.core.catalog.service.impl.ProductOptionServiceImpl;
import com.smartshop.core.common.Country;
import com.smartshop.service.CountryService;

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
