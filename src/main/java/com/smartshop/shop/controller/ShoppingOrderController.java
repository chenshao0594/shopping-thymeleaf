package com.smartshop.shop.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.smartshop.core.cart.CartItem;
import com.smartshop.core.cart.service.CartItemService;
import com.smartshop.core.catalog.Product;
import com.smartshop.core.catalog.SKU;
import com.smartshop.core.order.OrderProductLine;
import com.smartshop.core.order.SalesOrder;
import com.smartshop.core.order.enumeration.SalesOrderStatus;
import com.smartshop.core.order.service.SalesOrderService;
import com.smartshop.domain.Customer;
import com.smartshop.exception.BusinessException;
import com.smartshop.service.ProductService;
import com.smartshop.service.SKUService;
import com.smartshop.shop.utils.UserInfoContextHolder;

@Controller("ShoppingOrderController")
@RequestMapping("/order")
public class ShoppingOrderController extends AbstractShoppingController {

	@Inject
	private SalesOrderService salesOrderService;
	@Inject
	private ProductService productService;

	@Inject
	private SKUService skuService;

	@Inject
	private CartItemService cartItemService;

	@Timed
	@PostMapping("/commit")
	public ModelAndView commitOrder(ModelAndView model, List<Long> cartItemIds, final HttpServletRequest request)
			throws BusinessException {
		SalesOrder order = new SalesOrder();
		Customer customer = UserInfoContextHolder.getCustomer();
		order.setCustomerId(customer.getId());
		order.setStatus(SalesOrderStatus.INITED);
		order.setBilling(customer.getBilling());
		order.setDelivery(customer.getDelivery());
		order.setMerchant(UserInfoContextHolder.getMerchantStore());
		Set<OrderProductLine> productItems = new HashSet<OrderProductLine>();
		for (long itemId : cartItemIds) {
			CartItem item = this.cartItemService.findOne(itemId);
			OrderProductLine line = new OrderProductLine();
			Product product = this.productService.findOne(item.getProductId());
			if (product == null) {
				throw new BusinessException(" product is null {}" + cartItemIds);
			}
			SKU sku = this.skuService.findOne(item.getSkuId());
			if (sku == null) {
				throw new BusinessException("sku is null");
			}
			line.setProductId(item.getProductId());
			line.setSkuId(item.getSkuId());
			line.setUnitPrice(sku.getRetailPrice());
			line.setItemQuantity(item.getQuantity());
			line.setCurrency(UserInfoContextHolder.getMerchantStore().getCurrency());
			// line.setTotal(total);
			productItems.add(line);
		}
		order.setProductLines(productItems);
		this.salesOrderService.save(order);
		return model;

	}

}
