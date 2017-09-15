package com.shoppay.admin.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.shoppay.common.constants.ApplicationConstants;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.customer.model.CustomerRO;
import com.shoppay.core.customer.service.CustomerService;

/**
 * REST controller for managing Customer.
 */
@Controller
@RequestMapping(ApplicationConstants.ADMIN_PREFIX + "/" + CustomerController.SECTION_KEY)
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