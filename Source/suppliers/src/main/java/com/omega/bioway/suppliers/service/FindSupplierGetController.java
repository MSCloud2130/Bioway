package com.omega.bioway.suppliers.service;

import com.omega.bioway.suppliers.business.SupplierFinder;
import com.omega.bioway.suppliers.crosscutting.entities.Supplier;
import com.omega.bioway.suppliers.crosscutting.exceptions.BadRequestException;
import com.omega.bioway.suppliers.crosscutting.exceptions.SupplierAlreadyExistException;
import com.omega.bioway.suppliers.crosscutting.exceptions.SupplierNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PathParam;
import java.util.HashMap;

@RestController
@RequestMapping("supplier")
public class FindSupplierGetController {

    @Autowired
    private SupplierFinder finder;

    @GetMapping(value = "/{supplier_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity execute(@PathParam("supplier_id") String supplierId){
        Supplier supplier=finder.execute(supplierId);
        HashMap<String,String> response=supplier.getAllData();
        return ResponseEntity.status(HttpStatus.OK).body(response);

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
