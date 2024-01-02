package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {
	
	public static void main(String[] args) {
		
		// load spring config file
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// retrieve bean from spring container
		Coach alphaCoach = context.getBean("tennisCoach", Coach.class);
		
		Coach betaCoach = context.getBean("tennisCoach", Coach.class);
		
		// check if they are same
		boolean result = (alphaCoach == betaCoach);
		
		// print out the result
		System.out.println("\nPointing to the same object: " + result);
		
		System.out.println("\nMemory location for alphaCoach: " + alphaCoach);
		
		System.out.println("\nMemory location for betaCoach: " + betaCoach);
		
		// close the context
		context.close();
		
	}

}
