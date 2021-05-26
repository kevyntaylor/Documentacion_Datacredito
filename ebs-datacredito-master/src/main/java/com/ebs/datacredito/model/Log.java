package com.ebs.datacredito.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity(name = "Log")
@Table(name = "Log")
public class Log {

    @Id
    @SequenceGenerator(
            name = "log_sequence",
            sequenceName = "log_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "log_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private String remoteIP;

    @Column(nullable = false)
    private Timestamp starRequest;

    @Column(nullable = false)
    private Timestamp endRequest;

    public Log() {
    }

    public Log(String action, String remoteIP, Timestamp starRequest) {
        this.action = action;
        this.remoteIP = remoteIP;
        this.starRequest = starRequest;
        this.endRequest = endRequest;
    }

}
