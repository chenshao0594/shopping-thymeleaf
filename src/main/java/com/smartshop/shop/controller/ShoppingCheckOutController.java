package com.smartshop.shop.controller;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.smartshop.common.ShoppingControllerConstants;
import com.smartshop.core.cart.Cart;
import com.smartshop.core.cart.CartItem;
import com.smartshop.core.cart.service.CartService;
import com.smartshop.core.catalog.Product;
import com.smartshop.core.catalog.SKU;
import com.smartshop.core.facade.PricingFacade;
import com.smartshop.core.order.OrderProductLine;
import com.smartshop.core.order.SalesOrder;
import com.smartshop.core.order.enumeration.SalesOrderStatus;
import com.smartshop.core.order.service.SalesOrderService;
import com.smartshop.domain.Customer;
import com.smartshop.exception.BusinessException;
import com.smartshop.service.CountryService;
import com.smartshop.service.CustomerService;
import com.smartshop.service.ProductService;
import com.smartshop.service.SKUService;
import com.smartshop.shop.utils.UserInfoContextHolder;

@Controller("ShopCheckOutController")
@RequestMapping("/checkout")
public class ShoppingCheckOutController extends AbstractShoppingController {

	private final Logger LOGGER = LoggerFactory.getLogger(ShoppingCheckOutController.class);
	@Inject
	private CustomerService customerService;

	@Inject
	private CountryService countryService;

	@Inject
	private CartService shoppingCartService;

	@Inject
	private ProductService productService;

	@Inject
	private SKUService skuService;

	@Inject
	private SalesOrderService salesOrderService;

	@Inject
	private PricingFacade pricingFacade;

	@Timed
	@GetMapping()
	public ModelAndView checkout(ModelAndView model, final HttpServletRequest request) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LOGGER.info("principal user {}", principal);
		if (principal instanceof UserDetails) {
			LOGGER.info("UserDetails user {}", principal);
			String username = ((UserDetails) principal).getUsername();
			Customer customer = customerService.findCustomerByName(username);
			Page page = this.countryService.findAll(null);
			model.addObject("countries", page.getContent());
			model.addObject("customer", customer);
			model.setViewName(ShoppingControllerConstants.Checkout.address);
		} else {
			LOGGER.info(" not UserDetails user {}", principal.getClass());
			model.setViewName(ShoppingControllerConstants.Checkout.start);
		}
		return model;
	}

	@Timed
	@PostMapping("order")
	public ModelAndView order(ModelAndView model, final Customer customer) throws BusinessException {
		Customer userInfo = UserInfoContextHolder.getCustomer();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Cart shoppingCart = shoppingCartService.getShoppingCart(userInfo);
		Set<CartItem> items = shoppingCart.getLineItems();
		SalesOrder order = new SalesOrder();
		order.setCustomerId(userInfo.getId());
		order.setStatus(SalesOrderStatus.INITED);
		order.setBilling(customer.getBilling());
		order.setDelivery(customer.getDelivery());
		order.setMerchant(UserInfoContextHolder.getMerchantStore());
		order.setBilling(customer.getBilling());
		order.setDelivery(customer.getDelivery());
		Set<OrderProductLine> productItems = new HashSet<OrderProductLine>();
		BigDecimal orderTotal = new BigDecimal(0);
		for (CartItem item : items) {
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
			// line.setTotal(total);
			productItems.add(line);
			orderTotal.add(lineTotal);
		}
		order.setTotal(orderTotal);
		order.setProductLines(productItems);
		this.salesOrderService.save(order);
		this.shoppingCartService.delete(shoppingCart);
		return model;
	}

}
