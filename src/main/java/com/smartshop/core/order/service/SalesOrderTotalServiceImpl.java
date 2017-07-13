package com.smartshop.core.order.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.smartshop.core.catalog.service.ProductService;

@Service("salesOrderTotalService")
public class SalesOrderTotalServiceImpl implements SalesOrderTotalService {

	@Inject
	private ProductService productService;

}
