package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachine {

    /** Instance variables **/
    private int idNumber;
    private Map<Snack,Integer> inventory = new LinkedHashMap<>();
    private int[] options;
    private BigDecimal machineBalance;
    private Scanner scanner = new Scanner(System.in);


    /* when a vending machine is created
    it takes in a Menu object as a parameter
     */

    /** Constructor **/
    public VendingMachine(int idNumber) {
        this.idNumber = idNumber;
        this.machineBalance = new BigDecimal("0");
        createInventoryMap();

    }
                                                        //Map<String, Snack> countMap =
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
            System.out.println(Arrays.toString(e.getStackTrace()));
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

    public void increaseMachineBalance(BigDecimal dollars) {
        machineBalance = machineBalance.add(dollars); // todo : THIS DOESN'T WORK
    }

    public void substractMachineBalance(Snack key) {
        machineBalance = machineBalance.subtract(key.getPrice());
    }

    public String selectProduct(VendingMachine vendingMachine, Map<Snack, Integer> inventoryCopy, String choice, Menu menu, String[] PURCHASE_MENU_OPTIONS) {

        String userInput = scanner.nextLine();

        for (Map.Entry<Snack, Integer> item : getInventory().entrySet()) {
            Snack key = item.getKey();
            Integer newInv = item.getValue();
            if (userInput.matches("[A-D]" + "[1-4]")) {
                updateValue(vendingMachine, inventoryCopy, key, newInv);
                substractMachineBalance(key);
                System.out.println(getMachineBalance());
                break;
            } else {
                System.out.println("Invalid option");
                return choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
            }
        } return choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);


    }

    /*
    public void updateValue(VendingMachine vendingMachine, Map<Snack, Integer> inventoryCopy, Snack key, Integer newInv) {
        newInv--;
        System.out.println(newInv);
        inventoryCopy.put(key, newInv);
        vendingMachine.setInventory(inventoryCopy);
    }
*/

    public String purchaseItem(String slot) {
        //lookup snack based on slot using the inventory map (instance variable)
        //lookup current value of how many there are (inventory count)
        //if (inventoryCount < 1) {return "Sold Out"}
        //if (not enough money) {return "Give more money"}
        //else {inventoryCount--, update map, update machineBalance, return snack-specific noise}
    }

    public boolean isValidSlot(String userInput) {
        return inventory.containsKey(userInput);
    }
}
