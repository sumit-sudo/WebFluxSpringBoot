package com.movie.review.dao;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import com.movie.review.model.Review;

public interface MovieReviewRepo extends ReactiveCassandraRepository<Review, String>{

}
