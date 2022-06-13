package com.kafka.consumer.access;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kafka.consumer.service.LibraryEventService;

@Component
public class LibraryEventConsumer {

	Logger log=LoggerFactory.getLogger(LibraryEventConsumer.class);
	
	@Autowired
	LibraryEventService libraryEventService;
	
	@KafkaListener(topics = {"library-events"})
	public void getMessage(ConsumerRecord<Integer, String> consumerRecord) throws JsonMappingException, JsonProcessingException {
		
		libraryEventService.proccessLibraryEvent(consumerRecord);
		log.info("Consumer Record"+ consumerRecord);
	}
	
}
