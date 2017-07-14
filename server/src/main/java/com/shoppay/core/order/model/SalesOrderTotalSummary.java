package com.shoppay.core.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.shoppay.core.order.SalesOrderTotal;

public class SalesOrderTotalSummary implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal subTotal;// one time price for items
	private BigDecimal total;// final price
	private BigDecimal taxTotal;// total of taxes

	private List<SalesOrderTotal> totals;// all other fees (tax, shipping ....)

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<SalesOrderTotal> getTotals() {
		return totals;
	}

	public void setTotals(List<SalesOrderTotal> totals) {
		this.totals = totals;
	}

	public BigDecimal getTaxTotal() {
		return taxTotal;
	}

	public void setTaxTotal(BigDecimal taxTotal) {
		this.taxTotal = taxTotal;
	}

}
