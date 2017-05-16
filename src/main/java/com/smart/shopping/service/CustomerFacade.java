package com.smart.shopping.service;

import com.smart.shop.customer.CustomerModel;
import com.smart.shopping.core.common.Address;
import com.smart.shopping.domain.Customer;
import com.smart.shopping.domain.MerchantStore;

public interface CustomerFacade {

	public CustomerModel getCustomerDataByUserName(final String userName, final MerchantStore store) throws Exception;

	public CustomerModel getCustomerById(final Long id, final MerchantStore merchantStore) throws Exception;

	public Customer getCustomerByUserName(final String userName, final MerchantStore store) throws Exception;

	public boolean checkIfUserExists(final String userName, final MerchantStore store) throws Exception;

	public CustomerModel registerCustomer(final CustomerModel customer, final MerchantStore merchantStore)
			throws Exception;

	public Address getAddress(final Long userId, final MerchantStore merchantStore, boolean isBillingAddress);

	public void setCustomerModelDefaultProperties(Customer customer, MerchantStore store) throws Exception;

	public void authenticate(Customer customer, String userName, String password) throws Exception;

	Customer getCustomerModel(CustomerModel customer, MerchantStore merchantStore) throws Exception;

	Customer populateCustomerModel(Customer customerModel, CustomerModel customer, MerchantStore merchantStore)
			throws Exception;

}
