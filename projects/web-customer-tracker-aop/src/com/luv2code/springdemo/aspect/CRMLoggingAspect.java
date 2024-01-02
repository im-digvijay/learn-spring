package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	// setup logger
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// setup point cut declarations
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDAOPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow() {}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		
		// display the method we are calling
		String methodSignature = joinPoint.getSignature().toShortString();
		logger.info("=====>> in @Before: calling method: " + methodSignature);
		
		// display the argument to the method
		
		// get the arguments
		Object[] arguments = joinPoint.getArgs();
		
		// loop thru and display the arguments
		for (Object argument : arguments) {
			logger.info("=====>> argument: " + argument);
		}
		
	}
	
	// add @AfterReturning advice
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		
		// display method we are returning from
		String methodSignature = joinPoint.getSignature().toShortString();
		logger.info("=====>> in @AfterReturning: calling method: " + methodSignature);
		
		// display data returned
		logger.info("=====>> result: " + result);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
