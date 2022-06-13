package com.reactive.programming.practice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.reactive.programming.practice.service.FluxAndMonoGeneratorService;

import reactor.test.StepVerifier;

@SpringBootTest
class ReactiveProgrammingUsingReactorApplicationTests {

	FluxAndMonoGeneratorService obj=new FluxAndMonoGeneratorService();
	
	@Test
	void contextLoads() {
		
		var nameFlux=obj.nameFlux();
		
		StepVerifier.create(nameFlux)
					//.expectNext("SUMIT","AMIT","RAHUL")// content verification
					.expectNextCount(3)// how many elements we are expecting
					.verifyComplete();
					
		
	}

}
