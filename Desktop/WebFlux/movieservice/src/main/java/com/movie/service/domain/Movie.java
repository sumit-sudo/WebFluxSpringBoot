package com.movie.service.domain;

import java.util.List;

public class Movie {
	
	MovieInfo movieInfo;
	List<Review> reviewList;
	public Movie(MovieInfo movieInfo, List<Review> reviewList) {
		super();
		this.movieInfo = movieInfo;
		this.reviewList = reviewList;
	}

}
