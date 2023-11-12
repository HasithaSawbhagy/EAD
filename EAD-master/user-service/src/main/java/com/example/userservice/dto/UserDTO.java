package com.example.userservice.dto;

import com.example.userservice.enums.UserRole;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDTO {

    @Column(unique = true)
    private String email;
    private String fullName;
    private String password;
    private UserRole role;
    private String telephone;
    private String address;
}
