package com.omega.bioway.ProductSearch.service;

import java.util.ArrayList;
import java.util.List;

import com.omega.bioway.ProductSearch.business.ProductSearcher;
import com.omega.bioway.ProductSearch.crosscutting.entities.Product;
import com.omega.bioway.ProductSearch.crosscutting.exceptions.ProductsNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetAllProductsRequest;
import io.spring.guides.gs_producing_web_service.GetAllProductsResponse;
import io.spring.guides.gs_producing_web_service.ProductSOAP;

@Endpoint
public class FindProductsEndPointController {
    
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    @Autowired
    private ProductSearcher productSearcher;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request){
        GetAllProductsResponse response = new GetAllProductsResponse();
        List<Product> products = productSearcher.execute();
        for(Product p : products){
            ProductSOAP pSOAP = new ProductSOAP();
            pSOAP.setId(p.getId());
            pSOAP.setName(p.getName());
            pSOAP.setType(p.getType());
            pSOAP.setUri(p.getUri());
            pSOAP.setSupplierId(p.getSupplierId());
            response.getProducts().add(pSOAP);
        }
        return response;
    }

    @ExceptionHandler(ProductsNotFoundException.class)
    public GetAllProductsResponse handleProductNotFoundException(@RequestPayload GetAllProductsRequest request){
        GetAllProductsResponse response = new GetAllProductsResponse();
        List<ProductSOAP> products = response.getProducts();
        products = new ArrayList<ProductSOAP>();
        return response;
    }
}
