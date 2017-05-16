package com.smart.shopping.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smart.shop.customer.CustomerModel;
import com.smart.shopping.core.common.Address;
import com.smart.shopping.domain.Authority;
import com.smart.shopping.domain.Customer;
import com.smart.shopping.domain.MerchantStore;
import com.smart.shopping.exception.BusinessException;
import com.smart.shopping.repository.AuthorityRepository;
import com.smart.shopping.security.AuthoritiesConstants;
import com.smart.shopping.service.CustomerFacade;
import com.smart.shopping.service.CustomerService;
import com.smart.shopping.service.MailService;

@Service("customerFacade")
public class CustomerFacadeImpl implements CustomerFacade {

	@Inject
	private CustomerService customerService;

	@Inject
	private MailService mailService;
	@Inject
	private AuthorityRepository authorityRepository;

	@Inject
	private PasswordEncoder passwordEncoder;

	@Inject
	private AuthenticationManager customerAuthenticationManager;

	@Override
	public CustomerModel getCustomerDataByUserName(String userName, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerModel getCustomerById(Long id, MerchantStore merchantStore) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomerByUserName(String userName, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomerByEmailAddress(final String emailAddress, final MerchantStore store)
			throws BusinessException {
		return this.customerService.findCustomerByEmailAddress(emailAddress);
	}

	@Override
	public boolean checkIfUserExists(String userName, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CustomerModel registerCustomer(CustomerModel customerInfo, MerchantStore merchantStore)
			throws BusinessException {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerInfo, customer);
		String encryptedPassword = passwordEncoder.encode(customerInfo.getPassword());
		customer.setPassword(encryptedPassword);
		customer.getBilling().setFirstName(customer.getFirstName());
		customer.getBilling().setLastName(customer.getLastName());
		customer.anonymous(false);
		Authority authority = authorityRepository.findOne(AuthoritiesConstants.CUSTOMER);
		customer.setAuthority(authority);
		this.customerService.save(customer);
		return null;
	}

	@Override
	public Address getAddress(Long userId, MerchantStore merchantStore, boolean isBillingAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCustomerModelDefaultProperties(Customer customer, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void authenticate(Customer customer, String userName, String password) throws BusinessException {
		Validate.notNull(customer, "Customer cannot be null");
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority role = new SimpleGrantedAuthority(AuthoritiesConstants.CUSTOMER);
		authorities.add(role);
		Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userName, password, authorities);
		Authentication authentication = customerAuthenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	@Override
	public Customer getCustomerModel(CustomerModel customer, MerchantStore merchantStore) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer populateCustomerModel(Customer customerModel, CustomerModel customer, MerchantStore merchantStore)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
