package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class OptionThreeDemo {

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
			
			// get the instructor from db
			int id = 1;
			Instructor instructor = session.get(Instructor.class, id);
			
			System.out.println("luv2code: Instructor: " + instructor);
			
			// commit the transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			
			System.out.println("\nluv2code: The session is now closed!\n");
			
			// THIS HAPPENS SOMEWHERE ELSE / LATER IN THE PROGRAM
			// YOU NEED TO GET A NEW SESSION
			
			System.out.println("\nluv2code: Opening a NEW session\n");
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			Query<Course> query = session.createQuery("select c from Course c "
													+ "where c.instructor.id=:instructorId", Course.class);
			
			query.setParameter("instructorId", id);
			
			List<Course> courses = query.getResultList();
			
			System.out.println("luv2code: courses: " + courses);
			
			// now assign to instructor object in memory
			instructor.setCourses(courses);
			
			// get courses for the instructor
			System.out.println("luv2code: Courses: " + instructor.getCourses());
			
			session.getTransaction().commit();
			
			System.out.println("luv2code: Done!");
			
		} finally {
			// add clean up code
			session.close();
			factory.close();
		}
		
	}

}
