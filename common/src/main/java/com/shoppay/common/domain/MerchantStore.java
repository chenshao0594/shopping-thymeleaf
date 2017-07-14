package com.shoppay.common.domain;

import java.io.Serializable;
import java.util.Locale;

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
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.shoppay.common.constants.BusinessConstants;
import com.shoppay.common.enumeration.MeasureUnit;
import com.shoppay.core.common.Country;
import com.shoppay.core.common.Zone;

/**
 * A MerchantStore.
 */
@Entity
@Table(name = "merchant_store")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MerchantStore extends BusinessDomain<Long, MerchantStore> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8819502867751478315L;

	public final static String DEFAULT_STORE = "DEFAULT";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "NAME", nullable = false, length = 100)
	private String name;

	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z0-9_]*$")
	@Column(name = "CODE", nullable = false, unique = true, length = 100)
	private String code;

	@NotEmpty
	@Column(name = "PHONE", length = 50)
	private String phone;

	@Column(name = "ADDRESS")
	private String address;

	@NotEmpty
	@Column(name = "CITY", length = 100)
	private String city;

	@NotEmpty
	@Column(name = "POSTAL_CODE", length = 15)
	private String postalcode;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Country.class)
	@JoinColumn(name = "COUNTRY_ID", nullable = false, updatable = true)
	private Country country;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Zone.class)
	@JoinColumn(name = "ZONE_ID", nullable = true, updatable = true)
	private Zone zone;

	@Column(name = "STATE_PROVINCE", length = 100)
	private String stateProvince;

	@Enumerated(EnumType.STRING)
	@Column(name = "WEIGHT_UNIT", length = 5)
	private MeasureUnit weightUnit;

	@Enumerated(EnumType.STRING)
	@Column(name = "SIZE_UNIT", length = 5)
	private MeasureUnit sizeUnit;

	@Column(name = "INVOICE_TEMPLATE", length = 25)
	private String invoiceTemplate;

	@Column(name = "DOMAIN_NAME", length = 80)
	private String domainName;

	@Column(name = "CONTINUE_SHOPPING_URL", length = 150)
	private String continueShoppingURL;

	@Email
	@NotEmpty
	@Column(name = "EMAIL_ADDRESS", length = 60, nullable = false)
	private String emailAddress;

	@Column(name = "LOGO", length = 100)
	private String Logo;

	// @ManyToOne(targetEntity = Currency.class)
	// @JoinColumn(name = "CURRENCY_ID", nullable=false)
	// private Currency currency;

	@Transient
	private java.util.Currency currency = BusinessConstants.DEFAULT_CURRENCY;
	@Transient
	private Locale locale = Locale.US;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public MeasureUnit getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(MeasureUnit weightUnit) {
		this.weightUnit = weightUnit;
	}

	public MeasureUnit getSizeUnit() {
		return sizeUnit;
	}

	public void setSizeUnit(MeasureUnit sizeUnit) {
		this.sizeUnit = sizeUnit;
	}

	public String getInvoiceTemplate() {
		return invoiceTemplate;
	}

	public void setInvoiceTemplate(String invoiceTemplate) {
		this.invoiceTemplate = invoiceTemplate;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getContinueShoppingURL() {
		return continueShoppingURL;
	}

	public void setContinueShoppingURL(String continueShoppingURL) {
		this.continueShoppingURL = continueShoppingURL;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getLogo() {
		return Logo;
	}

	public void setLogo(String logo) {
		Logo = logo;
	}

	public java.util.Currency getCurrency() {
		return currency;
	}

	public void setCurrency(java.util.Currency currency) {
		this.currency = currency;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@Override
	public String toString() {
		return "MerchantStore [id=" + id + ", name=" + name + ", code=" + code + ", phone=" + phone + ", address="
				+ address + ", city=" + city + ", postalcode=" + postalcode + ", country=" + country + ", zone=" + zone
				+ ", stateProvince=" + stateProvince + ", weightUnit=" + weightUnit + ", sizeUnit=" + sizeUnit
				+ ", invoiceTemplate=" + invoiceTemplate + ", domainName=" + domainName + ", continueShoppingURL="
				+ continueShoppingURL + ", emailAddress=" + emailAddress + ", Logo=" + Logo + ", currency=" + currency
				+ ", locale=" + locale + "]";
	}

}