package com.sayak.journalApp.service;

import com.sayak.journalApp.entity.JournalEntity;
import com.sayak.journalApp.entity.Users;
import com.sayak.journalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UsersService usersService;
    @Transactional
    public void saveJournalEntry(JournalEntity journalEntity, String userName){
        try {
            Users user = usersService.getUserByUserName(userName);
            journalEntity.setDate(LocalDateTime.now());
            JournalEntity saved = journalEntryRepository.save(journalEntity);
            user.getJournalEntities().add(saved);
            usersService.saveUser(user);
        }catch (Exception e){
            log.error("Error occurred during save the journal for {} ",userName, e);
            throw new RuntimeException("Error ocurred during saving the entry",e);
        }
    }

    public void saveJournalEntry(JournalEntity journalEntity){
        journalEntryRepository.save(journalEntity);
    }

    public List<JournalEntity> getAllJournalEntity(String userName){
        Users user = usersService.getUserByUserName(userName);
        return user.getJournalEntities();
    }

    public Optional<JournalEntity> findJournalEntryById(String userName, ObjectId id){
        try {
            Users user = usersService.getUserByUserName(userName);
            List<JournalEntity> collect = user.getJournalEntities().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
            if(!collect.isEmpty()){
                return journalEntryRepository.findById(id);
            }
        }catch (Exception e){
            log.error("Error ", e);
        }
        return null;
    }
    @Transactional
    public boolean deleteJournalEntityById(ObjectId id, String userName){
        try {
            Users user = usersService.getUserByUserName(userName);
            List<JournalEntity> collect = user.getJournalEntities().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
            if(!collect.isEmpty()){
                user.getJournalEntities().remove(id);
                usersService.saveUser(user);
                journalEntryRepository.deleteById(id);
                return true;
            }
        }catch (Exception e) {
            log.error("Error ", e);
            throw new RuntimeException("Id is not found for user: " + userName);
        }
        return false;

//        user.getJournalEntities().removeIf(x -> x.getId().equals(id));
//        usersService.saveUser(user);
//        journalEntryRepository.deleteById(id);
    }

}
