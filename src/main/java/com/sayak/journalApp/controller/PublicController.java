package com.sayak.journalApp.controller;

import com.sayak.journalApp.cache.AppCache;
import com.sayak.journalApp.entity.Users;
import com.sayak.journalApp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private AppCache appCache;
    @GetMapping("/health-check")
    public String healthCheck(){
        return "Ok";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody Users user){
        usersService.saveNewUser(user);
    }

    @GetMapping("/clear-app-cache")
    public void clearAppCache(){
        appCache.init();
    }
}
