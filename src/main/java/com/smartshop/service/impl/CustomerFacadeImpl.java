package com.smartshop.service.impl;

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

import com.smartshop.constants.AppConstants;
import com.smartshop.core.common.Address;
import com.smartshop.customer.CustomerRO;
import com.smartshop.domain.Authority;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.domain.User;
import com.smartshop.exception.BusinessException;
import com.smartshop.repository.AuthorityRepository;
import com.smartshop.security.AuthoritiesConstants;
import com.smartshop.service.CustomerFacade;
import com.smartshop.service.CustomerService;
import com.smartshop.service.MailService;
import com.smartshop.service.UserService;
import com.smartshop.shop.model.customer.CustomerModel;

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
	private UserService userService;

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
		return this.customerService.findCustomerByName(userName);
	}

	@Override
	public Customer getCustomerByEmailAddress(final String emailAddress, final MerchantStore store)
			throws BusinessException {
		return this.customerService.findCustomerByEmailAddress(emailAddress);
	}

	@Override
	public boolean checkIfUserExists(String email, MerchantStore store) throws BusinessException {
		Customer customer = this.customerService.findCustomerByEmailAddress(email);
		return customer != null;
	}

	@Override
	public CustomerRO registerCustomer(CustomerRO customerInfo, MerchantStore merchantStore) throws BusinessException {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerInfo, customer);
		String encryptedPassword = passwordEncoder.encode(customerInfo.getPassword());
		customer.setPassword(encryptedPassword);
		customer.getBilling().setFirstName(customer.getFirstName());
		customer.getBilling().setLastName(customer.getLastName());
		customer.anonymous(false);
		customer.setName(customerInfo.getEmailAddress());
		Authority authority = authorityRepository.findOne(AuthoritiesConstants.CUSTOMER);
		customer.setAuthority(authority);
		User customerUser = userService.createCustomerUser(customer.getName(), customerInfo.getPassword(),
				customerInfo.getFirstName(), customerInfo.getLastName(), customerInfo.getEmailAddress().toLowerCase(),
				customerInfo.getImage(), AppConstants.LANG);
		customer.setUserId(customerUser.getId());
		this.customerService.save(customer);
		return customerInfo;
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
