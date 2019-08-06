package com.example.realtimedata.repository;

import com.example.realtimedata.model.Log4Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogDALImpl implements LogDAL {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Log4Db> getAllLog() {
        return null;
    }

    @Override
    public Log4Db addNewLog(Log4Db log4Db) {
        return log4Db;
    }
}
