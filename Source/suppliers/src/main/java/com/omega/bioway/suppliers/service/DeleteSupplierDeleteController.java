package com.omega.bioway.suppliers.service;

import com.omega.bioway.suppliers.business.SupplierDeleter;
import com.omega.bioway.suppliers.crosscutting.exceptions.BadRequestException;
import com.omega.bioway.suppliers.crosscutting.exceptions.SupplierNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PathParam;
import java.util.HashMap;

@RestController
@RequestMapping("supplier")
public class DeleteSupplierDeleteController {

    @Autowired
    private SupplierDeleter deleter;

    @DeleteMapping(value = "/{supplier_id}")
    public ResponseEntity execute(@PathParam("supplier_id") String supplierId){
        deleter.execute(supplierId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ExceptionHandler(SupplierNotFoundException.class)
    public ResponseEntity<HashMap> handleSupplierNotFoundException(SupplierNotFoundException exception){
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
}
