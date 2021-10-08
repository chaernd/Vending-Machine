package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachine {

    /** Instance variables **/
    private int idNumber;
    private Map<String, Snack> inventory = new LinkedHashMap<>();
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
                inventory.put(lineArray[0], new Snack(lineArray[1], price, lineArray[3])); // create snack object, and add to Map with inventory number (5)
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

    public Map<String, Snack> getInventory() {
        return inventory;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public void setInventory(Map<String, Snack> inventory) {
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

    public void subtractMachineBalance(BigDecimal snackPrice) {
        machineBalance = machineBalance.subtract(snackPrice);
    }

//    public String selectProduct(VendingMachine vendingMachine, Map<Snack, Integer> inventoryCopy, String choice, Menu menu, String[] PURCHASE_MENU_OPTIONS) {
//
//        String userInput = scanner.nextLine();
//
//        for (Map.Entry<String, Snack> item : getInventory().entrySet()) {
//            Snack key = item.getKey();
//            Integer newInv = item.getValue();
//            if (userInput.matches("[A-D]" + "[1-4]")) {
//                updateValue(vendingMachine, inventoryCopy, key, newInv);
//                subtractMachineBalance(key);
//                System.out.println(getMachineBalance());
//                break;
//            } else {
//                System.out.println("Invalid option");
//                return choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
//            }
//        } return choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
//
//
//    }

    /*
    public void updateValue(VendingMachine vendingMachine, Map<Snack, Integer> inventoryCopy, Snack key, Integer newInv) {
        newInv--;
        System.out.println(newInv);
        inventoryCopy.put(key, newInv);
        vendingMachine.setInventory(inventoryCopy);
    }
*/

    public void purchaseItem(String slot) {
        //slot is a key in the map
        //we want to get the value at that key (which is a snack)
        boolean isSuccessfulPurchase = true;
        Snack selectedSnack = inventory.get(slot); // this is the snack Object they want to buy
        if (selectedSnack.getInventory() > 0) { // if there is a snack to buy
            if (machineBalance.compareTo(selectedSnack.getPrice()) >= 0) { // if machine balance is greater than or equal to snackPrice
                BigDecimal snackPrice = selectedSnack.getPrice(); // turn snack price into a BigDecimal
                subtractMachineBalance(snackPrice); // makes change
                selectedSnack.setInventory(selectedSnack.getInventory() - 1); // should subtract one from the inventory.
            }
        } else { // if snack inventory is 0
            System.out.println("Sold out");
        }
        dispensingSound(isSuccessfulPurchase);
    }

    public boolean isValidSlot(String userInput) {
        return inventory.containsKey(userInput); // will return true if key entered is in the map, false if invalid entry.
    }

    public String dispensingSound(boolean isSuccessfulPurchase) {
        if (isSuccessfulPurchase) {
            return "Sound of dispensing an item";
        }else {
            return "Sound of failure";
        }
    }

    public boolean isValidDollarEntered(String userInput) {
        try {
            int dollar = Integer.parseInt(userInput);
            return true;
        } catch (Exception e) {
            System.out.println("You have not entered a valid whole number"); //
        }
        return false;
    }

}
