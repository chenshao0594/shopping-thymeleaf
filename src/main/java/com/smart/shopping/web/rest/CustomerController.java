package com.smart.shopping.web.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.smart.shopping.admin.controller.AbstractDomainController;
import com.smart.shopping.config.AppConstants;
import com.smart.shopping.customer.CustomerDTO;
import com.smart.shopping.domain.Customer;
import com.smart.shopping.exception.BusinessException;
import com.smart.shopping.service.CustomerService;

/**
 * REST controller for managing Customer.
 */
@Controller
@RequestMapping("/" + AppConstants.ADMIN_PREFIX + "/" + CustomerController.SECTION_KEY)
public class CustomerController extends AbstractDomainController<Customer, Long> {

	private final Logger log = LoggerFactory.getLogger(CustomerController.class);
	public static final String SECTION_KEY = "customers";

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super(customerService);
		this.customerService = customerService;
	}

	@Timed
	@GetMapping("/register")
	public ModelAndView registerInit(@Valid CustomerDTO customerInfo, ModelAndView model) throws BusinessException {
		// model.setViewName(this.getSectionKey() + "/skus");
		return model;
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