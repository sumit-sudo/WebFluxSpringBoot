package com.movie.service.domain;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;




public class MovieInfo {

	@JsonIgnore 
	private String movieInfoId;
	private String name;
	private Integer year;
	private List< String> cast;
	private LocalDate releaseDate;
	
	
	public MovieInfo(String movieInfoId, String name, Integer yer, List<String> cast, LocalDate releaseDate) {
		super();
		this.movieInfoId = movieInfoId;
		this.name = name;
		this.year = yer;
		this.cast = cast;
		this.releaseDate = releaseDate;
	}
	
	public MovieInfo() {
		super();
	}

	public String getMovieInfoId() {
		return movieInfoId;
	}
	public void setMovieInfoId(String movieInfoId) {
		this.movieInfoId = movieInfoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getYer() {
		return year;
	}
	public void setYer(Integer yer) {
		this.year = yer;
	}
	public List<String> getCast() {
		return cast;
	}
	public void setCast(List<String> cast) {
		this.cast = cast;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	
}
