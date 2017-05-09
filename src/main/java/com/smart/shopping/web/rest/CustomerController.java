package com.smart.shopping.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart.shopping.admin.controller.AbstractDomainController;
import com.smart.shopping.config.AppConstants;
import com.smart.shopping.domain.Customer;
import com.smart.shopping.service.CustomerService;

/**
 * REST controller for managing Customer.
 */
@RestController
@RequestMapping("/" + AppConstants.ADMIN_PREFIX + "/" + CustomerController.SECTION_KEY)
public class CustomerController extends AbstractDomainController<Customer, Long> {

	private final Logger log = LoggerFactory.getLogger(CustomerController.class);
	public static final String SECTION_KEY = "customers";

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super(customerService);
		this.customerService = customerService;
	}

	@Override
	protected String getSectionKey() {
		return SECTION_KEY;
	}

	@Override
	protected Class getEntityClass() {
		return Customer.class;
	}

}