package com.luv2code.springdemo.noxml;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.luv2code.springdemo")
@PropertySource("classpath:sport.properties")
@PropertySource("classpath:mylogger.properties")
public class SportConfig {
	/*
	 * To test out the functionality do following:
	 * 1. uncomment the below code
	 * 2. comment @ComponentScan in this class
	 * 3. comment @Autowired and @Qualifier in SwimCoach class
	 * 4. if the constructor in SwimCoach is commented out please uncomment it too
	 * 5. comment @Component in SadFortuneService class
	 * 6. optimize imports as needed
	 */
	
	/*
	// define bean for our sad fortune service
	@Bean
	public FortuneService sadFortuneService() {
		return new SadFortuneService();
	}
	
	// define bean for our swim coach and inject dependency
	@Bean
	public Coach swimCoach() {
		return new SwimCoach(sadFortuneService());
	}
	*/
	
	/*
	// define bean for our happy fortune service
	@Bean
	public FortuneService happyFortuneService() {
		return new HappyFortuneService();
	}
	
	// define bean for our swim coach AND inject dependency
	@Bean
	public Coach wrestlingCoach() {
		return new WrestlingCoach(happyFortuneService());
	}
	*/
	
}
