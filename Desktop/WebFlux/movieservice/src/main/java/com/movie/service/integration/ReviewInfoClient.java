package com.movie.service.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.movie.service.domain.Review;

import reactor.core.publisher.Flux;

@Component
public class ReviewInfoClient {
	@Autowired
	WebClient webClient;
	
	String reviewServiceBaseUrl="http://localhost:8081/v1/review/";
	
public Flux<Review> getMovieReview(String movieId){
		
		var url=reviewServiceBaseUrl.concat(movieId);
		return webClient
						.get()
						.uri(url)
						.retrieve()
						.bodyToFlux(Review.class)
						.log();
		
	}
}
