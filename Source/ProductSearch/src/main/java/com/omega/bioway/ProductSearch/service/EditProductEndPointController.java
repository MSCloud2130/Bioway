package com.omega.bioway.ProductSearch.service;

import com.omega.bioway.ProductSearch.business.ProductEditor;
import com.omega.bioway.ProductSearch.crosscutting.exceptions.ProductIsNotEditedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.EditProductRequest;
import io.spring.guides.gs_producing_web_service.EditProductResponse;

@Endpoint
public class EditProductEndPointController {
    
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    @Autowired
    private ProductEditor productEditor;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "editProductRequest")
    @ResponsePayload
    public EditProductResponse editProduct(@RequestPayload EditProductRequest request){
        EditProductResponse response = new EditProductResponse();
        boolean result = productEditor.execute(request.getProductId(), request.getProductName(), request.getProductUri());
        response.setResult(result);
        return response;
    }

    @ExceptionHandler(ProductIsNotEditedException.class)
    public EditProductResponse handleProductDidNotSaveException(@RequestPayload EditProductRequest request){
        EditProductResponse response = new EditProductResponse();
        response.setResult(false);
        return response;
    }
}
