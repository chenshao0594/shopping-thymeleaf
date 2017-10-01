package com.shoppay.core.customer.service;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.reference.Address;
import com.shoppay.common.service.AbstractDomainService;
import com.shoppay.core.customer.Customer;

/**
 * Service Interface for managing Customer.
 */
public interface CustomerService extends AbstractDomainService<Customer, Long> {

	Customer findByName(String name);

	Customer findByEmailAddress(String emailAddress);

	Address getCustomerAddress(MerchantStore store, String ipAddress) throws BusinessException;
	
	
}