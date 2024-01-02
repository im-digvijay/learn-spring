package com.luv2code.springdemo;

public class GolfCoach implements Coach {
	
	// define a private field for the dependency
	private FortuneService fortuneService;
	
	// define a constructor for the dependency injection
	public GolfCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	public GolfCoach() {
	}

	@Override
	public String getDailyWorkout() {
		return "Practice your putting skills for 2 hours today";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
