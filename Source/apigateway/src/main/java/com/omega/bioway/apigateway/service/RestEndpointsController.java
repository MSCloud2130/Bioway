package com.omega.bioway.apigateway.service;

import com.omega.bioway.apigateway.entities.customer.Customer;
import com.omega.bioway.apigateway.entities.identity.CreateUserRequest;
import com.omega.bioway.apigateway.entities.product.CreateServiceRequest;
import com.omega.bioway.apigateway.entities.product.ModifyServiceRequest;
import com.omega.bioway.apigateway.service.supplier.RegisterRequest;
import com.omega.bioway.apigateway.entities.payment.CreatePaymentRequest;
import com.omega.bioway.apigateway.entities.payment.Payment;
import com.omega.bioway.apigateway.entities.supplier.CreateSupplierRequest;
import com.omega.bioway.apigateway.entities.supplier.ModifySupplierRequest;
import com.omega.bioway.apigateway.entities.supplier.Supplier;
import com.omega.bioway.apigateway.entities.identity.LogInRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.soap.client.SoapFaultClientException;

import java.util.List;

import com.example.consumingwebservice.wsdl.ProductSOAP;
import com.omega.bioway.apigateway.service.products.SOAP.ProductsClient;

@RestController
public class RestEndpointsController {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //cart
    @PostMapping(value = "cart")
    public ResponseEntity createCart(){
        try {
            return restTemplate.postForEntity("http://cart-service/cart/", null, String.class, "");
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }


    @GetMapping(value="cart/{cartId}",produces = "application/json")
    public ResponseEntity getCart(@PathVariable String cartId)
    {
        try {
            return restTemplate.getForEntity("http://cart-service/cart/{cartId}", Object.class, cartId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }


    @PutMapping(value="cart/{cartId}/items", consumes = "application/json", produces = "application/json")
    public ResponseEntity AddItenCart(@PathVariable String cartId,@RequestBody AddItemRequest request)
    {
        try {
            return restTemplate.exchange("http://cart-service/cart/{cartId}/items", HttpMethod.PUT, new HttpEntity<>(request), Object.class, cartId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }


    @PatchMapping(value="cart/{cartId}/items", consumes = "application/json", produces = "application/json")
    public ResponseEntity editQuantityItemCart(@PathVariable String cartId, @RequestBody EditItemRequest request)
    {   
        try {
            return restTemplate.exchange("http://cart-service/cart/{cartId}/items", HttpMethod.PATCH, new HttpEntity<>(request), Object.class, cartId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }


    @DeleteMapping(value="cart/{cartId}/items/{productId}",produces = "application/json")
    public ResponseEntity deleteItemCart(@PathVariable String cartId, @PathVariable String productId)
    {   
        try {
            return restTemplate.exchange("http://cart-service/cart/{cartId}/items/{productId}", HttpMethod.DELETE, null, Object.class, cartId,productId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }


    //customers

    @GetMapping(value = "/customers", produces = "application/json")
    public ResponseEntity getCustomers(){
        System.out.println("SOLICITUD GET CUSTOMERS");
        try {
            return restTemplate.exchange("http://CUSTOMER-SERVICE/customers", HttpMethod.GET, null, Object.class);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @GetMapping(value = "/customers/{customer_id}", produces = "application/json")
    public ResponseEntity getCustomer(@PathVariable(value = "customer_id") String customerId){
        try {
            return restTemplate.exchange("http://customer-service/customers/{customerId}", HttpMethod.GET, null, Object.class, customerId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @PostMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCustomer(@RequestBody RegisterRequest request){
        ResponseEntity response;
        Customer customer = new Customer(request.getName(), request.getEmail(), request.getAge(),
                request.getPicture(), request.getDescription(), request.getPassword());
        Customer createdCustomer = null;
        try{
            createdCustomer = restTemplate.postForObject("http://customer-service/customers", new HttpEntity<>(customer), Customer.class);
        }catch(HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
        CreateUserRequest registerRequest = new CreateUserRequest(createdCustomer.getId(),createdCustomer.getEmail(),
                request.getPassword(),request.getType());
        try{
            response=restTemplate.exchange("http://identity-service/register", HttpMethod.POST, new HttpEntity<>(registerRequest), Object.class);
        }catch(HttpClientErrorException e){
            System.out.println(e.getLocalizedMessage());
            restTemplate.exchange("http://customer-service/customers/{customer_id}", HttpMethod.DELETE,null, Object.class,createdCustomer.getId());
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PutMapping(value = "/customers/{customer_id}")
    public ResponseEntity modifyCustomer(@RequestBody Customer customer, @PathVariable(value = "customer_id") String customerId){
        try {
            return restTemplate.exchange("http://customer-service/customers/{customerId}/", HttpMethod.PUT, new HttpEntity<>(customer), Object.class, customerId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @DeleteMapping(value = "/customers/{customer_id}")
    public ResponseEntity deleteCustomer(@PathVariable(value = "customer_id") String customerId){
        try {
            return restTemplate.exchange("http://customer-service/customers/{customerId}/", HttpMethod.DELETE, null, Object.class, customerId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    //Payments

    @PostMapping(value = "/payments", produces = "application/json")
    public ResponseEntity payServices(@RequestBody CreatePaymentRequest request){
        Payment payment = new Payment();
        payment.setCard(request.getCard());
        payment.setValue(request.getPurchase().getTotal());
        try {
            restTemplate.postForEntity("http://payment-service/payment", payment, Object.class);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
        try {
            restTemplate.exchange("http://cart-service/cart/{cartId}/items/{productId}", HttpMethod.DELETE, null, Object.class, request.getCartId(), request.getPurchase().getProduct().getId() );
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
        try {
            return restTemplate.exchange("http://purchase-service/purchases", HttpMethod.POST, new HttpEntity<>(request.getPurchase()), Object.class);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    //products
    @Autowired
    private ProductsClient soapClient;


    @GetMapping(value = "products", produces = "application/json")
    public ResponseEntity getProducts(@RequestParam(required = false) String productName, @RequestParam(required = false) String productType, @RequestParam(required = false) String supplierId){
        try{
            return new ResponseEntity<List<ProductSOAP>>(soapClient.getProducts(productName, productType, supplierId).getProducts(), HttpStatus.OK);
        }catch (SoapFaultClientException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "products/{id}", produces = "application/json")
    public ResponseEntity getProduct(@PathVariable String id){
        try {
            return restTemplate.getForEntity("http://products-service/products/{id}", Object.class, id);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }

    }

    @PostMapping(value = "products/{productId}/questions", consumes = "application/json")
    public ResponseEntity createQuestion(@PathVariable String productId, @RequestBody CreateQuestionRequest request){
        try {
            return restTemplate.postForEntity("http://products-service/products/{productId}/questions", request, Object.class, productId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @PostMapping(value = "products/{productId}/ratings", consumes = "application/json")
    public ResponseEntity createRating(@PathVariable String productId, @RequestBody CreateRatingRequest request){
        try {
            return restTemplate.postForEntity("http://products-service/products/{productId}/ratings", request, Object.class, productId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @PutMapping(value = "products/{productId}/questions/{questionId}/answer", consumes = "application/json")
    public ResponseEntity submitAnswer(@PathVariable String productId, @PathVariable String questionId, @RequestBody SubmitAnswerRequest request) {
        try {
            return restTemplate.exchange("http://products-service/products/{productId}/questions/{questionId}/answer", HttpMethod.PUT, new HttpEntity<>(request), Object.class, productId, questionId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @PostMapping(value = "products")
    public ResponseEntity createProduct(@RequestBody CreateServiceRequest request){
        try {
            return restTemplate.exchange("http://products-service/products", HttpMethod.POST, new HttpEntity<>(request), Object.class);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @PutMapping(value = "products/{productId}")
    public ResponseEntity modifyProduct(@PathVariable String productId, @RequestBody ModifyServiceRequest request){
        try {
            return restTemplate.exchange("http://products-service/products/{productId}", HttpMethod.PUT, new HttpEntity<>(request), Object.class, productId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @DeleteMapping(value = "products/{productId}")
    public ResponseEntity deleteProduct(@PathVariable String productId){
        try {
            return restTemplate.exchange("http://products-service/products/{productId}", HttpMethod.DELETE, null, Object.class,productId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }


    //supplier
    @PostMapping(value = "/supplier", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerSupplier(@RequestBody RegisterRequest request){
        ResponseEntity response;
        Supplier supplier=null;
        CreateSupplierRequest supplierRequest=new CreateSupplierRequest(request.getEmail(),request.getName(),
                request.getAge(),request.getPicture(),request.getDescription(),request.getPhone(),request.getWebPage(),
                request.getSocialAccounts());
        try{
            supplier=restTemplate.postForObject("http://supplier-service/supplier", new HttpEntity<>(supplierRequest), Supplier.class);
        }catch(HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
        CreateUserRequest registerRequest = new CreateUserRequest(supplier.getId(),supplier.getEmail(),
                request.getPassword(),request.getType());
        try{
            response=restTemplate.exchange("http://identity-service/register", HttpMethod.POST, new HttpEntity<>(registerRequest), Object.class);
        }catch(HttpClientErrorException e){
            restTemplate.exchange("http://supplier-service/supplier/{supplier_id}", HttpMethod.DELETE,null, Object.class,supplier.getId());
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }catch (IllegalStateException e){
            restTemplate.exchange("http://supplier-service/supplier/{supplier_id}", HttpMethod.DELETE,null, Object.class,supplier.getId());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @DeleteMapping(value = "/supplier/{supplier_id}")
    public ResponseEntity deleteSupplier(@PathVariable(value = "supplier_id")String supplierId){
        try{
            restTemplate.exchange("http://supplier-service/supplier/{supplier_id}", HttpMethod.DELETE, null, Object.class,supplierId);
            return restTemplate.exchange("http://identity-service/remove/{user_id}", HttpMethod.DELETE, null, Object.class,supplierId);
        }catch(HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @GetMapping(value = "/supplier")
    public ResponseEntity getAllSupplier(){
        try{
            return restTemplate.exchange("http://supplier-service/supplier", HttpMethod.GET, null, Object.class);
        }catch(HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @GetMapping(value = "/supplier/{supplier_id}")
    public ResponseEntity getSupplier(@PathVariable(value = "supplier_id")String supplierId){
        try{
            return restTemplate.exchange("http://supplier-service/supplier/{supplier_id}", HttpMethod.GET, null, Object.class,supplierId);
        }catch(HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @PutMapping(value = "/supplier/{supplier_id}")
    public ResponseEntity modifySupplier(@PathVariable(value = "supplier_id")String supplierId, @RequestBody ModifySupplierRequest request){
        try{
            return restTemplate.exchange("http://supplier-service/supplier/{supplier_id}", HttpMethod.PUT, new HttpEntity<>(request), Object.class, supplierId);
        }catch(HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    //user
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody LogInRequest request){
        try{
            return restTemplate.exchange("http://identity-service/login", HttpMethod.POST, new HttpEntity<>(request), Object.class);
        }catch(HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }




    //product classes
    static class CreateQuestionRequest {

        private String questionContent;
        private String userId;
        private String userName;

        public CreateQuestionRequest() {
        }

        public String getQuestionContent() {
            return questionContent;
        }

        public void setQuestionContent(String questionContent) {
            this.questionContent = questionContent;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    static class CreateRatingRequest {

        private int value;
        private String comment;
        private String userId;
        private String userName;
        private String resource;

        public CreateRatingRequest() {
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getComment() {
            return this.comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getResource() {
            return this.resource;
        }

        public void setResource(String resource) {
            this.resource = resource;
        }
    }

    static class SubmitAnswerRequest {

        private String answerContent;

        public SubmitAnswerRequest() {
        }

        public String getAnswerContent() {
            return answerContent;
        }

        public void setAnswerContent(String answerContent) {
            this.answerContent = answerContent;
        }
    }

    //cart classes
    static class EditItemRequest {

        private String productId;
        private int    quantity;

        public EditItemRequest() {
        }

        public String getProductId() {
            return this.productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return this.quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

    }

    static class AddItemRequest {

        private String productId;
        private double unitPrice;
        private String productUrl;
        private int    quantity;


        public AddItemRequest() {
        }

        public String getProductId() {
            return this.productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductUrl() {
            return this.productUrl;
        }

        public void setProductUrl(String productUrl) {
            this.productUrl = productUrl;
        }


        public int getQuantity() {
            return this.quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }



        public double getUnitPrice() {
            return this.unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }
    }
}
