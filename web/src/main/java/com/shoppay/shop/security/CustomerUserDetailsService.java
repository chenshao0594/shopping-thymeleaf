package com.shoppay.shop.security;

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
import org.springframework.transaction.annotation.Transactional;

import com.shoppay.common.repository.CustomerRepository;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.security.AuthoritiesConstants;


@Service
public class CustomerUserDetailsService implements UserDetailsService {

	private final Logger LOGGER = LoggerFactory.getLogger(CustomerUserDetailsService.class);

	@Inject
	private CustomerRepository customerRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String customerName) throws UsernameNotFoundException, DataAccessException {
		Customer customer = null;
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		customer = customerRepository.findByName(customerName);
		if(customer==null) {
			  throw new UsernameNotFoundException("user not existed");
		}
		GrantedAuthority role = new SimpleGrantedAuthority(AuthoritiesConstants.CUSTOMER);
		authorities.add(role);
		User authUser = new User(customerName, customer.getPassword(), authorities);
		return authUser;
	}
}
