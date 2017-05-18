package com.smartshop.domain;

import java.io.Serializable;
import java.time.LocalDate;

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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.elasticsearch.annotations.Document;

import com.smartshop.core.common.Billing;
import com.smartshop.core.common.Delivery;
import com.smartshop.core.enumeration.Gender;
import com.smartshop.domain.common.BusinessDomain;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "customer")
public class Customer extends BusinessDomain<Long, Customer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "LAST_NAME", length = 64, nullable = false)
	private String lastName;

	@NotEmpty
	@Column(name = "FIRST_NAME", length = 64, nullable = false)
	private String firstName;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;

	@Column(name = "anonymous")
	private Boolean anonymous = true;

	@Column(name = "company")
	private String company;

	@Column(name = "nick")
	private String nick;

	@Column(name = "remark")
	private String remark;

	@Email
	@Column(name = "email_address", nullable = false)
	private String emailAddress;

	@Column(name = "CUSTOMER_PASSWORD")
	private String password;

	@ManyToOne
	private MerchantStore merchantStore;

	@ManyToOne
	@JoinColumn(name = "authority_name", updatable = false)
	private Authority authority;

	@Valid
	@Embedded
	private Billing billing = new Billing();

	@Embedded
	private Delivery delivery = new Delivery();

	@Transient
	private String showCustomerStateList;

	@Transient
	private String showBillingStateList;

	@Transient
	private String showDeliveryStateList;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public Customer dateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public Customer gender(Gender gender) {
		this.gender = gender;
		return this;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Boolean isAnonymous() {
		return anonymous;
	}

	public Customer anonymous(Boolean anonymous) {
		this.anonymous = anonymous;
		return this;
	}

	public void setAnonymous(Boolean anonymous) {
		this.anonymous = anonymous;
	}

	public String getCompany() {
		return company;
	}

	public Customer company(String company) {
		this.company = company;
		return this;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getNick() {
		return nick;
	}

	public Customer nick(String nick) {
		this.nick = nick;
		return this;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public Customer emailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
		return this;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public Customer password(String password) {
		this.password = password;
		return this;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public MerchantStore getMerchantStore() {
		return merchantStore;
	}

	public void setMerchantStore(MerchantStore merchantStore) {
		this.merchantStore = merchantStore;
	}

	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public String getShowCustomerStateList() {
		return showCustomerStateList;
	}

	public void setShowCustomerStateList(String showCustomerStateList) {
		this.showCustomerStateList = showCustomerStateList;
	}

	public String getShowBillingStateList() {
		return showBillingStateList;
	}

	public void setShowBillingStateList(String showBillingStateList) {
		this.showBillingStateList = showBillingStateList;
	}

	public String getShowDeliveryStateList() {
		return showDeliveryStateList;
	}

	public void setShowDeliveryStateList(String showDeliveryStateList) {
		this.showDeliveryStateList = showDeliveryStateList;
	}

	public Boolean getAnonymous() {
		return anonymous;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

}