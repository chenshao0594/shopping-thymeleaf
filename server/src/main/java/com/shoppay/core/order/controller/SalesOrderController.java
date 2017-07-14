package com.shoppay.core.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppay.admin.controller.AbstractDomainController;
import com.shoppay.constants.AppConstants;
import com.shoppay.core.order.SalesOrder;
import com.shoppay.core.order.service.SalesOrderService;

@Controller
@RequestMapping(AppConstants.ADMIN_PREFIX + "/" + SalesOrderController.SECTION_KEY)

public class SalesOrderController extends AbstractDomainController<SalesOrder, Long> {

	private final Logger log = LoggerFactory.getLogger(SalesOrderController.class);
	public static final String SECTION_KEY = "salesOrders";

	private final SalesOrderService salesOrderService;

	public SalesOrderController(SalesOrderService salesOrderService) {
		super(salesOrderService);
		this.salesOrderService = salesOrderService;
	}

	@Override
	protected String getSectionKey() {
		return SECTION_KEY;
	}

	@Override
	protected Class getEntityClass() {
		return SalesOrder.class;
	}

}
