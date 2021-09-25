package com.omega.bioway.cart.dataaccess;
import com.omega.bioway.cart.crosscutting.entities.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {
}

