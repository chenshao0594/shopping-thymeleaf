package com.shoppay.shop.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.order.SalesOrder;
import com.shoppay.core.order.service.SalesOrderService;
import com.shoppay.shop.utils.CustomerInfoContextHolder;
import com.shoppay.web.constants.ShoppingControllerConstants;

@RestController("ShoppingOrderRestController")
@RequestMapping("/customer/orders")
public class ShoppingOrderRestController  extends AbstractShoppingController {
	private final Logger LOGGER = LoggerFactory.getLogger(ShoppingOrderRestController.class);
	
	@Inject
	private SalesOrderService orderService;
	
	public ResponseEntity<Page<SalesOrder>> delete(long id) {
		Customer customer = CustomerInfoContextHolder.getCustomer();
		MerchantStore store = CustomerInfoContextHolder.getMerchantStore();
		this.orderService.delete(id);
		Page<SalesOrder> page = this.orderService.findByCustomerAndStore(customer, store,null);
		return ResponseEntity.ok().body(page);
	}

}
