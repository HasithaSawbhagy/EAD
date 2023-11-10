package com.example.userservice.entity;

import com.example.userservice.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@Table(name = "delivery_person")
@Entity
public class DeliveryPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String fullName;
    private String password;
    private UserRole role;
    private String telephone;
    private String areaCode;

    public DeliveryPerson() {
    }

    public DeliveryPerson(String email, String fullName, String password, UserRole role, String telephone, String areaCode) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
        this.telephone = telephone;
        this.areaCode = areaCode;
    }



}
