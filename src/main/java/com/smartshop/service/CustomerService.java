package com.smartshop.service;

import com.smartshop.core.common.Address;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;

/**
 * Service Interface for managing Customer.
 */
public interface CustomerService extends AbstractDomainService<Customer, Long> {

	Customer findCustomerByEmailAddress(String emailAddress);

	Address getCustomerAddress(MerchantStore store, String ipAddress) throws BusinessException;
}