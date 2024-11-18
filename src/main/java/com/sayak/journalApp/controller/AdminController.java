package com.sayak.journalApp.controller;

import com.sayak.journalApp.entity.Users;
import com.sayak.journalApp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UsersService usersService;
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUser(){
        List<Users> allUsers = usersService.getAllUsers();
        if(allUsers != null && !allUsers.isEmpty()){
            return new ResponseEntity<>(allUsers, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping("/create-admin")
    public void createAdminUser(@RequestBody Users user){
        usersService.saveAdminUser(user);
    }
}
