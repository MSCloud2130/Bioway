package com.omega.bioway.suppliers.service;

import com.omega.bioway.suppliers.business.SupplierCreator;
import com.omega.bioway.suppliers.crosscutting.entities.Supplier;
import com.omega.bioway.suppliers.crosscutting.exceptions.BadRequestException;
import com.omega.bioway.suppliers.crosscutting.exceptions.SupplierAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("supplier")
public class CreateSupplierPostController {

    @Autowired
    private SupplierCreator creator;

    @PostMapping()
    public ResponseEntity execute(@RequestBody CreateSupplierRequest request){
        Supplier sup= creator.execute(request.getEmail(), request.getName(),request.getAge(),request.getPicture(),
                request.getDescription(), request.getPhone(),request.getWebPage(),request.getSocialAccounts());
        HashMap<String, Object> response=sup.getAllData();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @ExceptionHandler(SupplierAlreadyExistException.class)
    public ResponseEntity<HashMap> handleSupplierAlreadyExistException(SupplierAlreadyExistException exception){
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

    static class CreateSupplierRequest{
        private String email;
        private String name;
        private int age;
        private String picture;
        private String description;
        private String phone;
        private String webPage;
        private List<String> socialAccounts;

        public CreateSupplierRequest() {
        }

        public CreateSupplierRequest(String email, String name, int age, String picture, String description,
                                     String phone, String webPage, List<String> socialAccounts) {
            this.email = email;
            this.name = name;
            this.age = age;
            this.picture = picture;
            this.description = description;
            this.phone = phone;
            this.webPage = webPage;
            this.socialAccounts = socialAccounts;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getWebPage() {
            return webPage;
        }

        public void setWebPage(String webPage) {
            this.webPage = webPage;
        }

        public List<String> getSocialAccounts() {
            return socialAccounts;
        }

        public void setSocialAccounts(List<String> socialAccounts) {
            this.socialAccounts = socialAccounts;
        }
    }
}
