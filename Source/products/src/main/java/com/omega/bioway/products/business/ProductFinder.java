package com.omega.bioway.products.business;

import com.omega.bioway.products.crosscutting.entities.*;
import com.omega.bioway.products.crosscutting.exceptions.ProductNotFoundException;
import com.omega.bioway.products.dataaccess.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductFinder {

    @Autowired
    private ProductRepository repository;

    public Product execute(String id){
        Optional<Product> requestedProduct = repository.findById(id);
        if (requestedProduct.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }
        return requestedProduct.get();
    }

    /*
    private void agregarDatos() {
        ProductSupplier supplier = new ProductSupplier();
        supplier.setName("Pedro Perez");
        supplier.setId("cd96b919-d54d-43ca-b648-b90153def1b2");
        supplier.setResource("http://localhost:8080/suppliers/cd96b919-d54d-43ca-b648-b90153def1b2");

        Accommodation accommodation = new Accommodation();
        accommodation.setName("Hotel Santa Marta");
        accommodation.setSupplier(supplier);
        List<String> acPictures = new ArrayList<>();
        acPictures.add("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.espanol.marriott.com%2Fhotels%2Ftravel%2Fsmrmc-santa-marta-marriott-resort-playa-dormida%2F&psig=AOvVaw39RnJb6G5Fg4UyxIHgHJ7c&ust=1632577237752000&source=images&cd=vfe&ved=0CAgQjRxqFwoTCNDYt6Xel_MCFQAAAAAdAAAAABAD");
        acPictures.add("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.tripadvisor.co%2FHotel_Review-g297484-d7810972-Reviews-Del_Mar_Hotel-Santa_Marta_Santa_Marta_Municipality_Magdalena_Department.html&psig=AOvVaw39RnJb6G5Fg4UyxIHgHJ7c&ust=1632577237752000&source=images&cd=vfe&ved=0CAgQjRxqFwoTCNDYt6Xel_MCFQAAAAAdAAAAABAI");
        accommodation.setPictures(acPictures);
        accommodation.setDescription("Hermoso hotel con playa privada.");
        accommodation.setPrice(500000);
        accommodation.setType("ACCOMMODATION");
        List<Update> acUpdates = new ArrayList<>();
        Update acUpdate = new Update();
        acUpdate.setDate(LocalDateTime.now());
        acUpdate.setTitle("Promoción Especial!");
        acUpdate.setContent("¡Descuento del 20% hasta el 15 de octubre!¡No pierdas la oportunidad!");
        acUpdates.add(acUpdate);
        accommodation.setUpdates(acUpdates);
        List<Faq> acFaqs = new ArrayList<>();
        Faq acFaq1 = new Faq();
        acFaq1.setQuestion("¿Puedo llevar invitados a la playa?");
        acFaq1.setAnswer("No, solamente pueden ingresar los huéspedes del hotel.");
        acFaqs.add(acFaq1);
        accommodation.setFaqs(acFaqs);
        List<String> acLinks = new ArrayList<>();
        acLinks.add("https://www.youtube.com/");
        acLinks.add("https://www.facebook.com/");
        accommodation.setLinks(acLinks);
        accommodation.setCheckIn(LocalDateTime.now().plusMonths(2));
        accommodation.setCheckOut(LocalDateTime.now().plusMonths(2).plusDays(7));
        Location acLocation = new Location();
        acLocation.setLatitude(11.243011201972344);
        acLocation.setLongitude(-74.21494039215254);
        acLocation.setAddress("Cra. 1c ##18-25, Santa Marta, Magdalena");
        accommodation.setLocation(acLocation);
        repository.save(accommodation);

        EcoTour ecoTour = new EcoTour();
        ecoTour.setName("Tour Parque Tayrona");
        ecoTour.setSupplier(supplier);
        List<String> etPictures = new ArrayList<>();
        etPictures.add("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.parquetayrona.com%2Fes%2Frazones-para-viajar-al-parque-tayrona%2F&psig=AOvVaw3TcdAIN5AzMmc4LW_FkyRt&ust=1632578468496000&source=images&cd=vfe&ved=0CAgQjRxqFwoTCLCs1_Hil_MCFQAAAAAdAAAAABAD");
        ecoTour.setPictures(etPictures);
        ecoTour.setDescription("Tour para entrar en contacto con la naturaleza.");
        ecoTour.setPrice(200000);
        ecoTour.setType("ECOTOUR");
        List<Update> etUpdates = new ArrayList<>();
        Update etUpdate = new Update();
        etUpdate.setDate(LocalDateTime.now());
        etUpdate.setTitle("Nuevo Trayecto");
        etUpdate.setContent("Desde el 1 de octubre, el trayecto del tour incluirá un paseo por la playa.");
        etUpdates.add(acUpdate);
        ecoTour.setUpdates(etUpdates);
        List<Faq> etFaqs = new ArrayList<>();
        Faq etFaq1 = new Faq();
        etFaq1.setQuestion("¿Hay descanso para comer algo?");
        etFaq1.setAnswer("Sí, el tour tiene una parada en un resutarante para comer.");
        etFaqs.add(acFaq1);
        ecoTour.setFaqs(etFaqs);
        List<String> etLinks = new ArrayList<>();
        etLinks.add("https://www.youtube.com/");
        etLinks.add("https://www.facebook.com/");
        ecoTour.setLinks(etLinks);
        ecoTour.setStartTime(LocalDateTime.now().plusWeeks(2));
        ecoTour.setEndTime(LocalDateTime.now().plusWeeks(2).plusHours(5));
        Location etLocation = new Location();
        etLocation.setLatitude(11.281764151608279);
        etLocation.setLongitude(-73.91478437263598);
        etLocation.setAddress("Entrada Parque Tayrona, Santa Marta, Magdalena");
        etLocation.setLatitude(11.281764151608279);
        etLocation.setLongitude(-73.91478437263598);
        etLocation.setAddress("Entrada Parque Tayrona, Santa Marta, Magdalena");
        ecoTour.setDepartureLocation(etLocation);
        ecoTour.setArrivalLocation(etLocation);
        repository.save(ecoTour);
    }
     */
}
