package com.omega.bioway.products.crosscutting.entities;

import java.util.List;

public class FoodAndDrink extends Product {

    private List<String> includedItems;

    public FoodAndDrink() {
    }

    public List<String> getIncludedItems() {
        return includedItems;
    }

    public void setIncludedItems(List<String> includedItems) {
        this.includedItems = includedItems;
    }

}
