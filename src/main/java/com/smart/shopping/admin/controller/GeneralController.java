package com.smart.shopping.admin.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ControllerAdvice
public class GeneralController {
	@RequestMapping("/")
	public String index() {
		return "welcome";
	}

	@RequestMapping("/router")
	public String accessDeniedRouter(@RequestParam("q") String resource) {
		// log.debug("In accessDeniedRouter resource = " + resource);

		return "redirect:/" + resource;
	}

	@RequestMapping("/unauthorized")
	public ModelAndView accessDenied() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("timestamp", new Date());
		mav.setViewName("unauthorized");
		return mav;
	}

	@RequestMapping(value = "/oups", method = RequestMethod.GET)
	public String triggerException() {
		throw new RuntimeException("Expected: controller used to showcase what happens when an exception is thrown");
	}

}
