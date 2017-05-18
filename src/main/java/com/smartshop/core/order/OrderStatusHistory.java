package com.smartshop.core.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import com.smartshop.core.order.enumeration.OrderStatus;
import com.smartshop.domain.common.BusinessDomain;

@Entity
@Table(name = "Order_Status_History")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "orderStatusHistory")
public class OrderStatusHistory extends BusinessDomain<Long, SalesOrder> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5678516452269750976L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = SalesOrder.class)
	@JoinColumn(name = "ORDER_ID", nullable = false)
	private SalesOrder order;

	@Enumerated(value = EnumType.STRING)
	private OrderStatus status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_ADDED", nullable = false)
	private Date dateAdded;

	@Column(name = "CUSTOMER_NOTIFIED")
	private java.lang.Integer customerNotified;

	@Column(name = "COMMENTS")
	private String comments;

	public OrderStatusHistory() {
	}

	@Override
	public Long getId() {
		return id;
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

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public java.lang.Integer getCustomerNotified() {
		return customerNotified;
	}

	public void setCustomerNotified(java.lang.Integer customerNotified) {
		this.customerNotified = customerNotified;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

}