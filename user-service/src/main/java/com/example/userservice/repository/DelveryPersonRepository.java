package com.example.userservice.repository;

import com.example.userservice.entity.DelveryPerson;
import com.example.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelveryPersonRepository extends JpaRepository<DelveryPerson, Long> {



    User findByEmailAndPassword(String email, String password);

    DelveryPerson findByEmail(String email);
}
