package com.omega.bioway.apigateway.service.products.SOAP;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.example.consumingwebservice.wsdl.GetProductsResponse;
import com.example.consumingwebservice.wsdl.GetProductsRequest;

public class ProductsClient extends WebServiceGatewaySupport {
    
    public GetProductsResponse getProducts(String productName, String productType, String supplierId){
        GetProductsRequest request = new GetProductsRequest();
        request.setProductName(productName);
        request.setProductType(productType);
        request.setSupplierId(supplierId);
        GetProductsResponse response = (GetProductsResponse) getWebServiceTemplate()
            .marshalSendAndReceive("http://ProductSearch:8080/app/ProductSearch/products.wsdl", request,
                new SoapActionCallback(
                    "http://spring.io/guides/gs-producing-web-service/getAllProductsRequest"));
        return response;
    }

}
