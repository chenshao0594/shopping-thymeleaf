package com.shoppay.shop.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.core.cart.CartItem;
import com.shoppay.core.cart.service.CartItemService;
import com.shoppay.core.catalog.Product;
import com.shoppay.core.catalog.SKU;
import com.shoppay.core.catalog.facade.PricingFacade;
import com.shoppay.core.catalog.service.ProductService;
import com.shoppay.core.catalog.service.SKUService;
import com.shoppay.core.common.Delivery;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.facade.PaymentFacade;
import com.shoppay.core.order.OrderProductLine;
import com.shoppay.core.order.SalesOrder;
import com.shoppay.core.order.enumeration.SalesOrderStatus;
import com.shoppay.core.order.service.SalesOrderService;
import com.shoppay.shop.model.PaymentInfo;
import com.shoppay.shop.model.ShoppingOrderContext;
import com.shoppay.shop.utils.UserInfoContextHolder;
import com.shoppay.web.constants.ShoppingControllerConstants;

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

	@Inject
	private PricingFacade pricingFacade;

	@Inject
	private PaymentFacade paymentFacade;

	@Timed
	@PostMapping()
	public ModelAndView addOrder(ModelAndView model, ShoppingOrderContext orderContext,
			final HttpServletRequest request)
			throws BusinessException, IllegalAccessException, InvocationTargetException {
		ShoppingOrderContext orderSession = (ShoppingOrderContext) request.getSession()
				.getAttribute(ShoppingControllerConstants.ORDER_KEY);
		Set<Long> cartItemIds = orderSession.getCartItemIds();
		SalesOrder order = new SalesOrder();
		order.setBilling(orderContext.getBilling());
		Delivery delivery = new Delivery();
		BeanUtils.copyProperties(delivery, orderContext.getBilling());
		order.setDelivery(delivery);

		Customer customer = UserInfoContextHolder.getCustomer();
		order.setCustomerId(customer.getId());
		order.setStatus(SalesOrderStatus.INITED);
		order.setBilling(customer.getBilling());
		order.setDelivery(customer.getDelivery());
		order.setMerchant(UserInfoContextHolder.getMerchantStore());
		Set<OrderProductLine> productItems = new HashSet<OrderProductLine>();
		BigDecimal orderTotal = new BigDecimal(0);
		for (Long id : cartItemIds) {
			CartItem item = this.cartItemService.findOne(id);
			OrderProductLine line = new OrderProductLine();
			Product product = this.productService.findOne(item.getProductId());
			if (product == null) {
				throw new BusinessException(" product is null {}" + item.getId());
			}
			SKU sku = this.skuService.findOne(item.getSkuId());
			if (sku == null) {
				throw new BusinessException("sku is null");
			}
			BigDecimal lineTotal = this.pricingFacade.calculate(sku, item.getQuantity());
			line.setProductId(item.getProductId());
			line.setSkuId(item.getSkuId());
			line.setSkuName(sku.getName());
			line.setUnitPrice(sku.getRetailPrice());
			line.setItemQuantity(item.getQuantity());
			line.setCurrency(UserInfoContextHolder.getMerchantStore().getCurrency());
			line.setTotal(lineTotal);
			line.setSalesOrder(order);
			productItems.add(line);
			orderTotal.add(lineTotal);
		}
		order.setTotal(orderTotal);
		order.setProductLines(productItems);
		this.salesOrderService.save(order);
		for (Long id : cartItemIds) {
			this.cartItemService.delete(id);
		}
		model.addObject("orderId", order.getId());
		model.addObject("methods", this.paymentFacade.methods());
		model.setViewName(ShoppingControllerConstants.Payment.methods);
		return model;

	}

	@Timed
	@PostMapping("payment")
	public void payment(ModelAndView model, PaymentInfo paymentInfo, final HttpServletRequest request) {
		SalesOrder order = this.salesOrderService.findOne(paymentInfo.getOrderId());

	}

}
