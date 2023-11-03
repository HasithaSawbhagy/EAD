package com.example.userservice.entity;

import com.example.userservice.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "inventory_keeper")
@Entity
public class InventoryKeeper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String fullName;
    private String password;
    private UserRole role;
    private String telephone;

}
