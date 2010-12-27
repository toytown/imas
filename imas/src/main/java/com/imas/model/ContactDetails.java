package com.imas.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "OAS_CONTACT_DETAILS")
public class ContactDetails implements Serializable {

	private static final long serialVersionUID = 3623565387080941741L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "contact_id")
	private Long id;
	
	@ManyToOne(cascade=CascadeType.ALL)	
	@JoinColumn(name="user_id")
	private User customer;
	
	@Column(name = "email", length = 100)
	private String email;
	
	@Column(name = "fax", length = 100)
	private String fax;
	
	@Column(name = "mobile", length = 100)
	private String mobile;

	@Column(name = "phone1", length = 100)
	private String phone1;
	
	@Column(name = "phone2", length = 100)
	private String phone2;	
	
	@Column(name = "city", length = 100)
	private String city;
	
	@Column(name = "zip", length = 10)
	private String zip;	
	
	@Column(name = "street", length = 100)
	private String street;
	
	@Column(name = "house_no", length = 100)
	private String houseNumber;

	@Column(name = "floor", length = 100)
	private String floor;
	
	@Column(name = "homepage", length = 100)
	private String homePageURL; 
	
	@Column(name = "contact_status", length = 5)
	private Short contactStatus;
	
	@Column(name = "contact_type", length = 100)
	private Short contactType;

	
	//private PostalCode postalCode;	
	
	public Long getId() {
		return id;
	}

	public void setId(Long contactId) {
		this.id = contactId;
	}

	public User getUser() {
		return customer;
	}

	public void setUser(User customer) {
		this.customer = customer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getHomePageURL() {
		return homePageURL;
	}

	public void setHomePageURL(String homePageURL) {
		this.homePageURL = homePageURL;
	}

	public Short getContactStatus() {
		return contactStatus;
	}

	public void setContactStatus(Short contactStatus) {
		this.contactStatus = contactStatus;
	}

	public Short getContactType() {
		return contactType;
	}

	public void setContactType(Short contactType) {
		this.contactType = contactType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((contactStatus == null) ? 0 : contactStatus.hashCode());
		result = prime * result + ((contactType == null) ? 0 : contactType.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result + ((floor == null) ? 0 : floor.hashCode());
		result = prime * result + ((homePageURL == null) ? 0 : homePageURL.hashCode());
		result = prime * result + ((houseNumber == null) ? 0 : houseNumber.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((phone1 == null) ? 0 : phone1.hashCode());
		result = prime * result + ((phone2 == null) ? 0 : phone2.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactDetails other = (ContactDetails) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (contactStatus == null) {
			if (other.contactStatus != null)
				return false;
		} else if (!contactStatus.equals(other.contactStatus))
			return false;
		if (contactType == null) {
			if (other.contactType != null)
				return false;
		} else if (!contactType.equals(other.contactType))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (floor == null) {
			if (other.floor != null)
				return false;
		} else if (!floor.equals(other.floor))
			return false;
		if (homePageURL == null) {
			if (other.homePageURL != null)
				return false;
		} else if (!homePageURL.equals(other.homePageURL))
			return false;
		if (houseNumber == null) {
			if (other.houseNumber != null)
				return false;
		} else if (!houseNumber.equals(other.houseNumber))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (phone1 == null) {
			if (other.phone1 != null)
				return false;
		} else if (!phone1.equals(other.phone1))
			return false;
		if (phone2 == null) {
			if (other.phone2 != null)
				return false;
		} else if (!phone2.equals(other.phone2))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}

	/*
	public PostalCode getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(PostalCode postalCode) {
		this.postalCode = postalCode;
	}*/


}
