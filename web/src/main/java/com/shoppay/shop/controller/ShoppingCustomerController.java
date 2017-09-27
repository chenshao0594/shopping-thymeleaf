package com.shoppay.shop.controller;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.shoppay.common.service.MailService;
import com.shoppay.common.service.MerchantStoreService;
import com.shoppay.common.service.MessageService;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.customer.service.CustomerService;
import com.shoppay.core.facade.CustomerFacade;
import com.shoppay.core.order.SalesOrder;
import com.shoppay.core.order.service.SalesOrderService;
import com.shoppay.shop.utils.CustomerInfoContextHolder;
import com.shoppay.web.constants.ShoppingControllerConstants;

@Controller("ShopCustomerController")
@RequestMapping("/customer")
public class ShoppingCustomerController extends AbstractShoppingController {
	private final Logger LOGGER = LoggerFactory.getLogger(ShoppingCustomerController.class);

	@Inject
	private CustomerService customerService;

	@Inject
	private CustomerFacade customerFacade;

	@Inject
	private MerchantStoreService merchantStoreService;

	@Inject
	private MessageService messageService;

	@Inject
	private MailService emailService;

	@Inject
	private SalesOrderService orderService;

	
	@GetMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}

	@GetMapping(value = "/orders")
	public String orders(Model model) {
		Customer customer = CustomerInfoContextHolder.getCustomer();
		List<SalesOrder> orders = this.orderService.findByCustomer(customer);
		model.addAttribute("orders", orders);
		return ShoppingControllerConstants.Customer.customerOrders;
	}

}
