package com.code.loganalyzer.dao;

import com.code.loganalyzer.entities.LogEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for storing Log Entry (Id, Type, Host, Duration, Alert).
 */
public interface LogEntryRepository extends JpaRepository<LogEntryEntity,String> {
}
