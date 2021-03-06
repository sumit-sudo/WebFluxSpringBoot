package com.movie.info.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

@ControllerAdvice
public class MovieGlobalException {
	
	@ExceptionHandler(WebExchangeBindException.class)
	public ResponseEntity<String> handleRequestBodyError(WebExchangeBindException exe) {
		System.out.println("HIIIIIIIII"+exe.getBindingResult().getAllErrors());
		var errorMessage=exe.getBindingResult().getAllErrors().stream()
							.map(DefaultMessageSourceResolvable::getDefaultMessage)
							.sorted()
							.collect(Collectors.joining(","));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		
	}

	@ExceptionHandler(MovieNotSavedException.class)
	public  ResponseEntity<MoviesErrorMessage> itemNotSavedException(MovieNotSavedException exe) {
		MoviesErrorMessage obj=new MoviesErrorMessage(
				HttpStatus.BAD_REQUEST.value(), exe.getMessage(), LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(obj);
	}
	
	
}
