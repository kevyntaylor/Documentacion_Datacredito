package com.ebs.datacredito.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity(name = "User")
@Table(
        name = "User",
        uniqueConstraints = {
                @UniqueConstraint(name = "email_constraint", columnNames = "email")
        }
)
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    private String email;

    private String password;

    private Timestamp create_date;

    private Timestamp edit_date;

    private boolean active;

    public User() {
    }

    public User(Long id, String email, String password, Timestamp create_date, Timestamp edit_date, boolean active) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.create_date = create_date;
        this.edit_date = edit_date;
        this.active = active;
    }

    public User(String email, String password, Timestamp create_date, Timestamp edit_date, boolean active) {
        this.email = email;
        this.password = password;
        this.create_date = create_date;
        this.edit_date = edit_date;
        this.active = active;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", create_date=" + create_date +
                ", edit_date=" + edit_date +
                ", active=" + active +
                '}';
    }

}
