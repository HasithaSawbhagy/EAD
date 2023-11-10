package com.example.userservice.entity;

import com.example.userservice.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String fullName;
    private String password;
    private UserRole role;
    private String telephone;
    private String address;

    public User(String email, String fullName, String password, UserRole role, String telephone, String address) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
        this.telephone = telephone;
        this.address = address;
    }

    public User() {

    }
}
