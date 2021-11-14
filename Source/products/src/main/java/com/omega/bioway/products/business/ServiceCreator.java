package com.omega.bioway.products.business;

import com.omega.bioway.products.crosscutting.entities.*;
import com.omega.bioway.products.crosscutting.exceptions.BadRequestException;
import com.omega.bioway.products.dataaccess.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceCreator {

    private ProductRepository repository;

    @Autowired
    public ServiceCreator(ProductRepository repository) {
        this.repository = repository;
    }

    public String execute(CreateServiceRequest request){
        boolean exist=true;
        Product product=null;
        while (exist){
            if(request.getType().equalsIgnoreCase("TRANSPORT")){
                product=new Transport();
                product.setType("Transport");
            }else if(request.getType().equalsIgnoreCase("ACCOMMODATION")){
                product=new Accommodation();
                product.setType("Accommodation");
            }else if(request.getType().equalsIgnoreCase("ECOTOUR")){
                product=new EcoTour();
                product.setType("EcoTour");
            }else if(request.getType().equalsIgnoreCase("FOODANDDRINK")){
                product=new FoodAndDrink();
                product.setType("FoodAndDrink");
            }else{
                throw  new BadRequestException("Service type not allowed: "+request.getType());
            }
            try{
                Optional<Product> p=this.repository.findById(product.getId());
                if(p==null||p.isEmpty()){
                    exist=false;
                }
            }catch (Exception e){
                exist=false;
            }
        }
        if(request.getPrice()<0 | request.getName()==null || request.getDescription()==null){
            throw new BadRequestException("Error when assigning basic service/product information");
        }

        product.setPrice(request.getPrice());
        product.setName(request.getName());
        product.setLinks(request.getLinks());
        product.setPictures(request.getPictures());
        product.setDescription(request.getDescription());

        try{
            if(product instanceof Transport){
                ((Transport) product).setTransportType(request.getTransportType().get());
                ((Transport) product).setDepartureTime(request.getDepartureTime().get());
                ((Transport) product).setArrivalTime(request.getArrivalTime().get());
                ((Transport) product).setDepartureLocation(request.getDepartureLocation().get());
                ((Transport) product).setArrivalLocation(request.getArrivalLocation().get());
            }else if(product instanceof Accommodation){
                ((Accommodation) product).setCheckIn(request.getCheckIn().get());
                ((Accommodation) product).setCheckOut(request.getCheckOut().get());
                ((Accommodation) product).setLocation(request.getLocation().get());
            }else if(product instanceof EcoTour){
                ((EcoTour) product).setDepartureTime(request.getDepartureTime().get());
                ((EcoTour) product).setArrivalTime(request.getArrivalTime().get());
                ((EcoTour) product).setDepartureLocation(request.getDepartureLocation().get());
                ((EcoTour) product).setArrivalLocation(request.getArrivalLocation().get());
            }else if(product instanceof FoodAndDrink){
                ((FoodAndDrink) product).setIncludedItems(request.getIncludedItems().get());
            }
        }catch (Exception e){
            throw new BadRequestException("Error when assigning information for service type "+product.getType());
        }
        repository.save(product);
        return product.getId();
    }
}
