package com.omega.bioway.identity.business;

import com.omega.bioway.identity.crosscutting.entities.User;
import com.omega.bioway.identity.crosscutting.exceptions.BadRequestException;
import com.omega.bioway.identity.crosscutting.exceptions.UserAlreadyExistException;
import com.omega.bioway.identity.dataaccess.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCreator {

    private UserRepository repository;

    @Autowired
    public UserCreator(UserRepository repository) {
        this.repository = repository;
    }

    public void execute(String email, String password, String type){
        Optional<User> user=repository.findByEmail(email);
        if(user.isPresent()){
            throw new UserAlreadyExistException("User with email: "+email+" already exists");
        }
        repository.save(new User(email,password,type));
        return;
    }
}
