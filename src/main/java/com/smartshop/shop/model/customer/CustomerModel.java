package com.smartshop.shop.model.customer;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class CustomerModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8663381645405409678L;

	@Email(message = "{messages.invalid.email}")
	@NotEmpty(message = "{NotEmpty.customer.emailAddress}")
	private String emailAddress;

	private String gender;

	private String firstName;

	private String lastName;

	private String encodedPassword = null;

	private String clearPassword = null;

	private String password = null;

	private String checkPassword;

	private String userName;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEncodedPassword() {
		return encodedPassword;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}

	public String getClearPassword() {
		return clearPassword;
	}

	public void setClearPassword(String clearPassword) {
		this.clearPassword = clearPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckPassword() {
		return checkPassword;
	}

	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

}
