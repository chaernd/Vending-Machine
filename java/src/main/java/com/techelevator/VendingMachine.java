package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

    /** Instance variables **/
    private int idNumber;
    private Map<Snack,Integer> inventory = new HashMap<>();

    /* when a vending machine is created
    it takes in a Menu object as a parameter
     */

    /** Constructor **/
    public VendingMachine(int idNumber) {
        this.idNumber = idNumber;
        createInventoryMap();

    }

    // method that Populates the inventory
    public void createInventoryMap() {
        File menuFile = new File("vendingmachine.csv");

        try (Scanner fileReader = new Scanner(menuFile)) {
            while(fileReader.hasNextLine()) {
                String line = fileReader.nextLine(); // make a string
                String[] lineArray = line.split("\\|"); // turn into array
                BigDecimal price = new BigDecimal(lineArray[2]); // turn priceString into BigDecimal
                inventory.put(new Snack(lineArray[0],lineArray[1],price, lineArray[3]), 5); // create snack object, and add to Map with inventory number (5)
            }

        } catch (Exception e) {
            System.out.println("Error!");
        }
            /*
            -read the provided menu file
            -while has next line
                - Turn every line into a string
                - Split string into an array
                - create a snack object and add to inventory Map
                    - give that snack a price
                    - give that snack a foodType description (candy, chip, drink, gum)
                    - Give each snack its key
         */

    }

}
