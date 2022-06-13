package com.kafka.consumer.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public enum LibraryEventType {

	NEW,
	UPDATE
	/*
	 * ResponseEntity<?>
	 * return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PLease enter the valid Libary Id");
	 * Proper way of joining tables
	 * @Valid from javax Validation to perform validation on field rather than on data base
	 * Proper use of Object Mapper class, JASON<->String, JSON <-> class type object ( LibraryEvent.class)
	 * Integration Testing, Unit Testing
	 * How to use ENUM
	 * 
	 * 
	 */
}
