package com.code.loganalyzer.services.interfaces;

import com.code.loganalyzer.entities.LogEventEntity;

import java.util.List;

public interface ILogEventService {

    public LogEventEntity getLogEventByAppId(String appId);
    public LogEventEntity save(LogEventEntity logEventEntity,boolean cached);


}
