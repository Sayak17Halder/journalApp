package com.sayak.journalApp.Service;

import com.sayak.journalApp.entity.Users;
import com.sayak.journalApp.repository.UsersRepository;
import com.sayak.journalApp.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
@Disabled
public class UserDetailsServiceImplTests {
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;
    @Mock
    private UsersRepository usersRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void loadUserByUsernameTests(){
        when(usersRepository.getUserByUsername("Ram")).thenReturn(Users.builder().username("Ram").password("ksjbvkasJhbr").roles(new ArrayList<>()).build());
        UserDetails ram = userDetailsService.loadUserByUsername("Ram");
        Assertions.assertNotNull(ram);
    }
}
