package com.shoppay.core.order.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppay.app.repository.SalesOrderRepository;
import com.shoppay.common.constants.BusinessConstants;
import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.service.impl.AbstractDomainServiceImpl;
import com.shoppay.core.cart.Cart;
import com.shoppay.core.cart.CartItem;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.enumeration.OrderSummaryEnum;
import com.shoppay.core.order.QSalesOrder;
import com.shoppay.core.order.SalesOrder;
import com.shoppay.core.order.SalesOrderTotal;
import com.shoppay.core.order.SalesOrderTotalType;
import com.shoppay.core.order.model.SalesOrderSummary;
import com.shoppay.core.order.model.SalesOrderTotalSummary;
import com.shoppay.core.order.service.SalesOrderService;
import com.shoppay.core.order.service.SalesOrderTotalService;
import com.shoppay.repository.search.SalesOrderSearchRepository;

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
	private SalesOrderTotalService orderTotalService;

	public SalesOrderServiceImpl(SalesOrderRepository salesOrderRepository,
			SalesOrderSearchRepository salesOrderSearchRepository) {
		super(salesOrderRepository, salesOrderSearchRepository);
		this.salesOrderRepository = salesOrderRepository;
		this.salesOrderSearchRepository = salesOrderSearchRepository;
	}

	@Override
	public SalesOrderTotalSummary calculateShoppingCartTotal(Cart shoppingCart, MerchantStore store)
			throws BusinessException {
		Validate.notNull(shoppingCart, "Order summary cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		try {
			return caculateShoppingCart(shoppingCart, null, store);
		} catch (Exception e) {
			LOGGER.error("Error while calculating shopping cart total" + e);
			throw new BusinessException(e);
		}
	}

	private SalesOrderTotalSummary caculateShoppingCart(final Cart shoppingCart, final Customer customer,
			final MerchantStore store) throws Exception {
		SalesOrderSummary orderSummary = new SalesOrderSummary();
		orderSummary.setOrderSummaryType(OrderSummaryEnum.SHOPPINGCART);
		orderSummary.setCartItems(shoppingCart.getLineItems());
		return this.caculateOrder(orderSummary, customer, store);
	}

	private SalesOrderTotalSummary caculateOrder(SalesOrderSummary summary, Customer customer,
			final MerchantStore store) throws Exception {
		SalesOrderTotalSummary totalSummary = new SalesOrderTotalSummary();
		List<SalesOrderTotal> orderTotals = new ArrayList<SalesOrderTotal>();
		BigDecimal grandTotal = new BigDecimal(0);
		grandTotal.setScale(2, RoundingMode.HALF_UP);
		BigDecimal subTotal = new BigDecimal(0);
		subTotal.setScale(2, RoundingMode.HALF_UP);
		for (CartItem item : summary.getCartItems()) {
			BigDecimal st = item.getItemPrice().multiply(new BigDecimal(item.getQuantity()));
			item.setSubTotal(st);
			subTotal = subTotal.add(st);
		}

		totalSummary.setSubTotal(subTotal);
		grandTotal = grandTotal.add(subTotal);
		SalesOrderTotal orderTotalSubTotal = new SalesOrderTotal();
		orderTotalSubTotal.setModule(BusinessConstants.OT_SUBTOTAL_MODULE_CODE);
		orderTotalSubTotal.setOrderTotalType(SalesOrderTotalType.SUBTOTAL);
		orderTotalSubTotal.setCode("order.total.subtotal");
		orderTotalSubTotal.setTitle(BusinessConstants.OT_SUBTOTAL_MODULE_CODE);
		orderTotalSubTotal.setSortOrder(5);
		orderTotalSubTotal.setValue(subTotal);
		orderTotals.add(orderTotalSubTotal);
		if (summary.getShippingSummary() != null) {
			SalesOrderTotal shippingSubTotal = new SalesOrderTotal();
			shippingSubTotal.setModule(BusinessConstants.OT_SHIPPING_MODULE_CODE);
			shippingSubTotal.setOrderTotalType(SalesOrderTotalType.SHIPPING);
			shippingSubTotal.setCode("order.total.shipping");
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
		}
		SalesOrderTotal orderTotal = new SalesOrderTotal();
		orderTotal.setModule(BusinessConstants.OT_TOTAL_MODULE_CODE);
		orderTotal.setOrderTotalType(SalesOrderTotalType.TOTAL);
		orderTotal.setCode("order.total.total");
		orderTotal.setTitle(BusinessConstants.OT_TOTAL_MODULE_CODE);
		orderTotal.setText("order.total.total");
		orderTotal.setSortOrder(500);
		orderTotal.setValue(grandTotal);
		orderTotals.add(orderTotal);
		totalSummary.setTotal(grandTotal);
		totalSummary.setTotals(orderTotals);
		return totalSummary;

	}

	@Override
	public List<SalesOrder> findByCustomer(Customer customer) {
		QSalesOrder qSales = QSalesOrder.salesOrder;
		return (List<SalesOrder>) this.salesOrderRepository.findAll(qSales.customerId.eq(customer.getId()));
	}

	@Override
	public SalesOrderTotalSummary calculateShoppingCartTotal(Cart cartModel, Customer customer, MerchantStore store) {
		// TODO Auto-generated method stub
		return null;
	}

}