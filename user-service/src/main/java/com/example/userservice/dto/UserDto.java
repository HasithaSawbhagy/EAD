package com.example.userservice.dto;

public class UserDto {

    private String fullName;
    private String email;
    private String password;
    private String role;
    private String telephone;

    public UserDto(String fullName, String email, String password, String role, String telephone) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.telephone = telephone;
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

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
