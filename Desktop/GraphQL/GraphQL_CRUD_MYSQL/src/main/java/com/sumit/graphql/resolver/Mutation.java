package com.sumit.graphql.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.sumit.graphql.dao.AuthorDao;
import com.sumit.graphql.dao.TutorialDao;
import com.sumit.graphql.entities.Author;
import com.sumit.graphql.entities.Tutorial;

@Component
public class Mutation implements GraphQLMutationResolver {

	@Autowired
	private AuthorDao author;
	@Autowired
	private TutorialDao tutorials;
	
	public Tutorial createTutorial(String title, String description, int id){
		Tutorial tutorial=new Tutorial();
		tutorial.setDescription(description);
		tutorial.setTitle(title);
		tutorial.setAuthor(new Author(id));
		return tutorials.save(tutorial);
	}
}
