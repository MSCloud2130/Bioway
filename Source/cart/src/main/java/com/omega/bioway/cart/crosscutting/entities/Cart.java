package com.omega.bioway.cart.crosscutting.entities;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "cart")
public class Cart {
    @Id
    private String  customerId;
    private double totalCost;
    private List<Item> items;
    
    public Cart(){
        this.items = new ArrayList<>();
    }

    public Cart(String customerId, double totalCost)
    {
        this.customerId = customerId;
        this.totalCost = totalCost;
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


    public Item editItem(String productId,int quantity)
    {
        for (Item item : this.items) {
            if(item.getProductId().equals(productId))
                {   
                    item.setQuantity(quantity);
                    return item;
                }
        }
        return null;
    }


    public String getCostumerId() {
        return customerId;
    }

    public void setCostumerId(String customerId) {
        this.customerId = customerId;
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
