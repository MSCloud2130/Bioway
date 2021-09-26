package com.omega.bioway.cart.crosscutting.entities;

public class ItemEdited {
    private Item edited;
    private boolean greaterQuantity;
    private int currentQuantity;

    public ItemEdited(Item edited,boolean greaterQuantity,int currentQuantity)
    {   
        this.edited = edited;
        this.greaterQuantity = greaterQuantity;
        this.currentQuantity = currentQuantity;
    }

    public Item getEdited()
    {
        return this.edited;
    }

    public boolean getgreaterQuantity()
    {
        return this.greaterQuantity;
    }

    public int getCurrentQuantity()
    {
        return this.currentQuantity;
    }
}
