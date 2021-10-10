package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.math.*;

public class VendingMachine {

    /**
     * Instance variables
     **/
    private int idNumber;
    private Map<String, Snack> inventory = new LinkedHashMap<>();
    private int[] options;
    private BigDecimal machineBalance;
    private Scanner scanner = new Scanner(System.in);
    private final BigDecimal STARTING_BALANCE = new BigDecimal("0");

    /**
     * Constructor
     **/

    public VendingMachine(int idNumber) {
        this.idNumber = idNumber;
        this.machineBalance = STARTING_BALANCE;
//        this.machineBalance = new BigDecimal("0");
        createInventoryMap();

    }

    public void createInventoryMap() {
        File menuFile = new File("vendingmachine.csv");

        try (Scanner fileReader = new Scanner(menuFile)) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine(); // make a string
                String[] lineArray = line.split("\\|"); // turn into array
                BigDecimal price = (new BigDecimal(lineArray[2])).abs(); // turn priceString into BigDecimal
                inventory.put(lineArray[0], new Snack(lineArray[1], price, lineArray[3])); // create snack object, and add to Map with inventory number (5)
            }
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
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

    public String increaseMachineBalance(String userInput) {
        if (isValidDollarEntered(userInput)){
            BigDecimal dollarEntered = new BigDecimal(userInput);
            machineBalance = machineBalance.add(dollarEntered);
            writeToTransactionLedger(dollarEntered, "FEED MONEY");
            return "Your Balance is: " + getMachineBalance();
        }
            return "That was not a valid whole number";
    }

    public void subtractMachineBalance(BigDecimal snackPrice) {
        machineBalance = machineBalance.subtract(snackPrice);
    }


    public String purchaseItem(String userInput) {
        //slot is a key in the map
        //we want to get the value at that key (which is a snack)

        if (isValidSlot(userInput)) {
            Snack selectedSnack = inventory.get(userInput); // this is the snack Object they want to buy
            if (selectedSnack.getInventory() > 0) { // if there is a snack to buy
                if (machineBalance.compareTo(selectedSnack.getPrice()) >= 0) { // if machine balance is greater than or equal to snackPrice
                    BigDecimal snackPrice = selectedSnack.getPrice(); // turn snack price into a BigDecimal
                    subtractMachineBalance(snackPrice); // makes change
                    selectedSnack.setInventory(selectedSnack.getInventory() - 1); // should subtract one from the inventory.
                    writeToTransactionLedger(getMachineBalance().add(snackPrice), "PURCHASE");
                    return "Here's your snack, OK?" + " " +
                            selectedSnack.getSnackName() +
                            "\n" +
                            "Snack Price: " + selectedSnack.getPrice() +
                            "\n" +
                            "Remaining Balance: " + getMachineBalance() +
                            "\n" +
                            selectedSnack.playSound();

                } else {
                    return "You need to add more money";
                }
            }
            return "Sold Out";
        } else {
            return "That was not a valid selection";
        }
    }


    public boolean isValidSlot(String userInput) {
        return inventory.containsKey(userInput); // will return true if key entered is in the map, false if invalid entry.
    }

    public boolean isValidDollarEntered(String userInput) {
        try {
            int dollar = Integer.parseInt(userInput);
            if (dollar > 0) {
                return true;
            }
        } catch (Exception e) {
//            System.out.println("You have not entered a valid whole number");
        }
        return false;
    }

    public String displayItems() {
        String items = "";
        for (Map.Entry<String, Snack> item : getInventory().entrySet()) {
            String key = item.getKey();
            Snack value = item.getValue();
            items += key + " " + value.getSnackName() + " " + value.getPrice() + " " + value.getInventory() + "\n";
        }
        return items;
    }

    public void writeToTransactionLedger(BigDecimal dollars, String transactionType) {
        try (FileWriter appender = new FileWriter(new File("Log.txt"), true)) {
            try (PrintWriter writer = new PrintWriter(appender)) {

                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
                writer.println(formatter.format(date) + " " + transactionType + ": $" + dollars.setScale(2) + " $" + getMachineBalance().setScale(2));

            } catch (Exception e) {
                System.out.print("There's a big with the write file");
            }
        } catch (Exception e) {
            System.out.print("ERROR WITH YOUR WRITE FILE");
        }
    }

    public void makeChange() {
        BigDecimal balanceBeforeGivingChange = getMachineBalance(); // 10
        machineBalance = STARTING_BALANCE; // 0
        writeToTransactionLedger(balanceBeforeGivingChange, "GIVE CHANGE");
    }

}
