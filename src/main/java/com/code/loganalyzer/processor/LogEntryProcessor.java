package com.code.loganalyzer.processor;

import com.code.loganalyzer.dao.LogEntryRepository;
import com.code.loganalyzer.entities.LogEntryEntity;
import com.code.loganalyzer.entities.LogEventEntity;
import com.code.loganalyzer.helpers.LogFileReader;
import com.code.loganalyzer.model.LogEvent;
import com.code.loganalyzer.services.interfaces.ILogEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class having methods to parse and process given log file.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LogEntryProcessor {


    private final ObjectMapper objectMapper;


    private final ILogEventService logEventService;


    private final LogEntryRepository logEntryRepository;

    @Value("${log.event.threashold:4}")
    private Long logEventThreshold;

    /**
     * Method to parse and process given log file.
     * @param logFile   Logfile path which needs to be processed.
     */
    @SneakyThrows
    public void processLogFile(String logFile){
        LogFileReader logFileReader = LogFileReader.getInstance(logFile);
        String strLogEvent = null;
        log.info("BEGIN log file parsing");
        log.info("log.event.threashold = {}",logEventThreshold);

        //Reading file line by line
        while(null!= (strLogEvent = logFileReader.readLine())){
            LogEvent logEvent = objectMapper.readValue(strLogEvent, LogEvent.class);
            LogEventEntity logEventEntity = objectMapper.convertValue(logEvent, LogEventEntity.class);  //Converting line string to Json Object.
            LogEventEntity logEventEntity2 = logEventService.getLogEventByAppId(logEventEntity.getAppId()); // Checking if we already stored event for same id before
            if(null != logEventEntity2){
                boolean isAlert = false;
                long duration = Math.abs(logEventEntity.getTimestamp() - logEventEntity2.getTimestamp());   //Checking time difference of events of same id.
                if(duration>logEventThreshold){ //logging WARN for duration > threashold
                    log.warn("appId {} duration {}", logEventEntity.getAppId(),duration);
                    isAlert = true;
                }
                LogEntryEntity logEntry = new LogEntryEntity(logEventEntity.getAppId(),logEventEntity.getType(),logEventEntity.getHost(),
                        duration,isAlert);

                log.info("LogEntry details {}",logEntry.toString());

                logEntryRepository.save(logEntry);  //Storing log details into dedicated table.
                logEventService.save(logEventEntity,false); // Storing log event into table with caching= false as we have done processing with given id.
            }
            else{
                logEventService.save(logEventEntity,true);
            }

        }
        logFileReader.close();
        log.info("END log file parsing");
    }
}
