package com.kafka.producer;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import com.kafka.producer.domain.Book;
import com.kafka.producer.domain.LibraryEvent;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(topics = { "library-events" }, partitions = 4)
@TestPropertySource(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
"spring.kafka.admin.properties.bootstrap.servers=${spring.embedded.kafka.brokers}"})
public class IntegrationTest {

	/*
	 * 
	 * with the help of @embededKafka and @TestPropertySource, we don't require 
	 * Zookeeper and brokers up and running
	 */
	@Autowired
	TestRestTemplate testTemplate;
	
	@Test
	public void testPostLibraryEvent() {
		
		Book book=new Book(104, "XYZ", "PQR");
		LibraryEvent libraryEvent=new LibraryEvent(1,book);
		
		HttpHeaders header=new HttpHeaders();
		header.set("content-type", MediaType.APPLICATION_JSON.toString());
		HttpEntity<LibraryEvent> request=new HttpEntity<>(libraryEvent,header);
		
		//when
		ResponseEntity<LibraryEvent> responseEntity=
				testTemplate.exchange("/api/v1/libraryevent", HttpMethod.POST, request,LibraryEvent.class);
		
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		
	}
	
}
