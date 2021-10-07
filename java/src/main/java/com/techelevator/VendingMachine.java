package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

    /** Instance variables **/
    private int idNumber;
    private Map<Snack,Integer> inventory = new LinkedHashMap<>();
    private int[] options;
    private BigDecimal machineBalance;

    /* when a vending machine is created
    it takes in a Menu object as a parameter
     */

    /** Constructor **/
    public VendingMachine(int idNumber) {
        this.idNumber = idNumber;
        this.machineBalance = new BigDecimal("0");
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

    public int getidNumber() {
        return idNumber;
    }

    public Map<Snack, Integer> getInventory() {
        return inventory;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public void setInventory(Map<Snack, Integer> inventory) {
        this.inventory = inventory;
    }

    public void setOptions(int[] options) {
        this.options = options;
    }

    public BigDecimal getMachineBalance() {
        return machineBalance;
    }

    public void setMachineBalance(BigDecimal dollars) {
        machineBalance.add(dollars); // todo : THIS DOESN'T WORK
    }
}
