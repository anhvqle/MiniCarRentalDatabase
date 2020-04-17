package edu.depauw.csc480.model;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Model object for a row in the Department table.
 * Accesses the underlying database through JPA.
 * 
 * @author Anh Le
 */
@Entity
public class Vehicle {
	@Id
	private int VehicleID;
	@Basic
	private String type;
	@Basic
  	private String make;
	@Basic
	private String model;
  
  	@OneToMany(mappedBy="vehicle")
  	private Collection<Rental> rentals;
  
  	public Vehicle(int VehicleID, String type, String make, String model) {
  		this.VehicleID = VehicleID;
  		this.type = type;
  		this.make = make;
  		this.model = model;
  	}
  	
  	@Override
  	public String toString() {
  		return "VehicleID: " + VehicleID  + "\nVehicle type: " + type 
  				+ "\nModel: " + model + "\nMake: " + make; 
  	}

  	public int getVehicleID() {
		return VehicleID;
	}
	
	public String getType() {
		return type;
	}
	
	public String getModel() {
		return model;
	}
	
	public String getMake() {
		return make;
	}
	
	public Collection<Rental> getRentals() {
		return rentals;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public void setRentals(Collection<Rental> rentals) {
		this.rentals = rentals;
	}
}
