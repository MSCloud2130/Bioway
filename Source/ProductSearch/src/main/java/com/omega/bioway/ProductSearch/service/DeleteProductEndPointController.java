package com.omega.bioway.ProductSearch.service;

import com.omega.bioway.ProductSearch.business.ProductDeleter;
import com.omega.bioway.ProductSearch.crosscutting.exceptions.ProductIsNotDeletedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.DeleteProductRequest;
import io.spring.guides.gs_producing_web_service.DeleteProductResponse;

@Endpoint
public class DeleteProductEndPointController {
    
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    @Autowired
    private ProductDeleter productDeleter;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteProductRequest")
    @ResponsePayload
    public DeleteProductResponse createProduct(@RequestPayload DeleteProductRequest request){
        DeleteProductResponse response = new DeleteProductResponse();
        boolean result = productDeleter.execute(request.getProductId());
        response.setResult(result);
        return response;
    }

    @ExceptionHandler(ProductIsNotDeletedException.class)
    public DeleteProductResponse handleProductIsNotDeletedException(@RequestPayload DeleteProductRequest request){
        DeleteProductResponse response = new DeleteProductResponse();
        response.setResult(false);
        return response;
    }
}
