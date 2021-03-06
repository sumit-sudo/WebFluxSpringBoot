package com.movie.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.review.dao.MovieReviewRepo;
import com.movie.review.model.Review;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class MovieReviewController {
	
	@Autowired
	MovieReviewRepo movieReviewRepo;

	@GetMapping("/allreview")
	public Flux<Review> getReviews() {
		
		return movieReviewRepo.findAll();
	}
	@GetMapping("/review/{reviewId}")
	public Mono<Review> getReviews(@PathVariable("reviewId") String reviewId) {
		
		return movieReviewRepo.findById(reviewId);
	}
	
	@PostMapping("/savereview")
public Mono<Review> saveReview(@RequestBody Review review) {
		
		return movieReviewRepo.save(review);	
	
}
}
