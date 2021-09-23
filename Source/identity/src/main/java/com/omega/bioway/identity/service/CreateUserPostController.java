package com.omega.bioway.identity.service;

import com.omega.bioway.identity.business.UserCreator;
import com.omega.bioway.identity.crosscutting.exceptions.BadRequestException;
import com.omega.bioway.identity.crosscutting.exceptions.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("register")
public class CreateUserPostController {

    @Autowired
    private UserCreator userCreator;

    @PostMapping()
    public ResponseEntity execute(@RequestBody CreateUserRequest request){
        List<String> data=userCreator.execute(request.getId(),request.getEmail(),request.getPassword(), request.getType());
        RegisterResponse response=new RegisterResponse(data.get(0),data.get(1));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
        private String id;
        private String email;
        private String password;
        private String type;

        public CreateUserRequest() {
        }

        public CreateUserRequest(String id, String email, String password, String type) {
            this.id = id;
            this.email = email;
            this.password = password;
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

    static class RegisterResponse{
        private String id;
        private String token;

        public RegisterResponse() {
        }

        public RegisterResponse(String id, String token) {
            this.id = id;
            this.token = token;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
