package com.omega.bioway.identity.dataaccess;

import com.omega.bioway.identity.crosscutting.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);
}
