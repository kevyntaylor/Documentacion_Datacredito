package com.ebs.datacredito.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
public class UserDTO implements Serializable {

    private Long id;
    private String email;
    private Timestamp create_date;
    private Timestamp edit_date;
    private boolean active;

    public UserDTO() {
    }

    public UserDTO(Long id, String email, Timestamp create_date, Timestamp edit_date, boolean active) {
        this.id = id;
        this.email = email;
        this.create_date = create_date;
        this.edit_date = edit_date;
        this.active = active;
    }
}
