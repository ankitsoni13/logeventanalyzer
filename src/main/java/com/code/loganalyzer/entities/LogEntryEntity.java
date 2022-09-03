package com.code.loganalyzer.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity corresponding to LogEntry
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LogEntryEntity {

    @Id
    @Column
    private String eventId;

    @Column
    private String type;
    @Column
    private String host;
    @Column
    private Long duration;

    @Column
    private Boolean alert;


}
