package com.smartshop.shop.controller;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.smartshop.common.ShoppingControllerConstants;
import com.smartshop.core.cart.Cart;
import com.smartshop.core.cart.CartItem;
import com.smartshop.core.cart.service.CartService;
import com.smartshop.core.catalog.Product;
import com.smartshop.core.catalog.SKU;
import com.smartshop.core.catalog.service.ProductService;
import com.smartshop.core.catalog.service.SKUService;
import com.smartshop.core.common.Country;
import com.smartshop.core.customer.Customer;
import com.smartshop.core.facade.PaymentFacade;
import com.smartshop.core.facade.PricingFacade;
import com.smartshop.core.order.OrderProductLine;
import com.smartshop.core.order.SalesOrder;
import com.smartshop.core.order.enumeration.SalesOrderStatus;
import com.smartshop.core.order.service.SalesOrderService;
import com.smartshop.exception.BusinessException;
import com.smartshop.service.CountryService;
import com.smartshop.service.CustomerService;
import com.smartshop.shop.model.ShoppingOrderContext;
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

	@Inject
	private PaymentFacade paymentFacade;

	@Timed
	@GetMapping("address")
	public ModelAndView address(ModelAndView model, final HttpServletRequest request, ShoppingOrderContext order)
			throws BusinessException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UUID uuid = UUID.randomUUID();
		order.setUuid(uuid.toString());
		LOGGER.info("principal user {}", principal);
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			Customer customer = customerService.findCustomerByName(username);
			Customer userInfo = UserInfoContextHolder.getCustomer();
			Cart shoppingCart = shoppingCartService.getShoppingCart(userInfo);
			for (CartItem item : shoppingCart.getLineItems()) {
				order.getCartItemIds().add(item.getId());
			}
			order.setUserId(userInfo.getId());
			Page<Country> page = this.countryService.findAll(null);
			model.addObject("countries", page.getContent());
			model.addObject("customer", customer);
			model.setViewName(ShoppingControllerConstants.Checkout.address);
		} else {
			LOGGER.info(" not UserDetails user {}", principal.getClass());
			model.setViewName(ShoppingControllerConstants.Checkout.start);
		}
		request.getSession().setAttribute(ShoppingControllerConstants.ORDER_KEY, order);
		return model;
	}

	/*
	 * @Timed
	 *
	 * @PostMapping("shipping") public ModelAndView shipping(ModelAndView model,
	 * ShoppingOrderContext order, final HttpServletRequest request) {
	 * ShoppingOrderContext orderSession = (ShoppingOrderContext)
	 * request.getSession().getAttribute(ORDER_KEY);
	 * orderSession.setBilling(order.getBilling());
	 * request.getSession().setAttribute(ORDER_KEY, orderSession); Object
	 * principal =
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 * LOGGER.info("principal user {}", principal); if (principal instanceof
	 * UserDetails) { LOGGER.info("UserDetails user {}", principal); String
	 * username = ((UserDetails) principal).getUsername(); Customer customer =
	 * customerService.findCustomerByName(username); Page page =
	 * this.countryService.findAll(null); model.addObject("countries",
	 * page.getContent()); model.addObject("customer", customer);
	 * model.setViewName(ShoppingControllerConstants.Checkout.address); } else {
	 * LOGGER.info(" not UserDetails user {}", principal.getClass());
	 * model.setViewName(ShoppingControllerConstants.Checkout.start); }
	 * model.setViewName(ShoppingControllerConstants.Payment.methods); return
	 * model; }
	 */
	@Timed
	// @PostMapping("order")
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
			productItems.add(line);
			orderTotal.add(lineTotal);
		}
		order.setTotal(orderTotal);
		order.setProductLines(productItems);
		this.salesOrderService.save(order);
		this.shoppingCartService.delete(shoppingCart);
		model.addObject("methods", this.paymentFacade.methods());
		model.setViewName(ShoppingControllerConstants.Payment.methods);
		return model;
	}

}
