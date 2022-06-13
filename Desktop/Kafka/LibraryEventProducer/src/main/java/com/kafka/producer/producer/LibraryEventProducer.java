package com.kafka.producer.producer;




import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.producer.domain.LibraryEvent;


@Component
public class LibraryEventProducer {

	Logger log=LoggerFactory.getLogger(LibraryEventProducer.class);
	String topic = "library-events";
	
	@Autowired
	KafkaTemplate<Integer, String> kafkaTemplate;
	@Autowired
	ObjectMapper mapper; // help us to convert JSON into string
	
	public void sendLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException {
		int key = libraryEvent.getLibraryId();
		String value = mapper.writeValueAsString(libraryEvent);
		
		ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.sendDefault(key, value);
		/*
		 * As It will transfer message into Buffer, and it will be push only if buffer
		 * size is full or it stays inside buffer for specific time period.
		 */
		// sendDefault will pick topic name from Application.yml file
		/*
		 * sendDefault(key, value); is a Asynchronous call, So It won't wait for method to complete,
		 * Once sendDeafult() get call at line num 35, It will go back to controller without worry about
		 * execution status of called method.
		 */
		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>(){

			@Override
			public void onSuccess(SendResult<Integer, String> result) {
				handleSuccess(key, value, result);
			}

			@Override
			public void onFailure(Throwable ex) {
				try {
					handleFaliure(key, value,ex);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	public void sendLibraryEventApproach2(LibraryEvent libraryEvent) throws JsonProcessingException {
		
		int key = libraryEvent.getLibraryId();
		String value = mapper.writeValueAsString(libraryEvent);
		// TOPIC NAME IS NOT DEFAULT, CAN BE TAKEN AS AN INPUT
		
		ProducerRecord<Integer, String> buildProduceRecord = buildProduceRecord(key, value,topic);
		ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.send(buildProduceRecord);
		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>(){

			@Override
			public void onSuccess(SendResult<Integer, String> result) {
				handleSuccess(key, value, result);
			}

			@Override
			public void onFailure(Throwable ex) {
				try {
					handleFaliure(key, value,ex);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public SendResult<Integer, String> sendLibraryEventSynchronous(LibraryEvent libraryEvent) throws JsonProcessingException{
		int key = libraryEvent.getLibraryId();
		String value = mapper.writeValueAsString(libraryEvent);
		SendResult<Integer, String> sendResult=null;
		// As It is Synchronous, It will wait only 1 second for prodcing message, if It fails to produce, 
		// t will get back to Controller class after 1 second.
		try {
			sendResult = kafkaTemplate.sendDefault(key, value).get(1, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.error("ExecutionException/InterruptedException Sending the Message and the exception is {}", e.getMessage());
		}
		catch(Exception e) {
			log.error("Exception Sending the Message and the exception is {}", e.getMessage());
		}
		
		return sendResult;
	}
	
	private ProducerRecord<Integer, String> buildProduceRecord(int key, String value, String topic) {
		List<Header> recordHeaders = List.of(new RecordHeader("event-source", "scanner".getBytes()));

        return new ProducerRecord<>(topic, null, key, value, recordHeaders);
		
	}
	private void handleSuccess(int key, String value, SendResult<Integer, String> result) {
		log.info("The message has been transfer to Topic with key {}, value {} and in the partion {} number",
				key,value,result.getRecordMetadata().partition());
		
	}

	private void handleFaliure(int key, String value, Throwable ex) throws Throwable {
		log.error("Error Sending the Message and the exception is {}", ex.getMessage());
		try {
			throw ex;
		}catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
