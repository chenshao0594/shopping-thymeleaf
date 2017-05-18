package com.smartshop.facade;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.smartshop.core.cart.ShoppingCart;
import com.smartshop.core.cart.service.ShoppingCartService;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.shop.model.ShoppingCartData;

@Service("shoppingCartFacade")
public class ShoppingCartFacadeImpl implements ShoppingCartFacade {

	@Inject
	private ShoppingCartService shoppingCartService;

	@Override
	public ShoppingCartData addItemsToShoppingCart(ShoppingCartData shoppingCartData,
			com.smartshop.shop.model.ShoppingCartItem item, MerchantStore store, Customer customer) throws Exception {
		ShoppingCart cartModel = null;
		if (!StringUtils.isBlank(item.getCode())) {
			cartModel = getShoppingCartModel(item.getCode(), store);
			if (cartModel == null) {
				cartModel = createCartModel(shoppingCartData.getCode(), store, customer);
			}
		}
		if (cartModel == null) {
			final String shoppingCartCode = StringUtils.isNotBlank(shoppingCartData.getCode())
					? shoppingCartData.getCode() : null;
			cartModel = createCartModel(shoppingCartCode, store, customer);
		}
		com.smartshop.shop.model.ShoppingCartItem shoppingCartItem = createCartItem(cartModel, item, store);

		boolean duplicateFound = false;
		if (CollectionUtils.isEmpty(item.getShoppingCartAttributes())) {
			Set<com.smartshop.shop.model.ShoppingCartItem> cartModelItems = cartModel.getLineItems();
			for (com.smartshop.shop.model.ShoppingCartItem cartItem : cartModelItems) {
				if (cartItem.getProduct().getId().longValue() == shoppingCartItem.getProduct().getId().longValue()) {
					if (CollectionUtils.isEmpty(cartItem.getAttributes())) {
						if (!duplicateFound) {
							cartItem.setQuantity(cartItem.getQuantity() + shoppingCartItem.getQuantity());
							duplicateFound = true;
							break;
						}
					}
				}
			}
		}

		if (!duplicateFound) {
			cartModel.getLineItems().add(shoppingCartItem);
		}
		/** Update cart in database with line items **/
		shoppingCartService.saveOrUpdate(cartModel);

		// refresh cart
		cartModel = shoppingCartService.getById(cartModel.getId(), store);
		shoppingCartCalculationService.calculate(cartModel, store, language);
		ShoppingCartDataPopulator shoppingCartDataPopulator = new ShoppingCartDataPopulator();
		shoppingCartDataPopulator.setShoppingCartCalculationService(shoppingCartCalculationService);
		shoppingCartDataPopulator.setPricingService(pricingService);
		shoppingCartDataPopulator.setimageUtils(imageUtils);

		return shoppingCartDataPopulator.populate(cartModel, store, language);
		return null;
	}

	@Override
	public ShoppingCart createCartModel(String shoppingCartCode, MerchantStore store, Customer customer)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCartData getShoppingCartData(Customer customer, MerchantStore store, String shoppingCartId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCartData getShoppingCartData(ShoppingCart shoppingCart) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCartData getShoppingCartData(String code, MerchantStore store) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCartData removeCartItem(Long itemID, String cartId, MerchantStore store) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCartData updateCartItem(Long itemID, String cartId, long quantity, MerchantStore store)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteShoppingCart(Long id, MerchantStore store) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public ShoppingCartData updateCartItems(List<com.smartshop.shop.model.ShoppingCartItem> shoppingCartItems,
			MerchantStore store) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCart getShoppingCartModel(String shoppingCartCode, MerchantStore store) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCart getShoppingCartModel(Customer customer, MerchantStore store) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteShoppingCart(String code, MerchantStore store) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdateShoppingCart(ShoppingCart cart) throws Exception {
		// TODO Auto-generated method stub

	}

}
