package com.omega.bioway.cart.business;

import com.omega.bioway.cart.crosscutting.entities.Cart;
import com.omega.bioway.cart.dataaccess.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartCreate {
    @Autowired
    private CartRepository repository;
    
    public String execute()
    {
        Cart cart = new Cart();
        repository.save(cart);
        return cart.getCartId();
    }
}
