package com.omega.bioway.cart.business;
import com.omega.bioway.cart.crosscutting.entities.Cart;
import com.omega.bioway.cart.crosscutting.entities.ItemEdited;
import com.omega.bioway.cart.crosscutting.exceptions.ItemNotFoundException;
import com.omega.bioway.cart.dataaccess.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ItemQuantityEdit {
    @Autowired
    private CartRepository repository;
    @Autowired
    private CartFinder cartFinder;

    public void execute(String productId, String cartId ,int quantity) {

        if(quantity==0)
        {
            ItemRemover aux = new ItemRemover();
            aux.execute(productId, cartId);
            return;
        }

        Cart cart = cartFinder.execute(cartId);
        ItemEdited temp = cart.editItem(productId,quantity);

        if(temp == null)
            throw new ItemNotFoundException("Item not found");
        if(temp.getCurrentQuantity() == quantity) return;
            
        int totalQuantity = Math.abs(quantity-temp.getCurrentQuantity());

        if(temp.getgreaterQuantity())    
            cart.setTotalCost(cart.getTotalCost()+(temp.getEdited().getUnitPrice()*totalQuantity));
        else
            cart.setTotalCost(cart.getTotalCost()-(temp.getEdited().getUnitPrice()*totalQuantity));
        repository.save(cart);
    }
}
