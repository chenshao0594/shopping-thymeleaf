package com.shoppay.core.payment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.shoppay.common.domain.BusinessDomain;
import com.shoppay.core.enumeration.PaymentEnvEnum;

@Entity
@Table(name = "payment_configuration")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PaymentConfiguration extends BusinessDomain<Long, PaymentConfiguration> implements Serializable {

	private static final long serialVersionUID = -7790700323284087735L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "details")
	private String configDetails;

	@Column(name = "type")
	private String type;

	@Column(name = "name")
	private String name;

	@Column(name = "version")
	private String version;

	@Column(name = "regions")
	private String regions;

	@Column(name = "image")
	private String image;

	@Column(name = "configuration")
	private String configuration;

	@Column(name = "payment_schema")
	private String scheme;

	@Column(name = "host", nullable = false)
	private String host;

	@Column(name = "port")
	private String port;

	@Column(name = "uri", nullable = false)
	private String uri;

	@Column(name = "config1")
	private String config1;
	// paypal checkout
	private String api;
	// paypal checkout
	private String username;
	// paypal checkout
	private String signature;

	@Enumerated(EnumType.STRING)
	@Column(name = "env_type")
	private PaymentEnvEnum envType;
	
	// beanstream
	private String password;
	// beanstream
	private String merchantId;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getConfigDetails() {
		return configDetails;
	}

	public void setConfigDetails(String configDetails) {
		this.configDetails = configDetails;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegions() {
		return regions;
	}

	public void setRegions(String regions) {
		this.regions = regions;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public PaymentEnvEnum getEnvType() {
		return envType;
	}

	public void setEnvType(PaymentEnvEnum envType) {
		this.envType = envType;
	}

	public String getConfig1() {
		return config1;
	}

	public void setConfig1(String config1) {
		this.config1 = config1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	

}
