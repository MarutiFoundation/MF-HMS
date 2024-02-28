package com.mfp.api.entity;

import java.sql.Date; 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Patient {
	@Id
	@Column(name = "Id",nullable = false,unique = true)
	private String id;

	@Column(name = "FirstName",nullable = false)
	@NotBlank(message = "FirstName Is Required")
	private String firstName;

	@Column(name = "LastName",nullable = false)
	@NotBlank(message = "LastName Is Required")
	private String lastName;

	@Column(name = "EmailId",nullable = false)
	@NotBlank(message = "EmailId Is Required")
	private String emailId;

	@Column(name = "Age",nullable = false)
	@NotBlank(message = "Age Is Required")
	private int age;

	@Column(name = "BloodGroup")
	private String bloodGroup;

	@Column(name = "MobileNo",nullable = false)
	@NotBlank(message = "MobileNo Is Required")
	private String mobileNo;

	@Column(name = "Street")
	private String street;

	@Column(name = "City",nullable = false)
	@NotBlank(message = "City Is Required")
	private String city;

	@Column(name = "Pincode",nullable = false)
	@NotBlank(message = "Pincode Is Required")
	private String pincode;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "RegisterDate")
	private Date registerDate;

	public Patient() {
		// TODO Auto-generated constructor stub
	}

	public Patient(String id, String firstName, String lastName, String emailId, int age, String bloodGroup,
			String mobileNo, String street, String city, String pincode, Date registerDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.age = age;
		this.bloodGroup = bloodGroup;
		this.mobileNo = mobileNo;
		this.street = street;
		this.city = city;
		this.pincode = pincode;
		this.registerDate = registerDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
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

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	
	

}
