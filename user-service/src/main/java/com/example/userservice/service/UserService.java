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

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}

