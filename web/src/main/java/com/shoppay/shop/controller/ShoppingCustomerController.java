package com.shoppay.shop.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.service.MailService;
import com.shoppay.common.service.MerchantStoreService;
import com.shoppay.common.service.MessageService;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.customer.service.CustomerService;
import com.shoppay.core.facade.CustomerFacade;
import com.shoppay.core.order.SalesOrder;
import com.shoppay.core.order.enumeration.SalesOrderStatus;
import com.shoppay.core.order.service.SalesOrderService;
import com.shoppay.core.utils.CustomerInfoContextHolder;
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
	public String orders(Model model, Pageable pageable) {
		Customer customer = CustomerInfoContextHolder.getCustomer();
		MerchantStore store = CustomerInfoContextHolder.getMerchantStore();
		Page<SalesOrder> page = this.orderService.findByCustomerAndStore(customer, store,pageable);
		model.addAttribute("page", page);
		return ShoppingControllerConstants.Order.list;
	}
	
	@GetMapping(value = "/orders/{orderId}/delete")
	public String deleteOrder(@PathVariable("orderId") long orderId, Model model, Pageable pageable) {
		Customer customer = CustomerInfoContextHolder.getCustomer();
		MerchantStore store = CustomerInfoContextHolder.getMerchantStore();
		SalesOrder order = this.orderService.findOne(orderId);
		order.setStatus(SalesOrderStatus.DELETED);
		this.orderService.update(order);
		Page<SalesOrder> page = this.orderService.findByCustomerAndStore(customer, store,pageable);
		model.addAttribute("page", page);
		return ShoppingControllerConstants.Order.list;
	}
	@GetMapping(value = "/orders/{orderId}/detail")
	public String getOrderDetail(@PathVariable("orderId") long orderId, Model model, Pageable pageable) {
		SalesOrder order = this.orderService.findOne(orderId);
		model.addAttribute("order", order);
		return ShoppingControllerConstants.Order.detail;
	}

}
