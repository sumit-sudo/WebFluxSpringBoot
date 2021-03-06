package com.movie.info;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.test.StepVerifier;


@WebFluxTest
@AutoConfigureWebTestClient
class MoviesInfoServicesApplicationTests {

	/*
	 * Performing Unit Testing
	 * 
	 * 
	 */
	@Autowired
	WebTestClient webTestClient;
	@Test
	void test_approach1() {
		// By Checking total number i.e size of return
		webTestClient
		.get()
		.uri("/api/mono")
		.exchange()
		.expectStatus()
		.is2xxSuccessful()
		.expectBodyList(String.class)
		.hasSize(1);
	}

	@Test
	void test_approach2() {
		// By Checking actual body content
		var flux=webTestClient
		.get()
		.uri("/api/flux")
		.exchange()
		.expectStatus()
		.is2xxSuccessful()
		.returnResult(Integer.class)
		.getResponseBody();
		
		StepVerifier.create(flux)
					.expectNext(1,2,3,4)
					.verifyComplete();
	}
	
	@Test
	void stream() {
		// By Checking actual body content
		var flux=webTestClient
		.get()
		.uri("/api/flux")
		.exchange()
		.expectStatus()
		.is2xxSuccessful()
		.returnResult(Long.class)
		.getResponseBody();
		
		StepVerifier.create(flux)
					.expectNext(1L,2L,3L,4L,5L)
					.thenCancel()
				.verify();
	}

}
