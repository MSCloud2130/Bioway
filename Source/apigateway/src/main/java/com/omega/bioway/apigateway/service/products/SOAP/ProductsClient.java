package com.omega.bioway.apigateway.service.products.SOAP;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.example.consumingwebservice.wsdl.GetProductsResponse;
import com.example.consumingwebservice.wsdl.GetProductsRequest;
import com.example.consumingwebservice.wsdl.CreateProductRequest;
import com.example.consumingwebservice.wsdl.CreateProductResponse;
import com.example.consumingwebservice.wsdl.DeleteProductRequest;
import com.example.consumingwebservice.wsdl.DeleteProductResponse;
import com.example.consumingwebservice.wsdl.EditProductRequest;
import com.example.consumingwebservice.wsdl.EditProductResponse;

public class ProductsClient extends WebServiceGatewaySupport {
    
    public GetProductsResponse getProducts(String productName, String productType, String supplierId){
        GetProductsRequest request = new GetProductsRequest();
        request.setProductName(productName);
        request.setProductType(productType);
        request.setSupplierId(supplierId);
        GetProductsResponse response = (GetProductsResponse) getWebServiceTemplate()
            .marshalSendAndReceive("http://localhost:8081/ProductSearch/products.wsdl", request,
                new SoapActionCallback(
                    "http://spring.io/guides/gs-producing-web-service/getAllProductsRequest"));
        return response;
    }

    public CreateProductResponse createProduct(String productId, String supplierId, String productName, String productType, String productUri){
        CreateProductRequest request = new CreateProductRequest();
        request.setProductId(productId);
        request.setSupplierId(supplierId);
        request.setProductName(productName);
        request.setProductType(productType);
        request.setProductUri(productUri);
        CreateProductResponse response = (CreateProductResponse) getWebServiceTemplate()
            .marshalSendAndReceive("http://localhost:8081/ProductSearch/products.wsdl", request,
            new SoapActionCallback(
                "http://spring.io/guides/gs-producing-web-service/getAllProductsRequest"));
        return response;
    }

    public EditProductResponse editProduct(String productId, String productName, String productUri){
        EditProductRequest request = new EditProductRequest();
        request.setProductId(productId);
        request.setProductName(productName);
        request.setProductUri(productUri);
        EditProductResponse response = (EditProductResponse) getWebServiceTemplate()
            .marshalSendAndReceive("http://localhost:8081/ProductSearch/products.wsdl", request,
            new SoapActionCallback(
                "http://spring.io/guides/gs-producing-web-service/getAllProductsRequest"));
        return response;
    }

    public DeleteProductResponse deleteProduct(String productId){
        DeleteProductRequest request = new DeleteProductRequest();
        request.setProductId(productId);
        DeleteProductResponse response = (DeleteProductResponse) getWebServiceTemplate()
        .marshalSendAndReceive("http://localhost:8081/ProductSearch/products.wsdl", request,
        new SoapActionCallback(
            "http://spring.io/guides/gs-producing-web-service/getAllProductsRequest"));
        return response;
    }

}
