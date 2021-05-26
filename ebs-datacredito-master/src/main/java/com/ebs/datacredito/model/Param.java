package com.ebs.datacredito.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Param")
@Table(name = "Param")
public class Param {

    @Id
    @SequenceGenerator(
            name = "param_sequence",
            sequenceName = "param_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "param_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String value;

    public Param() {
    }

}
