package com.smartshop.core.order;

import java.io.Serializable;
import java.math.BigDecimal;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.data.elasticsearch.annotations.Document;

import com.smartshop.common.domain.BusinessDomain;

@Entity
@Table(name = "SALES_ORDER_total")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "salesordertotal")
public class SalesOrderTotal extends BusinessDomain<Long, SalesOrderTotal> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CODE", nullable = false)
	private String code;// SHIPPING, TAX

	@Column(name = "TITLE", nullable = true)
	private String title;

	@Column(name = "TEXT", nullable = true)
	@Type(type = "org.hibernate.type.MaterializedClobType")
	private String text;

	@Column(name = "VALUE", precision = 15, scale = 4, nullable = false)
	private BigDecimal value;

	@Column(name = "MODULE", length = 60, nullable = true)
	private String module;

	/*
	 * @Column(name = "ORDER_VALUE_TYPE")
	 *
	 * @Enumerated(value = EnumType.STRING) private OrderValueType
	 * orderValueType = OrderValueType.ONE_TIME;
	 */

	@Column(name = "ORDER_TOTAL_TYPE")
	@Enumerated(value = EnumType.STRING)
	private SalesOrderTotalType orderTotalType = null;

	@Column(name = "SORT_ORDER", nullable = false)
	private int sortOrder;

	@ManyToOne(targetEntity = SalesOrder.class)
	@JoinColumn(name = "ORDER_ID", nullable = false)
	private SalesOrder order;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public SalesOrder getOrder() {
		return order;
	}

	public void setOrder(SalesOrder order) {
		this.order = order;
	}

	public SalesOrderTotalType getOrderTotalType() {
		return orderTotalType;
	}

	public void setOrderTotalType(SalesOrderTotalType orderTotalType) {
		this.orderTotalType = orderTotalType;
	}

}
