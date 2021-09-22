package com.omega.bioway.identity.business;

import com.omega.bioway.identity.crosscutting.entities.User;
import com.omega.bioway.identity.crosscutting.exceptions.BadRequestException;
import com.omega.bioway.identity.crosscutting.exceptions.UserNotFoundException;
import com.omega.bioway.identity.dataaccess.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthenticator {

    private UserRepository repository;

    @Autowired
    public UserAuthenticator(UserRepository repository) {
        this.repository = repository;
    }

    public String execute(String email, String password){
        Optional<User> user=repository.findByEmail(email);
        if(user.isPresent()){
            if(user.get().getPassword().equals(password)){
                //TODO: auth
                return null;
            }else{
                throw new BadRequestException("Incorrect password for user: "+email);
            }
        }else{
            throw new UserNotFoundException("User with email: "+email+" not found");
        }
    }
}
