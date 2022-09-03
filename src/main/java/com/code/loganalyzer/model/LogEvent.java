package com.code.loganalyzer.model;

import lombok.Data;

/**
 * POJO corresponding to log-file entry.
 */
@Data
public class LogEvent {
    public String id;
    public String state;
    public String type;
    public String host;
    public Long timestamp;
}
