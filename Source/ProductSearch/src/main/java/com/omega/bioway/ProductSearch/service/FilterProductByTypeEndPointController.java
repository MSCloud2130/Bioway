package com.omega.bioway.ProductSearch.service;

import java.util.ArrayList;
import java.util.List;

import com.omega.bioway.ProductSearch.business.ProductTypeFilter;
import com.omega.bioway.ProductSearch.crosscutting.entities.Product;
import com.omega.bioway.ProductSearch.crosscutting.exceptions.ProductsNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.FindProductsByTypeRequest;
import io.spring.guides.gs_producing_web_service.FindProductsByTypeResponse;
import io.spring.guides.gs_producing_web_service.ProductSOAP;

@Endpoint
public class FilterProductByTypeEndPointController {
    
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    @Autowired
    private ProductTypeFilter productTypeFilter;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findProductsByTypeRequest")
    @ResponsePayload
    public FindProductsByTypeResponse findProdutsByType(@RequestPayload FindProductsByTypeRequest request){
        FindProductsByTypeResponse response = new FindProductsByTypeResponse();
        List<Product> products = productTypeFilter.execute(request.getProductType());
        for(Product p : products){
            ProductSOAP pSOAP = new ProductSOAP();
            pSOAP.setId(p.getId());
            pSOAP.setName(p.getName());
            pSOAP.setType(p.getType());
            pSOAP.setUri(p.getUri());
            response.getProducts().add(pSOAP);
        }
        return response;
    }

    @ExceptionHandler(ProductsNotFoundException.class)
    public FindProductsByTypeResponse handleProductNotFoundException(@RequestPayload FindProductsByTypeRequest request){
        FindProductsByTypeResponse response = new FindProductsByTypeResponse();
        List<ProductSOAP> products = response.getProducts();
        products = new ArrayList<ProductSOAP>();
        return response;
    }
}
