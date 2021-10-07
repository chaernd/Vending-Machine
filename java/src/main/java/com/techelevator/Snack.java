package com.techelevator;

import java.math.BigDecimal;

public class Snack {

    /** instance variable **/
    private String itemNumber;
    private String snackName;
    private BigDecimal price;
    private String description;

    /** constructor **/
    public Snack(String itemNumber, String snackName, BigDecimal price, String description) {
        this.itemNumber = itemNumber;
        this.snackName = snackName;
        this.price = price;
        this.description = description;
    }

    public String getItemNumber() {
        return itemNumber;
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
}
