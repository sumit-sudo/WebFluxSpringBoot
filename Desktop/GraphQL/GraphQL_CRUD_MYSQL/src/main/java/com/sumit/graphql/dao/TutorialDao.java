package com.sumit.graphql.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sumit.graphql.entities.Tutorial;

@Repository
public interface TutorialDao extends JpaRepository<Tutorial,Integer>{

}
