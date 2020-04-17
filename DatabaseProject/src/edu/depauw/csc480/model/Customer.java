package edu.depauw.csc480.model;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Model object for a row in the Customer table. Accesses the underlying
 * database through JPA.
 * 
 * @author Anh Le
 */
@Entity
public class Customer {
	@Id
	private int customerID;
	@Basic
	private String fullName;
	@Basic
	private long phoneNumber;
	@Basic
	private String email;

	@OneToMany(mappedBy = "customer")
	private Collection<Rental> rentals;

	// No-arg constructor for JPA
	public Customer() {
	}

	public Customer(int customerID, String fullName, long phoneNumber, String email) {
		this.customerID = customerID;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	@Override
	public String toString() {
		return fullName + " (ID: " + customerID + " - #:" + phoneNumber + ")";
	}

	public int getCustomerID() {
		return customerID;
	}

	public String getFullName() {
		return fullName;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Rental> getRentals() {
		return rentals;
	}
}
