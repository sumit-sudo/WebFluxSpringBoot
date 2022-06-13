package com.kafka.consumer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class LibraryEvent {
	@Id
	private Integer libraryId;
	private LibraryEventType libraryEventType;
	@OneToOne(mappedBy = "libraryEvent",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
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
