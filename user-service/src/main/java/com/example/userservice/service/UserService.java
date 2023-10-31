package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User save(UserDto userDto);


}
