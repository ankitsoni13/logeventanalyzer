package com.code.loganalyzer.services;

import com.code.loganalyzer.dao.LogEventRepository;
import com.code.loganalyzer.entities.LogEventEntity;
import com.code.loganalyzer.services.interfaces.ILogEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Service class to interact with LogEventRepository. Provides caching layer.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LogEventService implements ILogEventService {


    private final LogEventRepository logEventRepository;

    @Override
    @CacheEvict(value = "logEntry")
    public LogEventEntity getLogEventByAppId(String appId) {
        return logEventRepository.findFirstByAppId(appId).orElse(null);
    }

    @Override
    @Cacheable(value = "logEntry",key="#logEventEntity.appId",condition="#cached")
    public LogEventEntity save(LogEventEntity logEventEntity, boolean cached) {
        return logEventRepository.save(logEventEntity);
    }

}
