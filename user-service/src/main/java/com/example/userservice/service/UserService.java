package com.example.userservice.service;

import com.example.userservice.entity.DeliveryPerson;
import com.example.userservice.entity.InventoryKeeper;
import com.example.userservice.entity.User;
import com.example.userservice.exception.NotFoundException;
import com.example.userservice.repository.DeliveryPersonRepository;
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
    private DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    private InventoryKeeperRepository inventoryKeeperRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //customer registration
    public User saveUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    //deliver person registration
    public DeliveryPerson saveDelveryPerson(DeliveryPerson deliveryPerson) {
        String encodedPassword = passwordEncoder.encode(deliveryPerson.getPassword());
        deliveryPerson.setPassword(encodedPassword);
        return deliveryPersonRepository.save(deliveryPerson);
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
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findByEmail(email);
        if (deliveryPerson != null && passwordEncoder.matches(password, deliveryPerson.getPassword())) {
            return deliveryPerson;
        }
        // If no match is found, return null
        return null;
    }


    //update customer
    public User updateUser(Long id, User user) {
        User existingUser =userRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
        existingUser.setFullName(user.getFullName());
        existingUser.setPassword(user.getPassword());
        existingUser.setAddress(user.getAddress());
        existingUser.setRole(user.getRole());
        existingUser.setEmail(user.getEmail());
        existingUser.setTelephone(user.getTelephone());
        return userRepository.save(existingUser);
    }

    //update delivery person
    public DeliveryPerson updateDeliveryPerson(Long id, DeliveryPerson deliveryPerson) {
        DeliveryPerson existingDeliveryPerson =deliveryPersonRepository.findById(id).orElseThrow(() -> new NotFoundException("Delivery Person not found"));
        existingDeliveryPerson.setFullName(deliveryPerson.getFullName());
        existingDeliveryPerson.setPassword(deliveryPerson.getPassword());
        existingDeliveryPerson.setEmail(deliveryPerson.getEmail());
        existingDeliveryPerson.setAreaCode(deliveryPerson.getAreaCode());
        existingDeliveryPerson.setTelephone(deliveryPerson.getTelephone());
        existingDeliveryPerson.setRole(deliveryPerson.getRole());
        return deliveryPersonRepository.save(existingDeliveryPerson);
    }

    //update inventory keeper
    public InventoryKeeper updateInventoryKeeper(Long id, InventoryKeeper inventoryKeeper) {
        InventoryKeeper existingInventoryKeeper =inventoryKeeperRepository.findById(id).orElseThrow(() -> new NotFoundException("Delivery Person not found"));
        existingInventoryKeeper.setFullName(inventoryKeeper.getFullName());
        existingInventoryKeeper.setPassword(inventoryKeeper.getPassword());
        existingInventoryKeeper.setEmail(inventoryKeeper.getEmail());
        existingInventoryKeeper.setTelephone(inventoryKeeper.getTelephone());
        existingInventoryKeeper.setRole(inventoryKeeper.getRole());
        return inventoryKeeperRepository.save(existingInventoryKeeper);
    }
}

