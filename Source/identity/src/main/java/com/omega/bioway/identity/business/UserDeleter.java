package com.omega.bioway.identity.business;

import com.omega.bioway.identity.crosscutting.entities.User;
import com.omega.bioway.identity.crosscutting.exceptions.UserNotFoundException;
import com.omega.bioway.identity.dataaccess.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserDeleter {

    private UserRepository repository;

    @Autowired
    public UserDeleter(UserRepository repository) {
        this.repository = repository;
    }

    public void execute(String userId){
        Optional<User> user=repository.findById(userId);
        if(user.isEmpty()){
            throw new UserNotFoundException("User with id: "+userId+" not found");
        }
        repository.delete(user.get());
    }
}
