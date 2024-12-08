package com.sayak.journalApp.repository;

import com.sayak.journalApp.entity.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Disabled
public class UserRepositoryImplTest {
    @Autowired
    private UserRepositoryImpl userRepository;
    @Test
    @Disabled
    public void getUserForSATest(){
        Assertions.assertNotNull(userRepository.getUserForSA());
    }
}
