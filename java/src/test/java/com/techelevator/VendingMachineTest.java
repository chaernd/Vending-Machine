package com.techelevator;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.Map;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.LinkedHashMap;

public class VendingMachineTest {
    private VendingMachine machine;

    @Before
    public void setup() {
        machine = new VendingMachine(1);
    }



    public void testCreateInventoryMap() {

    }

    public void testGetidNumber() {
    }

    public void testGetInventory() {
    }

    public void testSetIdNumber() {
    }

    public void testSetInventory() {
    }

    public void testSetOptions() {
    }

    public void testGetMachineBalance() {
    }

    public void testIncreaseMachineBalance() {
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

        machine.increaseMachineBalance("523.50");
        machine.subtractMachineBalance(new BigDecimal("3.05"));
        result = machine.getMachineBalance();
        Assert.assertEquals(new BigDecimal("530.45"), result);
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

    public void testDisplayItems() {
    }


/*
    @Test
    public void testWriteToTransactionLedger() {
        File readfile = new File("Log.txt");

        try (Scanner scanner = new Scanner(readfile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals()
            }
        } catch (Exception e) {
            System.out.println("Wrong.!");
        }

    }
*/


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