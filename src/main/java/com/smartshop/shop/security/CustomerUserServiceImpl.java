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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smartshop.domain.Customer;
import com.smartshop.security.AuthoritiesConstants;
import com.smartshop.service.CustomerService;

@Service("customerUserDetailsService")
public class CustomerUserServiceImpl implements UserDetailsService {

	private final Logger LOGGER = LoggerFactory.getLogger(CustomerUserServiceImpl.class);

	public final static String ROLE_PREFIX = "ROLE_";// Spring Security 4

	@Inject
	private CustomerService customerService;

	// @Inject
	// protected PermissionService permissionService;
	//
	// @Inject
	// protected GroupService groupService;

	@Override
	public UserDetails loadUserByUsername(String customerName) throws UsernameNotFoundException, DataAccessException {
		Customer user = null;
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		user = customerService.findCustomerByName(customerName);
		GrantedAuthority role = new SimpleGrantedAuthority(AuthoritiesConstants.CUSTOMER);
		authorities.add(role);
		User authUser = new User(customerName, user.getPassword(), true, true, true, true, authorities);
		return authUser;

	}

}
