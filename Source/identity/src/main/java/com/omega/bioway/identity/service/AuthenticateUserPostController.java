package com.omega.bioway.identity.service;

import com.omega.bioway.identity.business.UserAuthenticator;
import com.omega.bioway.identity.crosscutting.exceptions.BadRequestException;
import com.omega.bioway.identity.crosscutting.exceptions.UserNotFoundException;
import com.omega.bioway.identity.crosscutting.security.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("login")
public class AuthenticateUserPostController {

    @Autowired
    private UserAuthenticator authenticator;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity execute(@RequestBody LogInRequest request){
        List<String> data=authenticator.execute(request.getEmail(), request.getPassword());
        HttpStatus status =HttpStatus.ACCEPTED;
        LogInResponse response=null;
        if(data==null){
            status=HttpStatus.UNAUTHORIZED;
        }else{
            response=new LogInResponse(data.get(0),data.get(1));
        }
        return ResponseEntity.status(status).body(response);
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
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    static class LogInRequest{
        private String id;
        private String email;
        private String password;

        public LogInRequest() {
        }

        public LogInRequest(String id, String email, String password) {
            this.id = id;
            this.email = email;
            this.password = password;
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
    }

    static class LogInResponse{
        private String id;
        private String token;

        public LogInResponse() {
        }

        public LogInResponse(String id, String token) {
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
