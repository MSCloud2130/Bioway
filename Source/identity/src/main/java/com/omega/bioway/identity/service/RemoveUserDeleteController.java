package com.omega.bioway.identity.service;


import com.omega.bioway.identity.business.UserDeleter;
import com.omega.bioway.identity.crosscutting.exceptions.BadRequestException;
import com.omega.bioway.identity.crosscutting.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("remove")
public class RemoveUserDeleteController {

    @Autowired
    private UserDeleter deleter;

    @DeleteMapping(value = "/{user_id}")
    public ResponseEntity execute(@PathVariable(value="user_id") String userId){
        deleter.execute(userId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HashMap> handleUserNotFoundException(UserNotFoundException exception){
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
}
