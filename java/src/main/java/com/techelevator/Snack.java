package com.techelevator;

import java.math.BigDecimal;

public class Snack {

    /** instance variable **/
    private String snackName;
    private BigDecimal price;
    private String description;
    private int inventory = 5;

    /** constructor **/
    public Snack(String snackName, BigDecimal price, String description) {
        this.snackName = snackName;
        this.price = price;
        this.description = description;
    }

    public String getSnackName() {
        return snackName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }



}
