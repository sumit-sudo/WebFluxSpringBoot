package com.kafka.consumer.service;

import java.lang.runtime.SwitchBootstraps;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.consumer.dao.BookDao;
import com.kafka.consumer.dao.ConsumerEventDao;
import com.kafka.consumer.entity.Book;
import com.kafka.consumer.entity.LibraryEvent;

@Service
public class LibraryEventService {

	Logger log=LoggerFactory.getLogger(LibraryEventService.class);
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	ConsumerEventDao dao;
	@Autowired
	BookDao bdao;
	
	public void proccessLibraryEvent(ConsumerRecord<Integer, String> consumerRecord) throws JsonMappingException, JsonProcessingException {
		LibraryEvent libraryEvent = objectMapper.readValue(consumerRecord.value(), LibraryEvent.class);
		switch(libraryEvent.getLibraryEventType()){
		case NEW:
				save(libraryEvent);
				break;
		case UPDATE:
			break;
		default:
			
		}
		
	}

	private void save(LibraryEvent libraryEvent) {
		libraryEvent.getBook().setLibraryEvent(libraryEvent);
		dao.save(libraryEvent);
		log.info("message logged successfully with bookid {}", libraryEvent.getBook().getBookId());
		
	}
}
