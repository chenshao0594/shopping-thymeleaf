package com.smart.shopping.core.salesorder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.shopping.admin.controller.AbstractDomainController;
import com.smart.shopping.config.AppConstants;
import com.smart.shopping.core.order.SalesOrder;
import com.smart.shopping.service.SalesOrderService;

@Controller
@RequestMapping("/" + AppConstants.ADMIN_PREFIX + "/" + SalesOrderController.SECTION_KEY)

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
