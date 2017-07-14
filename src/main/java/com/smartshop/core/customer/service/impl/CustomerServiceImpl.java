package com.smartshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartshop.app.repository.CustomerRepository;
import com.smartshop.common.utils.GeoLocationUtils;
import com.smartshop.core.common.Address;
import com.smartshop.core.customer.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.domain.QCustomer;
import com.smartshop.exception.BusinessException;
import com.smartshop.repository.search.CustomerSearchRepository;
import com.smartshop.service.CustomerService;

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
		QCustomer qCustomer = QCustomer.customer;
		Customer result = this.customerRepository.findOne(qCustomer.emailAddress.eq(emailAddress));
		return result;
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
		QCustomer qCustomer = QCustomer.customer;
		Customer result = this.customerRepository.findOne(qCustomer.name.eq(name));
		return result;
	}

}
