package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Course.class)
										.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start a transaction
			session.beginTransaction();
			
			// option 2: Hibernate query with HQL
			
			// get the instructor from db
			int id = 1;
			
			Query<Instructor> query = session.createQuery("select i from Instructor i "
														+ "JOIN FETCH i.courses "
														+ "where i.id=:instructorId",
														Instructor.class);
			
			// set parameter on query
			query.setParameter("instructorId", id);
			
			// execute query and set instructor
			Instructor instructor = query.getSingleResult();
			
			System.out.println("luv2code: Instructor: " + instructor);
			
			// commit the transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			
			System.out.println("\nluv2code: The session is now closed!\n");
			
			// get courses for the instructor
			System.out.println("luv2code: Courses: " + instructor.getCourses());
			
			System.out.println("luv2code: Done!");
			
		} finally {
			// add clean up code
			session.close();
			factory.close();
		}
		
	}

}
