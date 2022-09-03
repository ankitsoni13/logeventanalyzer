package com.code.loganalyzer.processor;

import com.code.loganalyzer.dao.LogEntryRepository;
import com.code.loganalyzer.entities.LogEntryEntity;
import com.code.loganalyzer.entities.LogEventEntity;
import com.code.loganalyzer.helpers.LogFileReader;
import com.code.loganalyzer.services.LogEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;

import static org.junit.jupiter.api.Assertions.*;

class LogEntryProcessorTest {


    ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    LogFileReader logFileReader;

    @Mock
    LogEventService logEventService;

    @Mock
    LogEntryRepository logEntryRepository;


    @Mock
    LogEventEntity logEventEntity;

    @Mock
    LogEntryEntity logEntryEntity;

    LogEntryProcessor logEntryProcessor;

    final String STR_JSON1 = "{\"id\":\"1\",\"state\":\"START\",\"type\":\"APPLICATION\",\"host\":\"abc\",\"timestamp\":1491377495212}";
    final String STR_JSON2 = "{\"id\":\"1\",\"state\":\"END\",\"type\":\"APPLICATION\",\"host\":\"abc\",\"timestamp\":1491377495212}";

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        logEntryProcessor = new LogEntryProcessor(objectMapper,logEventService,logEntryRepository);
        Mockito.doReturn(STR_JSON1,STR_JSON2,null).when(logFileReader).readLine();
        Mockito.doNothing().when(logFileReader).close();

        Mockito.doReturn(logEventEntity).when(logEventService).getLogEventByAppId("1");

        Mockito.doReturn(System.currentTimeMillis()).when(logEventEntity).getTimestamp();
        Mockito.doReturn("1").when(logEventEntity).getAppId();

        Mockito.doReturn(logEventEntity).when(logEventService).save(Mockito.any(LogEventEntity.class),Mockito.anyBoolean());
        Mockito.doReturn(logEntryEntity).when(logEntryRepository).save(Mockito.any(LogEntryEntity.class));

        ReflectionTestUtils.setField(logEntryProcessor,"logEventThreshold",4L);

    }
    @Test
    void processLogFile() {
            try (MockedStatic<LogFileReader> mockedStatic = Mockito.mockStatic(LogFileReader.class)) {
                mockedStatic.when(() -> LogFileReader.getInstance(Mockito.anyString()))
                        .thenReturn(logFileReader);
                Assertions.assertDoesNotThrow(()->logEntryProcessor.processLogFile("abc.txt"));
            }
        }
    }