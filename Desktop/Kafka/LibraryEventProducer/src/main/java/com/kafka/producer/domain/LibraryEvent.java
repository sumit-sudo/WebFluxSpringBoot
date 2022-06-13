package com.kafka.producer.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class LibraryEvent {

	private Integer libraryId;
	private LibraryEventType libraryEventType;
	@NotNull
	@Valid
	private Book book;
	
	public LibraryEventType getLibraryEventType() {
		return libraryEventType;
	}
	public void setLibraryEventType(LibraryEventType libraryEventType) {
		this.libraryEventType = libraryEventType;
	}

	public Integer getLibraryId() {
		return libraryId;
	}
	public void setLibraryId(Integer libraryId) {
		this.libraryId = libraryId;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public LibraryEvent(int libraryId, Book book) {
		super();
		this.libraryId = libraryId;
		this.book = book;
	}
	public LibraryEvent() {
		super();
	}
	
	
}
