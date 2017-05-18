package com.smartshop.service;

import com.smartshop.core.common.Address;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;
import com.smartshop.shop.model.customer.CustomerModel;

public interface CustomerFacade {

	public CustomerModel getCustomerDataByUserName(final String userName, final MerchantStore store)
			throws BusinessException;

	public CustomerModel getCustomerById(final Long id, final MerchantStore merchantStore) throws BusinessException;

	public Customer getCustomerByUserName(final String userName, final MerchantStore store) throws BusinessException;

	public Customer getCustomerByEmailAddress(final String emailAddress, final MerchantStore store)
			throws BusinessException;

	public boolean checkIfUserExists(final String userName, final MerchantStore store) throws BusinessException;

	public CustomerModel registerCustomer(final CustomerModel customer, final MerchantStore merchantStore)
			throws BusinessException;

	public Address getAddress(final Long userId, final MerchantStore merchantStore, boolean isBillingAddress);

	public void setCustomerModelDefaultProperties(Customer customer, MerchantStore store) throws BusinessException;

	public void authenticate(Customer customer, String userName, String password) throws BusinessException;

	Customer getCustomerModel(CustomerModel customer, MerchantStore merchantStore) throws BusinessException;

	Customer populateCustomerModel(Customer customerModel, CustomerModel customer, MerchantStore merchantStore)
			throws BusinessException;

}
