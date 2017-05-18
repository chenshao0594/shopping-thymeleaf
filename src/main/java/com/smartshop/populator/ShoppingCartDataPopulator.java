package com.smartshop.populator;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.pdf.fonts.otf.Language;
import com.smartshop.core.cart.ShoppingCart;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;
import com.smartshop.order.model.OrderTotal;
import com.smartshop.order.model.SalesOrderSummary;
import com.smartshop.shop.model.ShoppingCartAttribute;
import com.smartshop.shop.model.ShoppingCartData;

public class ShoppingCartDataPopulator extends AbstractDataPopulator<ShoppingCart, ShoppingCartData> {

	private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartDataPopulator.class);
	private PricingService pricingService;

	private ShoppingCartCalculationService shoppingCartCalculationService;

	@Override
	public ShoppingCartData createTarget() {

		return new ShoppingCartData();
	}

	public ShoppingCartCalculationService getOrderService() {
		return shoppingCartCalculationService;
	}

	public PricingService getPricingService() {
		return pricingService;
	}

	@Override
	public ShoppingCartData populate(final ShoppingCart shoppingCart, final ShoppingCartData cart,
			final MerchantStore store, final Language language) {

		// Validate.notNull(imageUtils, "Requires to set imageUtils");
		int cartQuantity = 0;
		cart.setCode(shoppingCart.getShoppingCartCode());
		Set<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> items = shoppingCart.getLineItems();
		List<ShoppingCartItem> shoppingCartItemsList = Collections.emptyList();
		try {
			if (items != null) {
				shoppingCartItemsList = new ArrayList<ShoppingCartItem>();
				for (com.salesmanager.core.model.shoppingcart.ShoppingCartItem item : items) {

					ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
					shoppingCartItem.setCode(cart.getCode());
					shoppingCartItem.setProductCode(item.getProduct().getSku());
					shoppingCartItem.setProductVirtual(item.isProductVirtual());

					shoppingCartItem.setProductId(item.getProductId());
					shoppingCartItem.setId(item.getId());
					shoppingCartItem.setName(item.getProduct().getProductDescription().getName());

					shoppingCartItem.setPrice(pricingService.getDisplayAmount(item.getItemPrice(), store));
					shoppingCartItem.setQuantity(item.getQuantity());

					cartQuantity = cartQuantity + item.getQuantity();

					shoppingCartItem.setProductPrice(item.getItemPrice());
					shoppingCartItem.setSubTotal(pricingService.getDisplayAmount(item.getSubTotal(), store));
					ProductImage image = item.getProduct().getProductImage();
					if (image != null && imageUtils != null) {
						String imagePath = imageUtils.buildProductImageUtils(store, item.getProduct().getSku(),
								image.getProductImage());
						shoppingCartItem.setImage(imagePath);
					}
					Set<com.salesmanager.core.model.shoppingcart.ShoppingCartAttributeItem> attributes = item
							.getAttributes();
					if (attributes != null) {
						List<ShoppingCartAttribute> cartAttributes = new ArrayList<ShoppingCartAttribute>();
						for (com.salesmanager.core.model.shoppingcart.ShoppingCartAttributeItem attribute : attributes) {
							ShoppingCartAttribute cartAttribute = new ShoppingCartAttribute();
							cartAttribute.setId(attribute.getId());
							cartAttribute.setAttributeId(attribute.getProductAttributeId());
							cartAttribute.setOptionId(attribute.getProductAttribute().getProductOption().getId());
							cartAttribute
									.setOptionValueId(attribute.getProductAttribute().getProductOptionValue().getId());
							List<ProductOptionDescription> optionDescriptions = attribute.getProductAttribute()
									.getProductOption().getDescriptionsSettoList();
							List<ProductOptionValueDescription> optionValueDescriptions = attribute
									.getProductAttribute().getProductOptionValue().getDescriptionsSettoList();
							if (!CollectionUtils.isEmpty(optionDescriptions)
									&& !CollectionUtils.isEmpty(optionValueDescriptions)) {
								cartAttribute.setOptionName(optionDescriptions.get(0).getName());
								cartAttribute.setOptionValue(optionValueDescriptions.get(0).getName());
								cartAttributes.add(cartAttribute);
							}
						}
						shoppingCartItem.setShoppingCartAttributes(cartAttributes);
					}
					shoppingCartItemsList.add(shoppingCartItem);
				}
			}
			if (CollectionUtils.isNotEmpty(shoppingCartItemsList)) {
				cart.setShoppingCartItems(shoppingCartItemsList);
			}

			SalesOrderSummary summary = new SalesOrderSummary();
			List<com.salesmanager.core.model.shoppingcart.ShoppingCartItem> productsList = new ArrayList<com.salesmanager.core.model.shoppingcart.ShoppingCartItem>();
			productsList.addAll(shoppingCart.getLineItems());
			summary.setProducts(productsList);
			OrderTotalSummary orderSummary = shoppingCartCalculationService.calculate(shoppingCart, store, language);

			if (CollectionUtils.isNotEmpty(orderSummary.getTotals())) {
				List<OrderTotal> totals = new ArrayList<OrderTotal>();
				for (com.salesmanager.core.model.order.OrderTotal t : orderSummary.getTotals()) {
					OrderTotal total = new OrderTotal();
					total.setCode(t.getOrderTotalCode());
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
