package com.sayak.journalApp.Service;

import com.sayak.journalApp.service.UsersService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UsersServiceTests {
    @Autowired
    private UsersService usersService;

    @ParameterizedTest
    @ValueSource(strings = {
            "Ram",
            "Sam",
            "Abhi"
    })
    public void testFindByUsername(String name){
        assertNotNull(usersService.getUserByUserName(name));
//        assertTrue(!usersService.getUserByUserName("Ram").getJournalEntities().isEmpty());
    }
    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "10,2,12",
            "3,9,11"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
}
