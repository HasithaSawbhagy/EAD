package com.example.userservice.dto;

import com.example.userservice.enums.UserRole;
import lombok.Data;

@Data
public class InventoryKeeperDTO {

    private String email;
    private String fullName;
    private String password;
    private UserRole role;
    private String telephone;

}
