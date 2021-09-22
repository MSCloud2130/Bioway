package com.omega.bioway.identity.service;

import com.omega.bioway.identity.business.UserAuthenticator;
import com.omega.bioway.identity.crosscutting.exceptions.BadRequestException;
import com.omega.bioway.identity.crosscutting.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("login")
public class AuthenticateUserPostController {

    @Autowired
    private UserAuthenticator authenticator;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity execute(@RequestBody LogInRequest request){
        String token=authenticator.execute(request.getEmail(), request.getPassword());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(token);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HashMap> handleUserNotFoundException(UserNotFoundException exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<HashMap> handleBadRequestException(BadRequestException exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HashMap> handleException(Exception exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    static class LogInRequest{
        private String email;
        private String password;

        public LogInRequest() {
        }

        public LogInRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
