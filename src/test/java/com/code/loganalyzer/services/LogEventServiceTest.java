package com.code.loganalyzer.services;

import com.code.loganalyzer.dao.LogEventRepository;
import com.code.loganalyzer.entities.LogEventEntity;
import com.code.loganalyzer.model.LogEvent;
import com.code.loganalyzer.services.interfaces.ILogEventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LogEventServiceTest {

    @Mock
    LogEventRepository logEventRepository;

    @Mock
    LogEventEntity logEvent;

    ILogEventService logEventService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        logEventService = new LogEventService(logEventRepository);
    }
    @Test
    void getLogEventByAppId() {
        Mockito.doReturn(Optional.of(logEvent)).when(logEventRepository).findFirstByAppId("app1");
        Assertions.assertNotNull(logEventService.getLogEventByAppId("app1"));
    }

    @Test
    void save() {
        Mockito.doReturn(logEvent).when(logEventRepository).save(Mockito.any(LogEventEntity.class));
        Assertions.assertNotNull(logEventService.save(logEvent,false));
    }
}