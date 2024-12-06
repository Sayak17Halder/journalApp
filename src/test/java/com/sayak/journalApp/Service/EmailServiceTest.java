package com.sayak.journalApp.Service;

import com.sayak.journalApp.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;
    @Test
    public void sendEmailTest(){
        emailService.sendMail("avi17halder@gmail.com", "Welcome", "kaise ho aap !?");
    }
}
