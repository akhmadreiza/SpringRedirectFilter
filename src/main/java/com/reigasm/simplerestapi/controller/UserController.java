package com.reigasm.simplerestapi.controller;

import com.reigasm.simplerestapi.dto.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.reigasm.simplerestapi.service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/retrieveUsers")
    public ResponseEntity<List<UserTO>> getAllUsers()
    {
        List<UserTO> users= userService.getUserList();
        return new ResponseEntity<List<UserTO>>(users, HttpStatus.OK);
    }
}
