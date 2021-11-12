package com.omega.bioway.apigateway.service.products.SOAP;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ProductsConfiguration {
    
    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        //The package must match the package in <generatePackage> specified in pom.xml
        marshaller.setContextPath("com.example.consumingwebservice.wsdl");
        return marshaller;
    }

    @Bean
    public ProductsClient productsClient(Jaxb2Marshaller marshaller){
        ProductsClient client = new ProductsClient();
        //This is the localtion of the SOAP service
        client.setDefaultUri("http://ProductSearch:8080/app/ProductSearch");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
