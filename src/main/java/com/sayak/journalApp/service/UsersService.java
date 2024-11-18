package com.sayak.journalApp.service;
import com.sayak.journalApp.entity.Users;
import com.sayak.journalApp.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

//    private static  final Logger logger = LoggerFactory.getLogger(UsersService.class);
    @Autowired
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(Users user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            usersRepository.save(user);
        }catch (Exception e){
            log.error("Error occured for {} :", user.getUsername(), e);
        }
    }

    public void saveUser(Users user){
        usersRepository.save(user);
    }

    public void saveAdminUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("ADMIN"));
        usersRepository.save(user);
    }
    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    public Optional<Users> findUserById(ObjectId id){
        return usersRepository.findById(id);
    }

    public void deleteUserByUsername(String userName){
        usersRepository.deleteUserByUsername(userName);
    }
    public Users getUserByUserName(String userName){
        return usersRepository.getUserByUsername(userName);
    }


}
