package com.omega.bioway.identity.service;

import com.omega.bioway.identity.business.UserCreator;
import com.omega.bioway.identity.crosscutting.exceptions.BadRequestException;
import com.omega.bioway.identity.crosscutting.exceptions.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("register")
public class CreateUserPostController {

    @Autowired
    private UserCreator userCreator;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity execute(@RequestBody CreateUserRequest request){
        userCreator.execute(request.getEmail(),request.getPassword(), request.getType());
        return ResponseEntity.status(HttpStatus.CREATED).body(null);

    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<HashMap> handleUserAlreadyExistException(UserAlreadyExistException exception){
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

    static class CreateUserRequest{
        private String email;
        private String password;
        private String type;

        public CreateUserRequest() {
        }

        public CreateUserRequest(String email, String password, String type) {
            this.email = email;
            this.password = password;
            this.type = type;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
