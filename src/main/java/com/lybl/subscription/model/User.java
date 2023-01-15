package com.lybl.subscription.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString()
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private Long id;

    private String username;

    private String email;

    private String password;

    private String roles = "ADMIN,USER";

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
