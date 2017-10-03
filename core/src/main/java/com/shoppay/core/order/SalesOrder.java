package com.shoppay.core.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Type;

import com.shoppay.common.domain.BusinessDomain;
import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.reference.Billing;
import com.shoppay.common.reference.Delivery;
import com.shoppay.core.order.enumeration.SalesOrderChannel;
import com.shoppay.core.order.enumeration.SalesOrderStatus;
import com.shoppay.core.order.enumeration.SalesOrderType;
import com.shoppay.core.payment.enumeration.PaymentType;

@Entity
@Table(name = "SALES_ORDER")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SalesOrder extends BusinessDomain<Long, SalesOrder> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ORDER_STATUS")
	@Enumerated(value = EnumType.STRING)
	private SalesOrderStatus status;

	@Column(name = "CUSTOMER_ID")
	private Long customerId;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_PURCHASED")
	private Date datePurchased;

	// used for an order payable on multiple installment
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ORDER_DATE_FINISHED")
	private Date orderDateFinished;

	// What was the exchange rate
	@Column(name = "CURRENCY_VALUE")
	private BigDecimal currencyValue = new BigDecimal(1);// default 1-1

	@Column(name = "ORDER_TOTAL")
	private BigDecimal total;

	@Column(name = "IP_ADDRESS")
	private String ipAddress;

	@Column(name = "CHANNEL")
	@Enumerated(value = EnumType.STRING)
	private SalesOrderChannel channel = SalesOrderChannel.ONLINE;

	@Column(name = "ORDER_TYPE")
	@Enumerated(value = EnumType.STRING)
	private SalesOrderType orderType = SalesOrderType.ORDER;

	@Column(name = "PAYMENT_TYPE")
	@Enumerated(value = EnumType.STRING)
	private PaymentType paymentType;

	@Column(name = "PAYMENT_CODE")
	private String paymentCode;

	@Column(name = "SHIPPING_CODE")
	private String shippingCode;

	@Column(name = "CUSTOMER_AGREED")
	private Boolean customerAgreement = false;

	@Column(name = "CONFIRMED_ADDRESS")
	private Boolean confirmedAddress = false;

	@Embedded
	private Delivery delivery = null;

	@Valid
	@Embedded
	private Billing billing = null;

	@Type(type = "locale")
	@Column(name = "LOCALE")
	private Locale locale;

	@ManyToOne(targetEntity = MerchantStore.class)
	@JoinColumn(name = "MERCHANTID")
	private MerchantStore merchant;
	// SalesOrderLine

	@OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<OrderProductLine> productLines = new HashSet<OrderProductLine>();

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@OrderBy(clause = "ORDER_STATUS_HISTORY_ID asc")
	private Set<SalesOrderStatusHistory> orderHistory = new LinkedHashSet<SalesOrderStatusHistory>();
	
	@Column(name = "CURRENCY_CODE" ,nullable = false)
	private java.util.Currency currency;
	
	public SalesOrder() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public SalesOrderStatus getStatus() {
		return status;
	}

	public void setStatus(SalesOrderStatus status) {
		this.status = status;
	}

	public BigDecimal getCurrencyValue() {
		return currencyValue;
	}

	public void setCurrencyValue(BigDecimal currencyValue) {
		this.currencyValue = currencyValue;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public MerchantStore getMerchant() {
		return merchant;
	}

	public void setMerchant(MerchantStore merchant) {
		this.merchant = merchant;
	}

	public Set<SalesOrderStatusHistory> getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(Set<SalesOrderStatusHistory> orderHistory) {
		this.orderHistory = orderHistory;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	public Billing getBilling() {
		return billing;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setChannel(SalesOrderChannel channel) {
		this.channel = channel;
	}

	public SalesOrderChannel getChannel() {
		return channel;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public SalesOrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(SalesOrderType orderType) {
		this.orderType = orderType;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Boolean getCustomerAgreement() {
		return customerAgreement;
	}

	public void setCustomerAgreement(Boolean customerAgreement) {
		this.customerAgreement = customerAgreement;
	}

	public Boolean getConfirmedAddress() {
		return confirmedAddress;
	}

	public void setConfirmedAddress(Boolean confirmedAddress) {
		this.confirmedAddress = confirmedAddress;
	}

	public Date getDatePurchased() {
		return datePurchased;
	}

	public void setDatePurchased(Date datePurchased) {
		this.datePurchased = datePurchased;
	}

	public Date getOrderDateFinished() {
		return orderDateFinished;
	}

	public void setOrderDateFinished(Date orderDateFinished) {
		this.orderDateFinished = orderDateFinished;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getShippingCode() {
		return shippingCode;
	}

	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}

	public Set<OrderProductLine> getProductLines() {
		return productLines;
	}

	public void setProductLines(Set<OrderProductLine> productLines) {
		this.productLines = productLines;
	}

	public java.util.Currency getCurrency() {
		return currency;
	}

	public void setCurrency(java.util.Currency currency) {
		this.currency = currency;
	}
	
	

}