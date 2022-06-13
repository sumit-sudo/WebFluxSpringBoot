package com.kafka.consumer.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
public class Book {
	@Id
    private Integer bookId;
    private String bookName;
    private String bookAuthor;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="libraryId")
    private LibraryEvent libraryEvent;
    
    
	public LibraryEvent getLibraryEvent() {
		return libraryEvent;
	}
	public void setLibraryEvent(LibraryEvent libraryEvent) {
		this.libraryEvent = libraryEvent;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public Book(Integer bookId, String bookName, String bookAuthor, LibraryEvent libraryEvent) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.libraryEvent = libraryEvent;
	}
	public Book() {
		super();
	}
    
    
}
