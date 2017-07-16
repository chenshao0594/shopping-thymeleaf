package com.shoppay.core.customer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.reference.Address;
import com.shoppay.common.repository.CustomerRepository;
import com.shoppay.common.service.impl.AbstractDomainServiceImpl;
import com.shoppay.common.utils.GeoLocationUtils;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.customer.service.CustomerService;
import com.shoppay.repository.search.CustomerSearchRepository;

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

	@Override
	public Customer findCustomerByEmailAddress(String emailAddress) {
		Customer customer = new Customer();
		customer.setEmailAddress(emailAddress);
		Example example = Example.of(customer);
		return this.customerRepository.findOne(example);
	}

	@Override
	public Address getCustomerAddress(MerchantStore store, String ipAddress) throws BusinessException {

		try {
			return GeoLocationUtils.getAddress(ipAddress);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Customer findCustomerByName(String name) {
		Customer customer = new Customer();
		customer.setName(name);
		Example example = Example.of(customer);
		return this.customerRepository.findOne(example);
	}

}
