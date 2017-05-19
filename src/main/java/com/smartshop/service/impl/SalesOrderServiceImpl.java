package com.smartshop.service.impl;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.pdf.fonts.otf.Language;
import com.smartshop.constants.BusinessConstants;
import com.smartshop.core.cart.Cart;
import com.smartshop.core.cart.CartItem;
import com.smartshop.core.order.OrderStatusHistory;
import com.smartshop.core.order.OrderTotal;
import com.smartshop.core.order.OrderTotalType;
import com.smartshop.core.order.SalesOrder;
import com.smartshop.core.order.model.OrderSummary;
import com.smartshop.core.order.model.OrderTotalSummary;
import com.smartshop.core.payments.model.Payment;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.domain.ShippingConfiguration;
import com.smartshop.domain.Transaction;
import com.smartshop.exception.BusinessException;
import com.smartshop.order.model.OrderSummaryEnum;
import com.smartshop.order.model.SalesOrderSummary;
import com.smartshop.order.service.OrderTotalService;
import com.smartshop.repository.SalesOrderRepository;
import com.smartshop.repository.search.SalesOrderSearchRepository;
import com.smartshop.service.SalesOrderService;

/**
 * Service Implementation for managing Product.
 */
@Service
@Transactional
public class SalesOrderServiceImpl extends AbstractDomainServiceImpl<SalesOrder, Long> implements SalesOrderService {

	private final Logger LOGGER = LoggerFactory.getLogger(SalesOrderServiceImpl.class);
	private final SalesOrderRepository salesOrderRepository;
	private final SalesOrderSearchRepository salesOrderSearchRepository;

	@Inject
	private OrderTotalService orderTotalService;

	public SalesOrderServiceImpl(SalesOrderRepository salesOrderRepository,
			SalesOrderSearchRepository salesOrderSearchRepository) {
		super(salesOrderRepository, salesOrderSearchRepository);
		this.salesOrderRepository = salesOrderRepository;
		this.salesOrderSearchRepository = salesOrderSearchRepository;
	}

	@Override
	public void addOrderStatusHistory(SalesOrder order, OrderStatusHistory history) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public OrderTotalSummary caculateOrderTotal(OrderSummary orderSummary, Customer customer, MerchantStore store)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderTotalSummary caculateOrderTotal(OrderSummary orderSummary, MerchantStore store)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderTotalSummary calculateShoppingCartTotal(Cart shoppingCart, Customer customer, MerchantStore store)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderTotalSummary calculateShoppingCartTotal(Cart shoppingCart, MerchantStore store)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteArrayOutputStream generateInvoice(MerchantStore store, SalesOrder order) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SalesOrder getOrder(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(SalesOrder order) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public SalesOrder processOrder(SalesOrder order, Customer customer, List<CartItem> items, OrderTotalSummary summary,
			Payment payment, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SalesOrder processOrder(SalesOrder order, Customer customer, List<CartItem> items, OrderTotalSummary summary,
			Payment payment, Transaction transaction, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	private OrderTotalSummary caculateShoppingCart(final Cart shoppingCart, final Customer customer,
			final MerchantStore store, final Language language) throws Exception {
		SalesOrderSummary orderSummary = new SalesOrderSummary();
		orderSummary.setOrderSummaryType(OrderSummaryEnum.SHOPPINGCART);
		orderSummary.setCartItems(shoppingCart.getLineItems());
		return this.caculateOrder(orderSummary, customer, store, language);
	}

	private OrderTotalSummary caculateOrder(SalesOrderSummary summary, Customer customer, final MerchantStore store,
			final Language language) throws Exception {
		OrderTotalSummary totalSummary = new OrderTotalSummary();
		List<OrderTotal> orderTotals = new ArrayList<OrderTotal>();
		Map<String, OrderTotal> otherPricesTotals = new HashMap<String, OrderTotal>();
		ShippingConfiguration shippingConfiguration = null;

		BigDecimal grandTotal = new BigDecimal(0);
		grandTotal.setScale(2, RoundingMode.HALF_UP);
		BigDecimal subTotal = new BigDecimal(0);
		subTotal.setScale(2, RoundingMode.HALF_UP);
		for (CartItem item : summary.getCartItems()) {
			BigDecimal st = item.getItemPrice().multiply(new BigDecimal(item.getQuantity()));
			item.setSubTotal(st);
			subTotal = subTotal.add(st);
			// Other prices stategy
			/*
			 * FinalPrice finalPrice = item.getFinalPrice(); if (finalPrice !=
			 * null) { List<FinalPrice> otherPrices =
			 * finalPrice.getAdditionalPrices(); if (otherPrices != null) { for
			 * (FinalPrice price : otherPrices) { if (!price.isDefaultPrice()) {
			 * OrderTotal itemSubTotal =
			 * otherPricesTotals.get(price.getProductPrice().getCode());
			 *
			 * if (itemSubTotal == null) { itemSubTotal = new OrderTotal();
			 * itemSubTotal.setModule(BusinessConstants.
			 * OT_ITEM_PRICE_MODULE_CODE); //
			 * itemSubTotal.setText(Constants.OT_ITEM_PRICE_MODULE_CODE);
			 * itemSubTotal.setTitle(BusinessConstants.OT_ITEM_PRICE_MODULE_CODE
			 * );
			 * itemSubTotal.setOrderTotalCode(price.getProductPrice().getCode())
			 * ; itemSubTotal.setOrderTotalType(OrderTotalType.PRODUCT);
			 * itemSubTotal.setSortOrder(0);
			 * otherPricesTotals.put(price.getProductPrice().getCode(),
			 * itemSubTotal); }
			 *
			 * BigDecimal orderTotalValue = itemSubTotal.getValue(); if
			 * (orderTotalValue == null) { orderTotalValue = new BigDecimal(0);
			 * orderTotalValue.setScale(2, RoundingMode.HALF_UP); }
			 *
			 * orderTotalValue = orderTotalValue.add(price.getFinalPrice());
			 * itemSubTotal.setValue(orderTotalValue); if
			 * (price.getProductPrice().getProductPriceType().name().equals(
			 * OrderValueType.ONE_TIME)) { subTotal =
			 * subTotal.add(price.getFinalPrice()); } } } } }
			 */

		}

		// only in order page, otherwise invokes too many processing
		/*
		 * if (OrderSummaryEnum.ORDERTOTAL.name().equals(summary.
		 * getOrderSummaryType().name())) { // Post processing order total
		 * variation modules for sub total // calculation - drools, custom
		 * modules // may affect the sub total OrderTotalVariation
		 * orderTotalVariation =
		 * orderTotalService.findOrderTotalVariation(summary, customer, store,
		 * language);
		 *
		 * int currentCount = 10;
		 *
		 * if (CollectionUtils.isNotEmpty(orderTotalVariation.getVariations()))
		 * { for (OrderTotal variation : orderTotalVariation.getVariations()) {
		 * variation.setSortOrder(currentCount++); orderTotals.add(variation);
		 * subTotal = subTotal.subtract(variation.getValue()); } } }
		 */
		totalSummary.setSubTotal(subTotal);
		grandTotal = grandTotal.add(subTotal);
		OrderTotal orderTotalSubTotal = new OrderTotal();
		orderTotalSubTotal.setModule(BusinessConstants.OT_SUBTOTAL_MODULE_CODE);
		orderTotalSubTotal.setOrderTotalType(OrderTotalType.SUBTOTAL);
		orderTotalSubTotal.setOrderTotalCode("order.total.subtotal");
		orderTotalSubTotal.setTitle(BusinessConstants.OT_SUBTOTAL_MODULE_CODE);
		// orderTotalSubTotal.setText("order.total.subtotal");
		orderTotalSubTotal.setSortOrder(5);
		orderTotalSubTotal.setValue(subTotal);
		orderTotals.add(orderTotalSubTotal);
		// shipping
		if (summary.getShippingSummary() != null) {

			OrderTotal shippingSubTotal = new OrderTotal();
			shippingSubTotal.setModule(BusinessConstants.OT_SHIPPING_MODULE_CODE);
			shippingSubTotal.setOrderTotalType(OrderTotalType.SHIPPING);
			shippingSubTotal.setOrderTotalCode("order.total.shipping");
			shippingSubTotal.setTitle(BusinessConstants.OT_SHIPPING_MODULE_CODE);
			// shippingSubTotal.setText("order.total.shipping");
			shippingSubTotal.setSortOrder(100);

			orderTotals.add(shippingSubTotal);

			if (!summary.getShippingSummary().isFreeShipping()) {
				shippingSubTotal.setValue(summary.getShippingSummary().getShipping());
				grandTotal = grandTotal.add(summary.getShippingSummary().getShipping());
			} else {
				shippingSubTotal.setValue(new BigDecimal(0));
				grandTotal = grandTotal.add(new BigDecimal(0));
			}

			// check handling fees
			/*
			 * shippingConfiguration =
			 * shippingService.getShippingConfiguration(store); if
			 * (summary.getShippingSummary().getHandling() != null &&
			 * summary.getShippingSummary().getHandling().doubleValue() > 0) {
			 * if (shippingConfiguration.getHandlingFees() != null &&
			 * shippingConfiguration.getHandlingFees().doubleValue() > 0) {
			 * OrderTotal handlingubTotal = new OrderTotal();
			 * handlingubTotal.setModule(BusinessConstants.
			 * OT_HANDLING_MODULE_CODE);
			 * handlingubTotal.setOrderTotalType(OrderTotalType.HANDLING);
			 * handlingubTotal.setOrderTotalCode("order.total.handling");
			 * handlingubTotal.setTitle(BusinessConstants.
			 * OT_HANDLING_MODULE_CODE); //
			 * handlingubTotal.setText("order.total.handling");
			 * handlingubTotal.setSortOrder(120);
			 * handlingubTotal.setValue(summary.getShippingSummary().getHandling
			 * ()); orderTotals.add(handlingubTotal); grandTotal =
			 * grandTotal.add(summary.getShippingSummary().getHandling()); } }
			 */
		}

		// tax
		/*
		 * List<TaxItem> taxes = taxService.calculateTax(summary, customer,
		 * store, language); if (taxes != null && taxes.size() > 0) { BigDecimal
		 * totalTaxes = new BigDecimal(0); totalTaxes.setScale(2,
		 * RoundingMode.HALF_UP); int taxCount = 200; for (TaxItem tax : taxes)
		 * {
		 *
		 * OrderTotal taxLine = new OrderTotal();
		 * taxLine.setModule(BusinessConstants.OT_TAX_MODULE_CODE);
		 * taxLine.setOrderTotalType(OrderTotalType.TAX);
		 * taxLine.setOrderTotalCode(tax.getLabel());
		 * taxLine.setSortOrder(taxCount);
		 * taxLine.setTitle(BusinessConstants.OT_TAX_MODULE_CODE);
		 * taxLine.setText(tax.getLabel());
		 * taxLine.setValue(tax.getItemPrice());
		 *
		 * totalTaxes = totalTaxes.add(tax.getItemPrice());
		 * orderTotals.add(taxLine); //
		 * grandTotal=grandTotal.add(tax.getItemPrice());
		 *
		 * taxCount++;
		 *
		 * } grandTotal = grandTotal.add(totalTaxes);
		 * totalSummary.setTaxTotal(totalTaxes); }
		 */

		// grand total
		OrderTotal orderTotal = new OrderTotal();
		orderTotal.setModule(BusinessConstants.OT_TOTAL_MODULE_CODE);
		orderTotal.setOrderTotalType(OrderTotalType.TOTAL);
		orderTotal.setOrderTotalCode("order.total.total");
		orderTotal.setTitle(BusinessConstants.OT_TOTAL_MODULE_CODE);
		orderTotal.setText("order.total.total");
		orderTotal.setSortOrder(500);
		orderTotal.setValue(grandTotal);
		orderTotals.add(orderTotal);

		totalSummary.setTotal(grandTotal);
		totalSummary.setTotals(orderTotals);
		return totalSummary;

	}

}