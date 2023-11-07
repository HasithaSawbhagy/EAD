package com.example.userservice.service;

import com.example.userservice.entity.DelveryPerson;
import com.example.userservice.entity.InventoryKeeper;
import com.example.userservice.entity.User;
import com.example.userservice.repository.DelveryPersonRepository;
import com.example.userservice.repository.InventoryKeeperRepository;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
   @Autowired
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DelveryPersonRepository delveryPersonRepository;

    @Autowired
    private InventoryKeeperRepository inventoryKeeperRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    //deliver person registration
    public DelveryPerson saveDelveryPerson(DelveryPerson delveryPerson) {
        String encodedPassword = passwordEncoder.encode(delveryPerson.getPassword());
        delveryPerson.setPassword(encodedPassword);
        return delveryPersonRepository.save(delveryPerson);
    }

    //InventoryKeeper registration
    public InventoryKeeper saveInventoryKeeper(InventoryKeeper inventoryKeeper) {
        String encodedPassword = passwordEncoder.encode(inventoryKeeper.getPassword());
        inventoryKeeper.setPassword(encodedPassword);
        return inventoryKeeperRepository.save(inventoryKeeper);
    }

    public Object loginUser(String email, String password) {
        // First, try to authenticate as a CUSTOMER
        User customer = userRepository.findByEmail(email);
        if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {
            return customer;
        }

        // If not a CUSTOMER, try to authenticate as an INVENTORY_MANAGER
        InventoryKeeper inventoryManager = inventoryKeeperRepository.findByEmail(email);
        if (inventoryManager != null && passwordEncoder.matches(password, inventoryManager.getPassword())) {
            return inventoryManager;
        }

        // If not an INVENTORY_MANAGER, try to authenticate as a DELIVERY_PERSON
        DelveryPerson deliveryPerson = delveryPersonRepository.findByEmail(email);
        if (deliveryPerson != null && passwordEncoder.matches(password, deliveryPerson.getPassword())) {
            return deliveryPerson;
        }

        // If no match is found, return null
        return null;
    }
}

