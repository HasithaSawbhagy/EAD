package com.example.userservice.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class LoginDTO {


    private String email;
    private String password;
}
