package com.mfp.api.entity;

import javax.persistence.Column; 
import javax.persistence.Id;

public class BaseEntity {
	
	@Id
	@Column(name = "Id",nullable = false)
	private String id;

	@Column(name = "Name",nullable = false)
	private String name;

	@Column(name = "EmailId",nullable = false)
	private String emailId;

	@Column(name = "MobileNo",nullable = false)
	private String mobileNo;

	@Column(name = "Street",nullable = false)
	private String street;

	@Column(name = "City",nullable = false)
	private String city;

	@Column(name = "Pincode")
	private String pincode;
	
	 public BaseEntity() {
		// TODO Auto-generated constructor stub
	}

	public BaseEntity(String id, String name, String emailId, String mobileNo, String street, String city,
			String pincode) {
		super();
		this.id = id;
		this.name = name;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.street = street;
		this.city = city;
		this.pincode = pincode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	 
	 

}
