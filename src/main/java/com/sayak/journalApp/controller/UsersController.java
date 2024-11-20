package com.sayak.journalApp.controller;

import com.sayak.journalApp.WeatherEntity.WeatherResponse;
import com.sayak.journalApp.entity.Users;
import com.sayak.journalApp.service.UsersService;
import com.sayak.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private WeatherService weatherService;
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody Users newUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users oldUser = usersService.getUserByUserName(userName);
        oldUser.setUsername(newUser.getUsername());
        oldUser.setPassword(newUser.getPassword());
        usersService.saveNewUser(oldUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        usersService.deleteUserByUsername(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greetings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weather = weatherService.getWeather("Mumbai");
        String greeting = "";
        if(weather != null){
            greeting = ", weather feels like "+weather.getCurrent();
        }
        return new ResponseEntity<>("Hi "+authentication.getName() + greeting, HttpStatus.NO_CONTENT);
    }
}
