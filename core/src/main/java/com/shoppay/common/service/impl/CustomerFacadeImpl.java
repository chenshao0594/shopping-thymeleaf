package com.shoppay.common.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shoppay.common.constants.AppConstants;
import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.reference.Address;
import com.shoppay.common.repository.AuthorityRepository;
import com.shoppay.common.service.MailService;
import com.shoppay.common.service.UserService;
import com.shoppay.common.user.Authority;
import com.shoppay.common.user.User;
import com.shoppay.core.cart.Cart;
import com.shoppay.core.cart.service.CartService;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.customer.model.CustomerRO;
import com.shoppay.core.customer.service.CustomerService;
import com.shoppay.core.facade.CustomerFacade;
import com.shoppay.core.security.AuthoritiesConstants;
import com.shoppay.shop.model.customer.CustomerModel;

@Service("customerFacade")
public class CustomerFacadeImpl implements CustomerFacade {

	private final Logger LOGGER = LoggerFactory.getLogger(CustomerFacadeImpl.class);

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
	private CartService shoppingCartService;

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

	@Override
	public Cart mergeCart(final Customer customer, final String sessionShoppingCartId, final MerchantStore store)
			throws BusinessException {

		LOGGER.debug("Starting merge cart process");
		if (customer != null) {
			Cart customerCart = shoppingCartService.getShoppingCartByCustomer(customer);
			if (StringUtils.isNotBlank(sessionShoppingCartId)) {
				Cart sessionShoppingCart = shoppingCartService.getByCode(sessionShoppingCartId, store);
				if (sessionShoppingCart != null) {
					if (customerCart == null) {
						if (sessionShoppingCart.getCustomerId() == null) {
							LOGGER.debug("Not able to find any shoppingCart with current customer");
							// give it to the customer
							sessionShoppingCart.setCustomerId(customer.getId());
							shoppingCartService.save(sessionShoppingCart);
							customerCart = shoppingCartService.getById(sessionShoppingCart.getId(), store);
							return customerCart;
							// return
							// populateShoppingCartData(customerCart,store,language);
						} else {
							return null;
						}
					} else {
						if (sessionShoppingCart.getCustomerId() == null) {
							LOGGER.debug("Customer shopping cart as well session cart is available, merging carts");
							customerCart = shoppingCartService.mergeShoppingCarts(customerCart, sessionShoppingCart,
									store);
							customerCart = shoppingCartService.getById(customerCart.getId(), store);
							return customerCart;
							// return
							// populateShoppingCartData(customerCart,store,language);
						} else {
							if (sessionShoppingCart.getCustomerId() == customer.getId()) {
								if (!customerCart.getCode().equals(sessionShoppingCart.getCode())) {
									LOGGER.info("Customer shopping cart as well session cart is available");
									customerCart = shoppingCartService.mergeShoppingCarts(customerCart,
											sessionShoppingCart, store);
									customerCart = shoppingCartService.getById(customerCart.getId(), store);
									return customerCart;
									// return
									// populateShoppingCartData(customerCart,store,language);
								} else {
									return customerCart;
									// return
									// populateShoppingCartData(sessionShoppingCart,store,language);
								}
							} else {
								// the saved cart belongs to another user
								return null;
							}
						}

					}
				}
			} else {
				if (customerCart != null) {
					// return
					// populateShoppingCartData(customerCart,store,language);
					return customerCart;
				}
				return null;

			}
		}
		LOGGER.info("Seems some issue with system, unable to find any customer after successful authentication");
		return null;

	}
}
