package com.example.userservice.service;

import com.example.userservice.dto.DeliveryPersonDTO;
import com.example.userservice.dto.InventoryKeeperDTO;
import com.example.userservice.dto.UserDTO;
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


    public Object getUserByEmailTest(String email) {
        User customer = userRepository.findByEmail(email);
        if (customer != null ) {
            return customer;
        }

        // If not a CUSTOMER, try to authenticate as an INVENTORY_MANAGER
        InventoryKeeper inventoryManager = inventoryKeeperRepository.findByEmail(email);
        if (inventoryManager != null ) {
            return inventoryManager;
        }

        // If not an INVENTORY_MANAGER, try to authenticate as a DELIVERY_PERSON
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findByEmail(email);
        if (deliveryPerson != null ) {
            return deliveryPerson;
        }
        // If no match is found, return null
        return null;
    }

    //customer registration
    public User saveUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getEmail(),
                userDTO.getFullName(),
                this.passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getRole(),
                userDTO.getTelephone(),
                userDTO.getAddress()

        );

        return userRepository.save(user);
    }

    //deliver person registration
    public DeliveryPerson saveDelveryPerson(DeliveryPersonDTO deliveryPersonDTO) {

        DeliveryPerson deliveryPerson = new DeliveryPerson(
                deliveryPersonDTO.getEmail(),
                deliveryPersonDTO.getFullName(),
                this.passwordEncoder.encode(deliveryPersonDTO.getPassword()),
                deliveryPersonDTO.getRole(),
                deliveryPersonDTO.getTelephone(),
                deliveryPersonDTO.getAreaCode()

        );

        return deliveryPersonRepository.save(deliveryPerson);
    }

    //InventoryKeeper registration
    public InventoryKeeper saveInventoryKeeper(InventoryKeeperDTO inventoryKeeperDTO) {

        InventoryKeeper inventoryKeeper = new InventoryKeeper(
                inventoryKeeperDTO.getEmail(),
                inventoryKeeperDTO.getFullName(),
                this.passwordEncoder.encode(inventoryKeeperDTO.getPassword()),
                inventoryKeeperDTO.getRole(),
                inventoryKeeperDTO.getTelephone()

        );


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
    public User updateUser(Long id, UserDTO userDTO) {
        User existingUser =userRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
        existingUser.setFullName(userDTO.getFullName());
        existingUser.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));

        existingUser.setAddress(userDTO.getAddress());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setTelephone(userDTO.getTelephone());
        return userRepository.save(existingUser);
    }

    //update delivery person
    public DeliveryPerson updateDeliveryPerson(Long id, DeliveryPersonDTO deliveryPersonDTO) {
        DeliveryPerson existingDeliveryPerson =deliveryPersonRepository.findById(id).orElseThrow(() -> new NotFoundException("Delivery Person not found"));
        existingDeliveryPerson.setFullName(deliveryPersonDTO.getFullName());
        existingDeliveryPerson.setPassword(this.passwordEncoder.encode(deliveryPersonDTO.getPassword()));
        existingDeliveryPerson.setEmail(deliveryPersonDTO.getEmail());
        existingDeliveryPerson.setAreaCode(deliveryPersonDTO.getAreaCode());
        existingDeliveryPerson.setTelephone(deliveryPersonDTO.getTelephone());
        return deliveryPersonRepository.save(existingDeliveryPerson);
    }

    //update inventory keeper
    public InventoryKeeper updateInventoryKeeper(Long id, InventoryKeeperDTO inventoryKeeperDTO) {
        InventoryKeeper existingInventoryKeeper =inventoryKeeperRepository.findById(id).orElseThrow(() -> new NotFoundException("Delivery Person not found"));
        existingInventoryKeeper.setFullName(inventoryKeeperDTO.getFullName());
        existingInventoryKeeper.setPassword(this.passwordEncoder.encode(inventoryKeeperDTO.getPassword()));
        existingInventoryKeeper.setEmail(inventoryKeeperDTO.getEmail());
        existingInventoryKeeper.setTelephone(inventoryKeeperDTO.getTelephone());
        return inventoryKeeperRepository.save(existingInventoryKeeper);
    }
}

