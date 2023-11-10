package com.example.userservice.repository;

import com.example.userservice.entity.InventoryKeeper;
import com.example.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryKeeperRepository extends JpaRepository<InventoryKeeper, Long> {




    User findByEmailAndPassword(String email, String password);

    InventoryKeeper findByEmail(String email);
}
