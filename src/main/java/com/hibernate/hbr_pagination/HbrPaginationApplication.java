package com.hibernate.hbr_pagination;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HbrPaginationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HbrPaginationApplication.class, args);
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		
		Session session = factory.openSession();
		
		//createInitialDB(session);
		
		Query query = session.createQuery("from Employee");
		
		//Pagination started
		query.setFirstResult(12);
		query.setMaxResults(9);
		//Pagination ended
		
		List<Employee> employees = query.getResultList();
		
		employees.forEach(System.out::println);
		
		session.close();
		factory.close();

	}

	private static void createInitialDB(Session session) {
		
		Transaction transaction = session.beginTransaction();
		
		for (int i = 0; i < 50; i++) {
			session.save(new Employee("Employee" + i));
		}
		
		transaction.commit();
	}

}
