package com.shoppay.shop.controller;

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
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.reference.Country;
import com.shoppay.common.service.CountryService;
import com.shoppay.core.cart.Cart;
import com.shoppay.core.cart.CartItem;
import com.shoppay.core.cart.service.CartService;
import com.shoppay.core.catalog.Product;
import com.shoppay.core.catalog.SKU;
import com.shoppay.core.catalog.facade.PricingFacade;
import com.shoppay.core.catalog.service.ProductService;
import com.shoppay.core.catalog.service.SKUService;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.customer.service.CustomerService;
import com.shoppay.core.facade.PaymentFacade;
import com.shoppay.core.model.ShoppingOrderContext;
import com.shoppay.core.order.OrderProductLine;
import com.shoppay.core.order.SalesOrder;
import com.shoppay.core.order.enumeration.SalesOrderStatus;
import com.shoppay.core.order.service.SalesOrderService;
import com.shoppay.core.utils.CustomerInfoContextHolder;
import com.shoppay.web.constants.ShoppingControllerConstants;

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
			Customer customer = customerService.findByName(username);
			//Customer userInfo = UserInfoContextHolder.getCustomer();
			Cart shoppingCart = shoppingCartService.getShoppingCart(customer);
			for (CartItem item : shoppingCart.getLineItems()) {
				order.getCartItemIds().add(item.getId());
			}
			order.setUserId(customer.getId());
			Page<Country> page = this.countryService.findAll(null);
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
	// @PostMapping("order")
	public ModelAndView order(ModelAndView model, final Customer customer) throws BusinessException {
		Customer userInfo = CustomerInfoContextHolder.getCustomer();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Cart shoppingCart = shoppingCartService.getShoppingCart(userInfo);
		Set<CartItem> items = shoppingCart.getLineItems();
		SalesOrder order = new SalesOrder();
		order.setCustomerId(userInfo.getId());
		order.setStatus(SalesOrderStatus.INITED);
		order.setBilling(customer.getBilling());
		order.setDelivery(customer.getDelivery());
		order.setMerchant(CustomerInfoContextHolder.getMerchantStore());
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
			line.setCurrency(CustomerInfoContextHolder.getMerchantStore().getCurrency());
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
