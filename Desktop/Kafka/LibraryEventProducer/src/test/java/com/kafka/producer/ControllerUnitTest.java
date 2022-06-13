package com.kafka.producer;



import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.producer.controller.LibraryEventController;
import com.kafka.producer.domain.Book;
import com.kafka.producer.domain.LibraryEvent;
import com.kafka.producer.producer.LibraryEventProducer;

@WebMvcTest(LibraryEventController.class)
@AutoConfigureMockMvc
public class ControllerUnitTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	LibraryEventProducer producer;
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	public void testPostLibraryEvent() throws Exception
	{
		Book book=new Book(104, "XYZ", "PQR");
		LibraryEvent libraryEvent=new LibraryEvent(1,book);
		
		String json=objectMapper.writeValueAsString(libraryEvent);
		
		//As the return type of the method sendLibraryEvent is void
		doNothing().when(producer).sendLibraryEvent(isA(LibraryEvent.class));

        //expect
        mockMvc.perform(post("/api/v1/libraryevent")
        .content(json)
        .contentType(MediaType.APPLICATION_JSON.toString()))
        .andExpect(status().isCreated());
	}
}
