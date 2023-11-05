package com.example.userservice.controller;

import com.example.userservice.entity.DelveryPerson;
import com.example.userservice.entity.InventoryKeeper;
import com.example.userservice.entity.User;
import com.example.userservice.security.JwtTokenProvider;
import com.example.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    private final UserService userservice;
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    public UserController(UserService userservice, JwtTokenProvider jwtTokenProvider) {
        this.userservice = userservice;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userservice.saveUser(user);
    }

    //deliver person registration
    @PostMapping("/register_DelveryPerson")
    public DelveryPerson createDelveryPerson(@RequestBody DelveryPerson DelveryPerson) {
        return userservice.saveDelveryPerson(DelveryPerson);
    }


    //Inventory Keeper registration
    @PostMapping("/register_InventoryKeeper")
    public InventoryKeeper createInventoryKeeper(@Valid @RequestBody InventoryKeeper inventoryKeeper) {
        return userservice.saveInventoryKeeper(inventoryKeeper);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User loginUser) {
        Object authenticatedUser = userservice.loginUser(loginUser.getEmail(), loginUser.getPassword());

        if (authenticatedUser != null) {
            if (authenticatedUser instanceof User) {
                String token = jwtTokenProvider.generateToken(((User) authenticatedUser).getEmail(), String.valueOf(((User) authenticatedUser).getRole()));
                return ResponseEntity.ok(token);
            } else if (authenticatedUser instanceof InventoryKeeper) {
                String token = jwtTokenProvider.generateToken(((InventoryKeeper) authenticatedUser).getEmail(), String.valueOf(((InventoryKeeper) authenticatedUser).getRole()));
                return ResponseEntity.ok(token);
            } else if (authenticatedUser instanceof DelveryPerson) {
                String token = jwtTokenProvider.generateToken(((DelveryPerson) authenticatedUser).getEmail(), String.valueOf(((DelveryPerson) authenticatedUser).getRole()));
                return ResponseEntity.ok(token);
            }
        }

        return ResponseEntity.badRequest().body("Invalid email or password");
    }


    @GetMapping("/validateToken/{token}")
    public ResponseEntity<String> validateTokenAndGetEmail(@PathVariable String token) {
        boolean isValid = jwtTokenProvider.validateToken(token);
        if (isValid) {
            String email = jwtTokenProvider.getEmailFromToken(token);
            return ResponseEntity.ok("Valid Token. Email: " + email);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
    }

}

