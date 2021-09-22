package com.omega.bioway.suppliers.service;

import com.omega.bioway.suppliers.business.AllSuppliersObtainer;
import com.omega.bioway.suppliers.crosscutting.entities.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("supplier")
public class FindAllSuppliersGetController {

    @Autowired
    private AllSuppliersObtainer allSuppliersObtainer;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity execute(){
        List<Supplier> suppliers=allSuppliersObtainer.execute();
        List<HashMap> response = suppliers.stream().map(a->a.getAllData()).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HashMap> handleException(Exception exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
