package com.movie.info.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.movie.info.dao.MovieInfoRepo;
import com.movie.info.exception.MovieNotSavedException;
import com.movie.info.model.MovieInfo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class MovieInfoController {
	
	@Autowired
	MovieInfoRepo movieInfoRepo;
	
	@PostMapping("/saveMovies")
	public Mono<MovieInfo> saveMovies(@RequestBody @Valid MovieInfo movieInfo) {
		System.out.println("hi");
		return movieInfoRepo.save(movieInfo).switchIfEmpty(Mono.error(new WebExchangeBindException(null,null)));
	}
	
	@GetMapping("/movielist")
	public Flux<MovieInfo> getMovies() {
		Flux<MovieInfo> movies = movieInfoRepo.findAll();
		
		return movies;
	}
	
	@GetMapping("/movielist/{movieInfoId}")
	public Mono<ResponseEntity<MovieInfo>> getMovie(@PathVariable("movieInfoId") String movieInfoId) {
		
	return  movieInfoRepo.findById(movieInfoId)
			.map(movie->{
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(movie);
			})
			.switchIfEmpty(Mono.error(new MovieNotSavedException("some thing went wrong")));
			/*
			 * If we are not using Global Exception Handler then we can do in the below way as well
			 * .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
			 */
	}

	
	
}
