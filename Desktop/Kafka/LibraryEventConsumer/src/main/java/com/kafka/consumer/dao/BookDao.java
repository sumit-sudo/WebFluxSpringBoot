package com.kafka.consumer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kafka.consumer.entity.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Integer>{

}
