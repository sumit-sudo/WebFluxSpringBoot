package com.sumit.graphql.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.sumit.graphql.dao.AuthorDao;
import com.sumit.graphql.dao.TutorialDao;
import com.sumit.graphql.entities.Author;
import com.sumit.graphql.entities.Tutorial;


public class Query implements GraphQLQueryResolver {

	@Autowired
	private AuthorDao author;
	@Autowired
	private TutorialDao tutorial;
	
	public List<Author> findAllAuthors()
	{
		System.out.println("Hi");
		return author.findAll();
	}
	
	public List<Tutorial> findAllTutorial() {
		return tutorial.findAll();
	}
	
	public Long countTutorials()
	{
		return tutorial.count();
	}
	
}
