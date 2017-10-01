package com.shoppay.populator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.exception.ConversionException;
import com.shoppay.core.cart.Cart;
import com.shoppay.core.cart.CartItem;
import com.shoppay.core.cart.service.ShoppingCartCalculationService;
import com.shoppay.core.catalog.service.PricingService;
import com.shoppay.core.catalog.service.ProductService;
import com.shoppay.core.catalog.service.SKUService;
import com.shoppay.core.model.ShoppingCartData;
import com.shoppay.core.model.ShoppingCartItem;
import com.shoppay.core.order.model.SalesOrderTotalSummary;
import com.shoppay.order.model.OrderTotal;
import com.shoppay.order.model.SalesOrderSummary;

public class ShoppingCartDataPopulator extends AbstractDataPopulator<Cart, ShoppingCartData> {

	private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartDataPopulator.class);

	private PricingService pricingService;

	private ShoppingCartCalculationService shoppingCartCalculationService;

	private ProductService productService;

	private SKUService skuService;

	@Override
	public ShoppingCartData createTarget() {
		return new ShoppingCartData();
	}

	public void setSkuService(SKUService skuService) {
		this.skuService = skuService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public ShoppingCartCalculationService getOrderService() {
		return shoppingCartCalculationService;
	}

	public PricingService getPricingService() {
		return pricingService;
	}

	@Override
	public ShoppingCartData populate(final Cart shoppingCart, final ShoppingCartData cart, final MerchantStore store)
			throws ConversionException {
		// Validate.notNull(imageUtils, "Requires to set imageUtils");
		int cartQuantity = 0;
		cart.setCode(shoppingCart.getCode());
		Set<CartItem> items = shoppingCart.getLineItems();
		List<ShoppingCartItem> shoppingCartItemsList = Collections.emptyList();
		try {
			if (items != null) {
				shoppingCartItemsList = new ArrayList<ShoppingCartItem>();
				for (CartItem item : items) {
					ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
					shoppingCartItem.setCode(cart.getCode());
					shoppingCartItem.setSkuId(item.getSkuId());
					shoppingCartItem.setProductId(item.getProductId());
					shoppingCartItem.setId(item.getId());
					shoppingCartItem.setProductName(this.productService.findNameById(item.getProductId()));
					item.setItemPrice(this.skuService.findRetailPriceById(item.getSkuId()));
					// shoppingCartItem.setSkuName(this.skuService.findNameById(item.getProductId()));
					shoppingCartItem.setPrice(pricingService.getDisplayAmount(item.getItemPrice(), store));
					shoppingCartItem.setQuantity(item.getQuantity());
					cartQuantity = cartQuantity + item.getQuantity();
					shoppingCartItem.setItemPrice(item.getItemPrice());
					BigDecimal subTotal = item.getItemPrice().multiply(new BigDecimal(item.getQuantity()));
					shoppingCartItem.setSubTotal(pricingService.getDisplayAmount(subTotal, store));
					shoppingCartItemsList.add(shoppingCartItem);
				}
			}
			if (CollectionUtils.isNotEmpty(shoppingCartItemsList)) {
				cart.setShoppingCartItems(shoppingCartItemsList);
			}

			SalesOrderSummary summary = new SalesOrderSummary();
			Set<CartItem> itemsList = new HashSet<CartItem>();
			itemsList.addAll(shoppingCart.getLineItems());
			summary.setCartItems(itemsList);
			SalesOrderTotalSummary orderSummary = shoppingCartCalculationService.calculate(shoppingCart, store);
			if (CollectionUtils.isNotEmpty(orderSummary.getTotals())) {
				List<OrderTotal> totals = new ArrayList<OrderTotal>();
				for (com.shoppay.core.order.SalesOrderTotal t : orderSummary.getTotals()) {
					OrderTotal total = new OrderTotal();
					total.setCode(t.getCode());
					total.setValue(t.getValue());
					totals.add(total);
				}
				cart.setTotals(totals);
			}

			cart.setSubTotal(pricingService.getDisplayAmount(orderSummary.getSubTotal(), store));
			cart.setTotal(pricingService.getDisplayAmount(orderSummary.getTotal(), store));
			cart.setQuantity(cartQuantity);
			cart.setId(shoppingCart.getId());
		} catch (BusinessException ex) {
			LOG.error("Error while converting cart Model to cart Data.." + ex);
			throw new ConversionException("Unable to create cart data", ex);
		}
		return cart;

	};

	public void setPricingService(final PricingService pricingService) {
		this.pricingService = pricingService;
	}

	public void setShoppingCartCalculationService(final ShoppingCartCalculationService shoppingCartCalculationService) {
		this.shoppingCartCalculationService = shoppingCartCalculationService;
	}

}
