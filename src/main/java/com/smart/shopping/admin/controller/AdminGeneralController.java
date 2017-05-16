package com.smart.shopping.admin.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.smart.shopping.config.AppConstants;

@Controller
@ControllerAdvice
public class AdminGeneralController {
	@GetMapping("/" + AppConstants.ADMIN_PREFIX)
	public String index() {
		return "welcome";
	}

	@GetMapping(AppConstants.ADMIN_PREFIX + "/router")
	public String accessDeniedRouter(@RequestParam("q") String resource) {
		return "redirect:/" + resource;
	}

	@GetMapping(AppConstants.ADMIN_PREFIX + "/unauthorized")
	public ModelAndView accessDenied() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("timestamp", new Date());
		mav.setViewName("unauthorized");
		return mav;
	}

	@GetMapping(value = "/oups")
	public String triggerException() {
		throw new RuntimeException("Expected: controller used to showcase what happens when an exception is thrown");
	}

}
