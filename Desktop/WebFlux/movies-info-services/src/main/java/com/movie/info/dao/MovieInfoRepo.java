package com.movie.info.dao;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.movie.info.model.MovieInfo;

@Repository
public interface MovieInfoRepo extends ReactiveCassandraRepository<MovieInfo, String> {
/*
 * Basic Understanding of Flux and Mono and stream Data in the class FluxAndMonoController
 * Perform the Repository layer unit testing
 * Perform the Controller layer unit testing (Get and Post)
 * Perform Integration Testing for post request
 * Check the return type of each end point, its either mono or flux
 * Successfully Handle WebExchangeBindException exception when exception throws from @NotBlank or from
 * java validation dependency
 * 
 * Global Exception Handle for any bad request from Database
 * 
 * 	If we use logging.level.root=DEBUG then we will see there are two thread
 * 		http thread which is responsible for carrying request and response from client to server 
 * 		Another server thraed which perform database activities like save(), findById() etc
 * 
 * 
 * 
 * 
 */
}
