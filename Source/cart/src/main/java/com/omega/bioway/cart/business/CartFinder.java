package com.omega.bioway.cart.business;
import com.omega.bioway.cart.crosscutting.entities.Cart;
import com.omega.bioway.cart.crosscutting.exceptions.CartNotFoundException;
import com.omega.bioway.cart.dataaccess.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;


public class CartFinder {
    @Autowired
    private CartRepository repository;

    public Cart execute(String cartId){
        Optional<Cart> requestedCart = repository.findById(cartId);
        if (requestedCart.isEmpty()) {
            throw new CartNotFoundException("Cart not found");
        }
        return requestedCart.get();
    }
}
