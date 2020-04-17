package edu.depauw.csc480;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import edu.depauw.csc480.model.Rental;
import edu.depauw.csc480.model.Vehicle;
import edu.depauw.csc480.model.Customer;

/**
 * Simple client that retrieves data from an already created database. Running
 * this after Test will check that the same data may be retrieved from the
 * database and not just from the in-memory cache.
 * 
 * @author Anh Le
 */
public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("rentaldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String query = "select v from Vehicle v where v.type='Luxury Car'";
		TypedQuery<Vehicle> q = em.createQuery(query, Vehicle.class);
		Vehicle luxury = q.getSingleResult();
		
		String query2 = "select c from Customer c where c.customerID=123456789";
		TypedQuery<Customer> q2 = em.createQuery(query2, Customer.class);
		Customer cust = q2.getSingleResult();

		// Now retrieve a table of previous rentals of a luxury vehicle;
		System.out.println("Query 1");
		Collection<Rental> rentalRecords = luxury.getRentals();
		for (Rental rec : rentalRecords) {
			System.out.println(rec);
		}
		System.out.println();
		System.out.println("Query 2");
		//Now retrieve a list of customer with ID 123456789 
		//previous rental records
		Collection<Rental> LizRentals = cust.getRentals();
		for(Rental rec : LizRentals) {
			System.out.println(rec);
		}

		try {
			tx.commit();
		} catch (RollbackException ex) {
			ex.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}

		System.out.println("Done");
	}

}
