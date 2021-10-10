package com.techelevator;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;


import static org.junit.Assert.assertNotNull;

public class VendingMachineTest {
    private VendingMachine machine;


    @Before
    public void setup() {
        machine = new VendingMachine();
    }

    /**
     * Possibly create a second test file for the Run method in the CLI, to test method dependencies
     **/

    /*
    @Test
    public void testGetInventory() {
        File menuFile = new File("vendingmachine.csv");
        Map<String, Snack> expected = new LinkedHashMap<>();
        try (Scanner fileReader = new Scanner(menuFile)) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine(); // make a string
                String[] lineArray = line.split("\\|"); // turn into array
                BigDecimal price = (new BigDecimal(lineArray[2])).abs(); // turn priceString into BigDecimal
                expected.put(lineArray[0], new Snack(lineArray[1], price, lineArray[3])); // create snack object, and add to Map with inventory number (5)
            }
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    Map<String, Snack> result = machine.getInventory();
        assertEquals(assertTrue(Maps.difference(expected, result).areEqual()));
}
*/
    @Test
    public void testGetMachineBalance() {
        machine.increaseMachineBalance("5");
        BigDecimal result = machine.getMachineBalance();
        Assert.assertEquals(new BigDecimal("5"),result);
    }

    @Test
    public void testIncreaseMachineBalanceWithTooManyDecimals() {
        machine.increaseMachineBalance("5.5392057");
        BigDecimal result = machine.getMachineBalance();
        Assert.assertEquals(new BigDecimal("0"),result);
    }
    @Test
    public void testIncreaseMachineBalanceWithNegativeNumber() {
        machine.increaseMachineBalance("-1");
        BigDecimal result = machine.getMachineBalance();
        Assert.assertEquals(new BigDecimal("0"),result);
    }

    @Test
    public void testIncreaseMachineBalanceWith0() {
        machine.increaseMachineBalance("0");
        BigDecimal result = machine.getMachineBalance();
        Assert.assertEquals(new BigDecimal("0"),result);
    }

    @Test
    public void testIncreaseMachineBalanceWithWholeNumber() {
        machine.increaseMachineBalance("10");
        BigDecimal result = machine.getMachineBalance();
        Assert.assertEquals(new BigDecimal("10"),result);
    }

/*******************************************************************************/

    @Test
    public void testSubtractMachineBalanceIfNegative() {
        machine.subtractMachineBalance(new BigDecimal("-5"));
        BigDecimal result = machine.getMachineBalance();
        Assert.assertEquals(new BigDecimal("5"), result);
    }

    @Test
    public void testSubtractMachineBalanceIsCorrect() {
        machine.increaseMachineBalance("10");
        machine.subtractMachineBalance(new BigDecimal("5"));
        BigDecimal result = machine.getMachineBalance();
        Assert.assertEquals(new BigDecimal("5"), result);

        machine.increaseMachineBalance(("20"));
        machine.subtractMachineBalance(new BigDecimal("15"));
        result = machine.getMachineBalance();
        Assert.assertEquals(new BigDecimal("10"), result);

        machine.increaseMachineBalance("523.50"); // this fails b/c invalid whole number
        machine.subtractMachineBalance(new BigDecimal("3.05"));
        result = machine.getMachineBalance();
        Assert.assertEquals(new BigDecimal("6.95"), result);
    }




    /******************************************************************************/
    //public Snack(String snackName, BigDecimal price, String description)
    @Test
    public void testPurchaseItemSoldOut() {
        Snack testSnack = machine.getInventory().get("A1");
        testSnack.setInventory(0);
        String result = machine.purchaseItem("A1");
        Assert.assertEquals("Sold Out", result);
    }

    @Test
    public void testPurchaseItemInsufficientFunds() {
        machine.increaseMachineBalance("1");
        String result = machine.purchaseItem("A1");
        Assert.assertEquals("You need to add more money", result);
    }

    @Test
    public void testPurchaseItemSuccessfulPurchase() {
        Snack selectedSnack = machine.getInventory().get("A1");
        machine.increaseMachineBalance("10");
        String result = machine.purchaseItem("A1");
        String expected = "Here's your snack, OK?" + " " +
                selectedSnack.getSnackName() +
                "\n" +
                "Snack Price: " + selectedSnack.getPrice() +
                "\n" +
                "Remaining Balance: " + machine.getMachineBalance() +
                "\n" +
                selectedSnack.playSound();
        Assert.assertEquals(expected, result);
    }

/******************************************************************************/


/**************************************************************************/
    @Test
    public void testIsValidSlotLowestCorrectAnswer() {
    boolean result = machine.isValidSlot("A1");
    Assert.assertEquals(true, result);
}


    @Test
    public void testIsValidSlotHighestCorrectAnswer() {
        boolean result = machine.isValidSlot("D4");
        Assert.assertEquals(true, result);
    }

    @Test
    public void testIsValidSlotOutsideOfRange() {
        boolean result = machine.isValidSlot("A5");
        Assert.assertEquals(false, result);
    }
/**************************************************************************/


    @Test
    public void testIsValidDollarEnteredWithWords() {
        boolean result = machine.isValidDollarEntered("Apples");
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsValidDollarEnteredWithCents() {
        boolean result = machine.isValidDollarEntered(("0.42"));
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsValidDollarEnteredWithNegativeInteger() {
        boolean result = machine.isValidDollarEntered(("-5"));
        Assert.assertEquals(false, result);
    }

    @Test
    public void testDisplayItems() {
        // check format
        String result = machine.displayItems();
        String expected = "A1 Potato Crisps 3.05 5\n"+
        "A2 Stackers 1.45 5\n" +
        "A3 Grain Waves 2.75 5\n" +
        "A4 Cloud Popcorn 3.65 5\n" +
        "B1 Moonpie 1.80 5\n" +
        "B2 Cowtales 1.50 5\n" +
        "B3 Wonka Bar 1.50 5\n" +
        "B4 Crunchie 1.75 5\n" +
        "C1 Cola 1.25 5\n" +
        "C2 Dr. Salt 1.50 5\n" +
        "C3 Mountain Melter 1.50 5\n" +
        "C4 Heavy 1.50 5\n" +
        "D1 U-Chews 0.85 5\n" +
        "D2 Little League Chew 0.95 5\n" +
        "D3 Chiclets 0.75 5\n" +
        "D4 Triplemint 0.75 5\n";
        Assert.assertEquals(expected,result);
    }


    @Test
    public void testMakeChangeWithFive() {
        //check if the value of machine is 0 at the end of makeChange
        machine.increaseMachineBalance("5");
        machine.makeChange();
        BigDecimal result = machine.getMachineBalance();
        Assert.assertEquals(new BigDecimal("0"), result);
    }

    @Test
    public void testMakeChangeWithNegative() {
        machine.increaseMachineBalance("-1");
        machine.makeChange();
        BigDecimal result = machine.getMachineBalance();
        Assert.assertEquals(new BigDecimal("0"), result);
    }

    @Test
    public void testMakeChangeWithCents() {
        machine.increaseMachineBalance(".01");
        machine.makeChange();
        BigDecimal result = machine.getMachineBalance();
        Assert.assertEquals(new BigDecimal("0"), result);
    }

}