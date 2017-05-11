package com.smart.shopping.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.shopping.config.AppConstants;

@Controller
@RequestMapping("/" + AppConstants.ADMIN_PREFIX + "/" + PaymentController.SECTION_KEY)
public class PaymentController {
	private final Logger log = LoggerFactory.getLogger(PaymentController.class);
	public static final String SECTION_KEY = "payments";

}