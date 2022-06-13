package com.kafka.producer.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



public class Book {

	/*
	 * The @Column(nullable = false) annotation only adds a not null constraint to
	 * the table definition. Hibernate or any other framework will not perform any
	 * validation on the entity attribute.
	 */
	
	/*
	 * @NotNull annotation tells your BeanValidation implementation to check that
	 * the attribute is not null.
	 */
	/*
	 * You can ignore null fields at 
	 *  the class level by using @JsonInclude(Include.NON_NULL) to only include non-null fields,
	 */
	
	@NotNull
    private Integer bookId;
    @NotBlank
    private String bookName;
    @NotBlank
    private String bookAuthor;
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

	public Book(@NotNull Integer bookId, @NotBlank String bookName, @NotBlank String bookAuthor) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
	}
	public Book() {
		super();
	}
    
    
}
