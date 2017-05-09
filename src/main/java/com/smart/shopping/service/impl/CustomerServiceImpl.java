package com.smart.shopping.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.shopping.domain.Customer;
import com.smart.shopping.repository.CustomerRepository;
import com.smart.shopping.repository.search.CustomerSearchRepository;
import com.smart.shopping.service.CustomerService;

/**
 * Service Implementation for managing Customer.
 */
@Service
@Transactional
public class CustomerServiceImpl extends AbstractDomainServiceImpl<Customer, Long> implements CustomerService {

	private final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	private final CustomerRepository customerRepository;
	private final CustomerSearchRepository customerSearchRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository,
			CustomerSearchRepository customerSearchRepository) {
		super(customerRepository, customerSearchRepository);
		this.customerRepository = customerRepository;
		this.customerSearchRepository = customerSearchRepository;
	}

}
