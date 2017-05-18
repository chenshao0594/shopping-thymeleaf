package com.smartshop.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import com.smartshop.core.enumeration.TransactionType;
import com.smartshop.core.order.SalesOrder;
import com.smartshop.core.payment.enumeration.PaymentType;
import com.smartshop.domain.common.BusinessDomain;

@Entity
@Table(name = "transaction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Transaction extends BusinessDomain<Long, Transaction> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6350869577785842776L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID", nullable = true)
	private SalesOrder order;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	@Column(name = "DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;

	@Column(name = "TYPE")
	@Enumerated(value = EnumType.STRING)
	private TransactionType transactionType;

	@Column(name = "PAYMENT_TYPE")
	@Enumerated(value = EnumType.STRING)
	private PaymentType paymentType;

	@Column(name = "DETAILS")
	@Type(type = "org.hibernate.type.MaterializedClobType")
	private String details;

	@Transient
	private Map<String, String> transactionDetails = new HashMap<String, String>();

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;

	}

	public SalesOrder getOrder() {
		return order;
	}

	public void setOrder(SalesOrder order) {
		this.order = order;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Map<String, String> getTransactionDetails() {
		return transactionDetails;
	}

	public void setTransactionDetails(Map<String, String> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

}
