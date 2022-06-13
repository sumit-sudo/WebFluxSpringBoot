package com.kafka.consumer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kafka.consumer.entity.LibraryEvent;

@Repository
public interface ConsumerEventDao extends JpaRepository<LibraryEvent, Integer> {

}
