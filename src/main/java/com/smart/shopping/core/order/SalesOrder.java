package com.smart.shopping.core.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import org.springframework.data.elasticsearch.annotations.Document;

import com.smart.shopping.core.common.Billing;
import com.smart.shopping.core.common.Delivery;
import com.smart.shopping.core.order.enumeration.OrderChannel;
import com.smart.shopping.core.order.enumeration.OrderStatus;
import com.smart.shopping.core.order.enumeration.OrderType;
import com.smart.shopping.core.payment.enumeration.PaymentType;
import com.smart.shopping.domain.MerchantStore;
import com.smart.shopping.domain.common.BusinessDomain;

@Entity
@Table(name = "SALES_ORDER")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "salesOrder")
public class SalesOrder extends BusinessDomain<Long, SalesOrder> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ORDER_STATUS")
	@Enumerated(value = EnumType.STRING)
	private OrderStatus status;

	// the customer object can be detached. An order can exist and the customer
	// deleted
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
	private OrderChannel channel;

	@Column(name = "ORDER_TYPE")
	@Enumerated(value = EnumType.STRING)
	private OrderType orderType = OrderType.ORDER;

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

	// @Embedded
	// private CreditCard creditCard = null;

	@Type(type = "locale")
	@Column(name = "LOCALE")
	private Locale locale;

	@ManyToOne(targetEntity = MerchantStore.class)
	@JoinColumn(name = "MERCHANTID")
	private MerchantStore merchant;

	// @OneToMany(mappedBy = "order")
	// private Set<OrderAccount> orderAccounts = new HashSet<OrderAccount>();
	//
	// @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	// private Set<OrderProduct> orderProducts = new
	// LinkedHashSet<OrderProduct>();
	//
	// @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	// @OrderBy(clause = "sort_order asc")
	// private Set<OrderTotal> orderTotal = new LinkedHashSet<OrderTotal>();

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@OrderBy(clause = "ORDER_STATUS_HISTORY_ID asc")
	private Set<OrderStatusHistory> orderHistory = new LinkedHashSet<OrderStatusHistory>();

	public SalesOrder() {
	}

	@Column(name = "CUSTOMER_EMAIL_ADDRESS", length = 50, nullable = false)
	private String customerEmailAddress;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
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

	public Set<OrderStatusHistory> getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(Set<OrderStatusHistory> orderHistory) {
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

	public String getCustomerEmailAddress() {
		return customerEmailAddress;
	}

	public void setCustomerEmailAddress(String customerEmailAddress) {
		this.customerEmailAddress = customerEmailAddress;
	}

	public void setChannel(OrderChannel channel) {
		this.channel = channel;
	}

	public OrderChannel getChannel() {
		return channel;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
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

}