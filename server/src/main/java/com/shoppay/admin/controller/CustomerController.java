package com.shoppay.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppay.core.customer.Customer;
import com.shoppay.core.customer.service.CustomerService;

/**
 * REST controller for managing Customer.
 */
@Controller
@RequestMapping( "/customer")
public class CustomerController extends AbstractDomainController<Customer, Long> {

	private final Logger log = LoggerFactory.getLogger(CustomerController.class);

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super(customerService, Customer.class);
		this.customerService = customerService;
	}

}