package com.omega.bioway.ProductSearch.service;

import com.omega.bioway.ProductSearch.business.ProductCreator;
import com.omega.bioway.ProductSearch.crosscutting.exceptions.ProductDidNotSaveException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.CreateProductRequest;
import io.spring.guides.gs_producing_web_service.CreateProductResponse;

@Endpoint
public class CreateProductEndPointController {
    
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    @Autowired
    private ProductCreator productCreator;
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createProductRequest")
    @ResponsePayload
    public CreateProductResponse createProduct(@RequestPayload CreateProductRequest request){
        CreateProductResponse response = new CreateProductResponse();
        boolean result = productCreator.execute(request.getProductId(), request.getSupplierId(), request.getProductName(), request.getProductType(), request.getProductUri());
        response.setResult(result);
        return response;
    }

    @ExceptionHandler(ProductDidNotSaveException.class)
    public CreateProductResponse handleProductDidNotSaveException(@RequestPayload CreateProductRequest request){
        CreateProductResponse response = new CreateProductResponse();
        response.setResult(false);
        return response;
    }

}
