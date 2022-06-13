package com.movie.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.info.dao.MovieInfoRepo;
import com.movie.info.exception.MovieNotSavedException;
import com.movie.info.model.MovieInfo;

import reactor.core.publisher.Mono;

@Service
public class MovieService {

	@Autowired
	MovieInfoRepo movieInfoRepo;
	
	public Mono<MovieInfo> saveMovie(MovieInfo movieInfo) {
		return movieInfoRepo.save(movieInfo).switchIfEmpty(
				Mono.error(new MovieNotSavedException("some thing went wrong")));
	}
	
	
}
