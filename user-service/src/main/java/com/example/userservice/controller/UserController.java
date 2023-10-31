package com.example.userservice.controller;


import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user")UserDto userDto){
        return "register";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user")UserDto userDto, Model model){
        userService.save(userDto);
        model.addAttribute("message", "Registered successfully");
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


}
