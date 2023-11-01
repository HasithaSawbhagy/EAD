package com.example.userservice.controller;

import com.example.userservice.entity.User;
import com.example.userservice.security.JwtTokenProvider;
import com.example.userservice.service.UserService;
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

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User loginUser) {
        User authenticatedUser = userservice.loginUser(loginUser.getEmail(), loginUser.getPassword());

        if (authenticatedUser != null) {
            String token = jwtTokenProvider.generateToken(authenticatedUser.getEmail());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
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

