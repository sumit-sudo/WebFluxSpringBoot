package com.kafka.producer.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.producer.domain.LibraryEvent;
import com.kafka.producer.domain.LibraryEventType;
import com.kafka.producer.producer.LibraryEventProducer;

@RestController
@RequestMapping("/api")
public class LibraryEventController {

	@Autowired
	LibraryEventProducer producer;
	Logger log=LoggerFactory.getLogger(LibraryEventController.class);
	
	@PostMapping("/v1/libraryevent")
	// Here with the help of @Valid, from javax.validation, we are adding validation for payload values.
	// @NotNull, @NotEmpty @Valid makes this possible in Book.class and LibraryEvent.class
	public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody @Valid LibraryEvent libraryevent) throws JsonProcessingException
	{
		libraryevent.setLibraryEventType(LibraryEventType.NEW);
		// APPROACH-1 ASYNCHRONOUS
		/*
		 * log.info("Before calling Asynchronous method sendLibraryEvent");
		 * producer.sendLibraryEvent(libraryevent);
		 * log.info("After calling Asynchronous method sendLibraryEvent");
		 */
		//CHECKING ASYNCHRONOUS FUNCTIONALITY, as After calling Asynchronous method sendLibraryEvent 
		//get print before message from producer class.
		
		
		/* APPROACH-2 SYNCHRONOUS
		 * log.info("BEFORE"); SendResult<Integer, String> sendLibraryEventSynchronous =
		 * producer.sendLibraryEventSynchronous(libraryevent);
		 * log.info(sendLibraryEventSynchronous.toString()); 
		 * log.info("AFTER");
		 */
		
		 //APPROACH-3 , ASYNCHRONOUS with Custom Topic Name
		 log.info("BEFORE"); 
		 producer.sendLibraryEventApproach2(libraryevent);
		 log.info("AFTER");

		
		return ResponseEntity.status(HttpStatus.CREATED).body(libraryevent);
	}
	
	@PutMapping("/v1/libraryeventupdate")
	public ResponseEntity<?> putLibraryEvent(@RequestBody @Valid LibraryEvent libraryevent) throws JsonProcessingException
	{
		if(libraryevent.getLibraryId()==null) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PLease enter the valid Libary Id");
		}
		else {
		libraryevent.setLibraryEventType(LibraryEventType.UPDATE);
		// APPROACH-1 ASYNCHRONOUS
		log.info("Before calling Asynchronous method sendLibraryEvent");
		producer.sendLibraryEvent(libraryevent);
		log.info("After calling Asynchronous method sendLibraryEvent");
		
		//CHECKING ASYNCHRONOUS FUNCTIONALITY, as After calling Asynchronous method sendLibraryEvent 
		//get print before message from producer class.
		
		
		/* APPROACH-2 SYNCHRONOUS
		 * log.info("BEFORE"); SendResult<Integer, String> sendLibraryEventSynchronous =
		 * producer.sendLibraryEventSynchronous(libraryevent);
		 * log.info(sendLibraryEventSynchronous.toString()); 
		 * log.info("AFTER");
		 */
		
		/* APPROACH-3 , ASYNCHRONOUS with Custom Topic Name
		 * log.info("BEFORE"); 
		 * producer.sendLibraryEventApproach2(libraryevent);
		 * log.info("AFTER");
		 */
		
		return ResponseEntity.status(HttpStatus.CREATED).body(libraryevent);
	}
	}
}
