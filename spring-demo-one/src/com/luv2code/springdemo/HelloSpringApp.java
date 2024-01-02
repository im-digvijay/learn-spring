package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {
	
	public static void main(String[] args) {
		
		// load the spring configuration file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// retrieve the bean from spring container
		Coach coach = context.getBean("myCoach", Coach.class);
		
		// call methods on the bean
		System.out.println(coach.getDailyWorkout());
		
		// let's call our new method for fortunes
		System.out.println(coach.getDailyFortune());
		
		// close the context
		context.close();
		
	}

}
