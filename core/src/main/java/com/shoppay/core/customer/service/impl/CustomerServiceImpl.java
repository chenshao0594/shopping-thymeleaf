package com.shoppay.core.customer.service.impl;

import java.time.ZonedDateTime;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.reference.Address;
import com.shoppay.common.repository.CustomerRepository;
import com.shoppay.common.service.impl.AbstractDomainServiceImpl;
import com.shoppay.common.user.User;
import com.shoppay.common.utils.GeoLocationUtils;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.customer.service.CustomerService;
import com.shoppay.core.security.SecurityUtils;
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

	@Inject
	private  PasswordEncoder passwordEncoder;

	public CustomerServiceImpl(CustomerRepository customerRepository,
			CustomerSearchRepository customerSearchRepository) {
		super(customerRepository, customerSearchRepository);
		this.customerRepository = customerRepository;
		this.customerSearchRepository = customerSearchRepository;
	}

	@Override
	public Customer findByEmailAddress(String emailAddress) {
		return this.customerRepository.findByEmailAddress(emailAddress);
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
	public Customer findByName(String name) {
		return this.customerRepository.findByName(name);
	}

	@Override
	public void changePassword( Customer customer,String password) {

		String encryptedPassword = passwordEncoder.encode(password);
		customer.setPassword(encryptedPassword);
		this.customerRepository.saveAndFlush(customer);

	}

	@Override
	public Customer completePasswordReset(String newPassword, String resetKey) {
		Customer customer = this.customerRepository.findByResetKey(resetKey);
		ZonedDateTime oneDayAgo = ZonedDateTime.now().minusHours(24);
		if( customer.getResetDate().isAfter(oneDayAgo)) {
			customer.setPassword(passwordEncoder.encode(newPassword));
			customer.setResetKey(null);
			customer.setResetDate(null);
		}
		return customer;
	}

}
