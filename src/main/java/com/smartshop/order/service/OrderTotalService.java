package com.smartshop.order.service;

import com.itextpdf.text.pdf.fonts.otf.Language;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.order.model.OrderTotalVariation;
import com.smartshop.order.model.SalesOrderSummary;

public interface OrderTotalService {
	OrderTotalVariation findOrderTotalVariation(final SalesOrderSummary summary, final Customer customer,
			final MerchantStore store, final Language language) throws Exception;

}
