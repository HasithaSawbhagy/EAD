package com.example.userservice.dto;

public class UserDto {

    private String email;
    private String password;
    private String role;
    private String userName;
    private String telephone;

    public UserDto(String email, String password, String role, String userName, String telephone) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
