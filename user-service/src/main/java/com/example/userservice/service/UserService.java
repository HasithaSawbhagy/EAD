package com.example.userservice.service;

import com.example.userservice.dto.LoginDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;
import com.example.userservice.response.LoginResponse;
import org.springframework.stereotype.Service;


public interface UserService {

    String addUser(UserDto userDto);

    LoginResponse loginUser(LoginDto loginDto);


}
