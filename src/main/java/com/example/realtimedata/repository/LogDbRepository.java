package com.example.realtimedata.repository;

import com.example.realtimedata.model.Log4Db;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDbRepository extends MongoRepository<Log4Db, String> {
}
