package com.code.loganalyzer.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;

/**
 * Entity corresponding to LogEvent
 */
@Data
@Entity
public class LogEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer pkId;

    @Column
    @JsonProperty("id")
    private String appId;

    @Column
    private String state;
    @Column
    private String type;
    @Column
    private String host;
    @Column
    private Long timestamp;

}
