package com.example.userservice.repository;

import com.example.userservice.entity.DeliveryPerson;
import com.example.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Long> {



    User findByEmailAndPassword(String email, String password);

    DeliveryPerson findByEmail(String email);
}
