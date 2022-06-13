package com.kafka.producer.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public enum LibraryEventType {

	NEW,
	UPDATE
	/*
	 * ResponseEntity<?>
	 * return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PLease enter the valid Libary Id");
	 * @Valid from javax Validation to perform validation on field rather than on data base
	 * Integration Testing, 
	 * Unit Testing ( returning void from when())
	 * How to use ENUM
	 * Proper way of joining table
	 * 
	 * NOTE: there is no database dependency added to the consumer microservices as we are following
	 *  below data transfer
	 *  
	 *  Consumer ----> Brokers ( Servers -> Topic -> partitions ) ----> consumers ---> database
	 */
}
