package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		// print out method we are advising on
		String methodSignature = proceedingJoinPoint.getSignature().toShortString();
		logger.info("\n=====>>> Executing @Around on method: " + methodSignature);
		
		// get begin time stamp
		long begin = System.currentTimeMillis();
		
		// now, let's execute the method
		Object result = null;
		
		try {
			result = proceedingJoinPoint.proceed();
		} catch (Exception e) {
			
			//log the exception
			logger.warning(e.getMessage());
			
			// give user a custom message
			// result = "Major accident! But no worries, your private AOP helicopter is on the way!";
			
			// re-throw exception
			throw e;
			
		}
		
		// get end time stamp
		long end = System.currentTimeMillis();
		
		// compute duration and display it
		long duration = end - begin;
		logger.info("\n=====>>> Duration: " + duration / 1000.0 + " seconds");
		
		return result;
	}
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
		
		// print out which method we are advising on
		String methodSignature = joinPoint.getSignature().toShortString();
		logger.info("\n=====>>> Executing @After (finally) on method: " + methodSignature);
		
	}
	
	@AfterThrowing(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "exec")
	public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exec) {
		
		// print out which method we are advising on
		String methodSignature = joinPoint.getSignature().toShortString();
		logger.info("\n=====>>> Executing @AfterThrowing on method: " + methodSignature);
		
		// log the exception
		logger.info("\n=====>>> Exception is: " + exec);
		
	}
	
	// add a new advice for @AfterReturning on the findAccounts method
	@AfterReturning(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
		
		// print out which method we are advising on
		String methodSignature = joinPoint.getSignature().toShortString();
		logger.info("\n=====>>> Executing @AfterReturning on method: " + methodSignature);
		
		// print out the results of the method call
		logger.info("\n=====>>> Result is: " + result);
		
		// let's post-process the data... let's modify it :-)
		
		// convert the account names to upper case
		convertAccountNamesToUpperCase(result);
		
		logger.info("\n=====>>> Result is: " + result);
		
	}
	
	private void convertAccountNamesToUpperCase(List<Account> accounts) {
		
		// loop through accounts
		for (Account account : accounts) {
			
			// get the upper case version of name
			String upperCaseName = account.getName().toUpperCase();
			
			// update the name of the account
			account.setName(upperCaseName);
			
		}
		
	}

	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint joinPoint) {
		logger.info("\n=====>>> Executing @Before advice on method");
		
		// display the method signature
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		
		logger.info("Method: " + methodSignature);
		
		// display method arguments
		
		// get arguments
		Object[] args = joinPoint.getArgs();
		
		// loop through arguments
		for(Object arg : args) {
			
			logger.info(arg.toString());
			
			if (arg instanceof Account) {
				
				// downcast and print Account specific stuff
				Account account = (Account) arg;
				logger.info("Account name: " + account.getName());
				logger.info("Account level: " + account.getLevel());
				
			}
			
		}
	}
	
}
