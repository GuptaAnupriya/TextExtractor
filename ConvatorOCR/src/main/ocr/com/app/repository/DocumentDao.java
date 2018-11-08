package com.app.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Document;


@Repository
public interface DocumentDao extends MongoRepository<Document, String> {

}
