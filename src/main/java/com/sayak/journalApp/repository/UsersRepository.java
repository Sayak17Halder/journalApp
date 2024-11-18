package com.sayak.journalApp.repository;

import com.sayak.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface UsersRepository extends MongoRepository<Users, ObjectId> {
    Users getUserByUsername(String username);

    void deleteUserByUsername(String userName);
}
