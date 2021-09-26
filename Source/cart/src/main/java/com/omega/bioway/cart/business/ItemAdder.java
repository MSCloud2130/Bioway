package com.omega.bioway.cart.business;
import com.omega.bioway.cart.crosscutting.entities.Cart;
import com.omega.bioway.cart.crosscutting.entities.Item;
import com.omega.bioway.cart.dataaccess.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemAdder {

    @Autowired
    private CartRepository repository;
    @Autowired
    private CartFinder cartFinder;

    @Autowired
    private ItemQuantityEdit itemQuantityEdit;

    public void execute(String productId, double unitPrice, String cartId, String productUrl, int quantity) {
        Cart cart = cartFinder.execute(cartId);


        if(cart.isInTheCar(productId))
            itemQuantityEdit.execute(productId, cartId, quantity);
        else
        {
            cart.addItem(new Item(productId,productUrl,quantity,unitPrice));
            cart.setTotalCost(cart.getTotalCost()+(unitPrice*quantity));
            repository.save(cart);
        }
    }

}
