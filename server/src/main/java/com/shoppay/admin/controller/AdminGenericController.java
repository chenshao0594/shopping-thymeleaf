package com.shoppay.admin.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shoppay.common.constants.ApplicationConstants;

@Controller
@ControllerAdvice
@RequestMapping(ApplicationConstants.ADMIN_PREFIX)
public class AdminGenericController {
	@GetMapping("/login")
	public String index() {
		return "login";
	}

	@GetMapping({ "", "/home" })
	public String home() {
		return "welcome";
	}

	@GetMapping("/router")
	public String accessDeniedRouter(@RequestParam("q") String resource) {
		return "redirect:/" + resource;
	}

	@GetMapping("/unauthorized")
	public ModelAndView accessDenied() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("timestamp", new Date());
		mav.setViewName("unauthorized");
		return mav;
	}

	@GetMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:" + ApplicationConstants.ADMIN_PREFIX + "?logout";
	}

}
