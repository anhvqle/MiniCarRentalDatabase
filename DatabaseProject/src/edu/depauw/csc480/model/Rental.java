package edu.depauw.csc480.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Model object for a row in the Rental table. Accesses the underlying database
 * through JPA.
 * 
 * @author Anh Le
 */
@Entity
public class Rental {
	@Id
	private int rentalID;
	@Basic
	private String rentalDate;
	@Basic
	private String returnDate;

	@ManyToOne
	private Customer customer;
	@ManyToOne
	private Vehicle vehicle;

	// No-arg constructor for JPA
	public Rental() {
	}

	public Rental(Vehicle vehicle, int rentalID, String rentalDate, String returnDate, Customer customer) {
		this.vehicle = vehicle;
		this.rentalID = rentalID;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return vehicle + " (" + rentalID + "): " + rentalDate + " - " + returnDate + ", " + customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public int getRentalID() {
		return rentalID;
	}

	public String getRentalDate() {
		return rentalDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
