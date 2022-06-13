package com.sumit.graphql.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sumit.graphql.entities.Author;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer>{

}
