package com.code.loganalyzer.dao;

import com.code.loganalyzer.entities.LogEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/*
    JPA Repository to save/retrieve each Log event details
 */
public interface LogEventRepository extends JpaRepository<LogEventEntity,Integer> {

    Optional<LogEventEntity> findFirstByAppId(String appId);

}
