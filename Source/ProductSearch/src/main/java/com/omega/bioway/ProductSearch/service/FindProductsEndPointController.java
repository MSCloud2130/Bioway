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

import io.spring.guides.gs_producing_web_service.GetProductsRequest;
import io.spring.guides.gs_producing_web_service.GetProductsResponse;
import io.spring.guides.gs_producing_web_service.ProductSOAP;

@Endpoint
public class FindProductsEndPointController {
    
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    @Autowired
    private ProductSearcher productSearcher;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getAllProducts(@RequestPayload GetProductsRequest request){
        GetProductsResponse response = new GetProductsResponse();
        String name = request.getProductName();
        String type = request.getProductType();
        String supplierId = request.getSupplierId();
        List<Product> products = productSearcher.execute(name, type, supplierId);
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
    public GetProductsResponse handleProductNotFoundException(@RequestPayload GetProductsRequest request){
        GetProductsResponse response = new GetProductsResponse();
        List<ProductSOAP> products = response.getProducts();
        products = new ArrayList<ProductSOAP>();
        return response;
    }
}
