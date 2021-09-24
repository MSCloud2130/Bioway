package com.omega.bioway.cart.business;
import com.omega.bioway.cart.crosscutting.entities.Cart;
import com.omega.bioway.cart.crosscutting.entities.Item;
import com.omega.bioway.cart.crosscutting.exceptions.ItemNotFoundException;
import com.omega.bioway.cart.dataaccess.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemRemover {
    @Autowired
    private CartRepository repository;
    @Autowired
    private CartFinder cartFinder;

    public void execute(String productId,String cartId) {
        Cart cart = cartFinder.execute(cartId);
        Item removed = cart.removeItem(productId);

        if(removed == null)
            throw new ItemNotFoundException("Item not found");
        
        cart.setTotalCost(cart.getTotalCost()-(removed.getQuantity()*removed.getUnitPrice()));
        repository.save(cart);
    }
}
