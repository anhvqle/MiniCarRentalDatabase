package edu.depauw.csc480;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import edu.depauw.csc480.model.Rental;
import edu.depauw.csc480.model.Vehicle;
import edu.depauw.csc480.model.Customer;

/**
 * Simple client that inserts sample data then runs a query.
 * 
 * @author Anh Le
 */
public class Test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("rentaldb");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// Clear the tables
		em.createQuery("delete from Customer").executeUpdate();
		em.createQuery("delete from Vehicle").executeUpdate();
		em.createQuery("delete from Rental").executeUpdate();
		
		try {
			tx.commit();
		} catch (RollbackException ex) {
			ex.printStackTrace();
			tx.rollback();
		}

		tx = em.getTransaction();
		tx.begin();

		Vehicle minivan = new Vehicle(101, "Minivan", "Dogde", "Grand Caravan");
		Vehicle sedan = new Vehicle(202, "Full Size Car", "Ford", "Fusion");
		Vehicle SUV = new Vehicle(303, "Compact SUV", "Ford", "Eco Sport");
		Vehicle luxury = new Vehicle(404, "Luxury Car", "Cadillac", "XTS");
		Vehicle truck = new Vehicle(505, "Pickup", "Ford", "F150");

		em.persist(minivan);
		em.persist(sedan);
		em.persist(SUV);
		em.persist(luxury);
		em.persist(truck);

		//Customers' emails are set to null for now
		Customer Elizabeth = new Customer(123456789, "Elizabeth Carlson", 3122449894l, null);
		Customer Gabriella = new Customer(234567890, "Gabriella Caraterro", 8138164209l, null);
		Customer Amy = new Customer(345678901, "Amy Santiago", 8148066456l, null);
		Customer Jake = new Customer(456789012, "Jake Peralta", 3177771933l, null);
		Customer Michael = new Customer(567890123, "Michael Scott", 2248291569l, null);
		Customer Charles = new Customer(678901234, "Charles Boyle", 7654810678l, null);

		em.persist(Elizabeth);
		em.persist(Gabriella);
		em.persist(Amy);
		em.persist(Jake);
		em.persist(Michael);
		em.persist(Charles);


		em.persist(new Rental(minivan, 1234, "01/01/2020", "01/07/2020",Michael));
		em.persist(new Rental(luxury, 5678, "02/15/2020", "02/21/2020", Elizabeth));
		em.persist(new Rental(truck, 9123, "03/03/2020", "03/04/2020", Jake));
		em.persist(new Rental(sedan, 4567, "12/09/2019", "12/19/2019", Gabriella));
		em.persist(new Rental(SUV, 8901, "01/01/2020", "01/07/2020", Amy));
		em.persist(new Rental(luxury, 2345, "01/15/2020", "01/17/2020", Gabriella));
		em.persist(new Rental(luxury, 6789, "04/01/2020", "04/05/2020", Michael));

		try {
			tx.commit();
		} catch (RollbackException ex) {
			ex.printStackTrace();
			tx.rollback();
		}

		tx = em.getTransaction();
		tx.begin();

		// Now retrieve a table of previous rentals of a luxury vehicle;
		Collection<Rental> rentalRecords = luxury.getRentals();
		System.out.println("Query 1:");
		for (Rental rec : rentalRecords) {
			System.out.println(rec);
		}
		
		System.out.println();
		//Given a customer's name, retrieve a lists of all of
		//his or her previous rental record
		Collection<Rental> gabriellaRentals = Gabriella.getRentals();
		System.out.println("Query 2: Gabriella's Rental Records");
		for(Rental rec : gabriellaRentals) {
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
