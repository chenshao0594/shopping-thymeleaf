package com.shoppay.shop.controller;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.springframework.web.servlet.ModelAndView;

import com.shoppay.core.order.SalesOrder;
import com.shoppay.core.order.service.SalesOrderService;
import com.shoppay.shop.model.PaymentInfo;

public class ShoppingPayController extends AbstractShoppingController {

	@Inject
	private SalesOrderService salesOrderService;


	public void payment(ModelAndView model, PaymentInfo paymentInfo) {
		SalesOrder order = this.salesOrderService.findOne(paymentInfo.getOrderId());
		Validate.notNull(order);
		order.getTotal();
		
		
		
		
		switch(paymentInfo.getType()) {
		case CREDITCARD:
			break;
		case FREE:
			break;
		case COD:
			break;
		case MONEYORDER:
			break;
		case PAYPAL:
			break;
		case STRIPE:
			break;
		case WEPAY:
			break;
		default:
			break;


		}


	}
}
