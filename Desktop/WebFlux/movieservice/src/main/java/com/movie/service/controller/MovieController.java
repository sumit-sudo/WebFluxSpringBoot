package com.movie.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.service.domain.Movie;
import com.movie.service.domain.Review;
import com.movie.service.integration.MoviesInfoClientIntegration;
import com.movie.service.integration.ReviewInfoClient;

import reactor.core.publisher.Mono;

/*
 * Basically MovieController API act as Webclient which will be responsible for interacting with different API
 * In this project, We will pass id of movie as an input and we will call two API ( movie-review-service 
 * and movies-info-service) in a non blocking way.
 */
@RestController
@RequestMapping("/v1/movie")
public class MovieController {
	
	@Autowired
	MoviesInfoClientIntegration moviesInfoClientIntegration;
	
	@Autowired
	ReviewInfoClient reviewInfoClient;
	
	@GetMapping("/{id}")
	public Mono<Movie> getMovieById(@PathVariable("id") String movieInfoId){
		
	return	moviesInfoClientIntegration.getMovieInfo(movieInfoId)
									.flatMap(movieInfo->
									{
									var collectListMono = reviewInfoClient.getMovieReview(movieInfoId).collectList();
									return collectListMono.map(reviews->new Movie(movieInfo,reviews));

									});

}
	
}
