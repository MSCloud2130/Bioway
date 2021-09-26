package com.omega.bioway.cart.crosscutting.entities;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "cart")
public class Cart {
    @Id
    private String  cartId;
    private double totalCost;
    private List<Item> items;
    


    public Cart()
    {
        this.cartId = UUID.randomUUID().toString();
        this.totalCost = 0;
        this.items = new ArrayList<>();
    }


    public void addItem(Item newItem)
    {
        this.items.add(newItem);
    }

    public Item removeItem(String productId)
    {
        for (Item item : this.items) {
            if(item.getProductId().equals(productId))
            {

                this.items.remove(item);
                return item;
            }
        }
        return null;
    }

    public boolean isInTheCar(String productId)
    {
        for (Item item : this.items) 
            if(item.getProductId().equals(productId))
                return true;
        
        return false;
    }


    public ItemEdited editItem(String productId,int quantity)
    {
        for (Item item : this.items) {
            if(item.getProductId().equals(productId))
                {   
                    ItemEdited temp = new ItemEdited(item, quantity > item.getQuantity(),item.getQuantity());
                    item.setQuantity(quantity);
                    return temp;
                }
        }
        return null;
    }


    public String getCartId() {
        return cartId;
    }

    public void setCartId(String customerId) {
        this.cartId = customerId;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
