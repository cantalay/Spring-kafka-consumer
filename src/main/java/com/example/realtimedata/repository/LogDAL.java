package com.example.realtimedata.repository;

import com.example.realtimedata.model.Log4Db;

import java.util.List;

public interface LogDAL {
    List<Log4Db> getAllLog();
    Log4Db addNewLog(Log4Db log4Db);
}
