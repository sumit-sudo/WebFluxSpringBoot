package com.sumit.graphql.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tutorial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private int id;
	@Column(name="description", nullable = false)
	private String description;
	@Column(name="title", nullable = false)
	private String title;
	//@Column(name="author_id", nullable = false)
	@ManyToOne
	@JoinColumn(name="author_id", nullable = false,updatable = false )
	private Author author;
	
	public Tutorial(int id, String description, String title, Author author) {
		super();
		this.id = id;
		this.description = description;
		this.title = title;
		this.author = author;
	}
	public Tutorial() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	
	
	
}
