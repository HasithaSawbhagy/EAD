package com.example.userservice.model;

import jakarta.persistence.*;


@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name="id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="fullName", length = 200)
    private String fullName;

    @Column(name="email", length = 200)
    private String email;


    @Column(name="passwod", length = 200)
    private String password;

    @Column(name="role", length = 200)
    private String role;

    @Column(name="telephone", length = 200)
    private String telephone;

    public User() {
        super();
    }

    public User(String fullName, String email, String password, String role, String telephone) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String userName) {
        this.fullName = fullName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
