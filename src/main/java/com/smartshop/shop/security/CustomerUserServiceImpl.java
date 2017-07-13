package com.smartshop.shop.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smartshop.core.customer.Customer;
import com.smartshop.security.AuthoritiesConstants;
import com.smartshop.service.CustomerService;

public class CustomerUserServiceImpl {

	private final Logger LOGGER = LoggerFactory.getLogger(CustomerUserServiceImpl.class);

	@Inject
	private CustomerService customerService;

	public UserDetails loadUserByUsername(String customerName) throws UsernameNotFoundException, DataAccessException {
		System.out.println("cusotmer user service ...");

		Customer user = null;
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		user = customerService.findCustomerByName(customerName);
		GrantedAuthority role = new SimpleGrantedAuthority(AuthoritiesConstants.CUSTOMER);
		authorities.add(role);
		User authUser = new User(customerName, user.getPassword(), true, true, true, true, authorities);
		return authUser;

	}

}
