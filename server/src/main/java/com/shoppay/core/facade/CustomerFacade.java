package com.shoppay.core.facade;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.reference.Address;
import com.shoppay.core.cart.Cart;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.customer.model.CustomerRO;
import com.shoppay.shop.model.customer.CustomerModel;

public interface CustomerFacade {

	public CustomerModel getCustomerDataByUserName(final String userName, final MerchantStore store)
			throws BusinessException;

	public CustomerModel getCustomerById(final Long id, final MerchantStore merchantStore) throws BusinessException;

	public Customer getCustomerByUserName(final String userName, final MerchantStore store) throws BusinessException;

	public Customer getCustomerByEmailAddress(final String emailAddress, final MerchantStore store)
			throws BusinessException;

	public boolean checkIfUserExists(final String email, final MerchantStore store) throws BusinessException;

	public CustomerRO registerCustomer(final CustomerRO customer, final MerchantStore merchantStore)
			throws BusinessException;

	public Address getAddress(final Long userId, final MerchantStore merchantStore, boolean isBillingAddress);

	public void setCustomerModelDefaultProperties(Customer customer, MerchantStore store) throws BusinessException;

	public void authenticate(Customer customer, String userName, String password) throws BusinessException;

	Customer getCustomerModel(CustomerModel customer, MerchantStore merchantStore) throws BusinessException;

	Customer populateCustomerModel(Customer customerModel, CustomerModel customer, MerchantStore merchantStore)
			throws BusinessException;

	Cart mergeCart(Customer customer, String sessionShoppingCartId, MerchantStore store) throws BusinessException;

}
