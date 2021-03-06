package com.movie.service.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.movie.service.domain.MovieInfo;
import reactor.core.publisher.Mono;

@Component
public class MoviesInfoClientIntegration {

	@Autowired
	WebClient webClient;
	
	String movieInfoBaseUrl="http://localhost:8080/v1/movielist/";

	public Mono<MovieInfo> getMovieInfo(String movieId){
		
		var url=movieInfoBaseUrl.concat(movieId);
		System.out.println("movieInfoBaseUrl"+movieInfoBaseUrl);
		return webClient
				.get()
				.uri(url)
				.retrieve()
				.bodyToMono(MovieInfo.class)
				.log();
	}
	
	
	
}
