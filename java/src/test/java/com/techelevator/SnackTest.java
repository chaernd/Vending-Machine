package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;

public class SnackTest {
    @Test
    public void testSnackSoundWithChip() {
        Snack myChips = new Snack("GenericChips",new BigDecimal("3.00"),"Chip");
        String result = myChips.playSound();
        String expected = "Crunch Crunch, Yum!";
        Assert.assertEquals(expected,result);
    }

    @Test
    public void testSnackSoundWithLobster() {
        Snack notInOurVendingMachine = new Snack("Lobster",new BigDecimal("3.00"),"Crustacean");
        String result = notInOurVendingMachine.playSound();
        String expected = "unknown";
        Assert.assertEquals(expected,result);
    }
    
    @Test
    public void testSnackSoundWithGum() {
        Snack genericGum = new Snack("Gum",new BigDecimal("3.00"),"Gum");
        String result = genericGum.playSound();
        String expected = "Chew Chew, Yum!";
        Assert.assertEquals(expected,result);
    }
    @Test
    public void testSnackSoundWithDrink() {
        Snack genericDrink = new Snack("Soda",new BigDecimal("3.00"),"Drink");
        String result = genericDrink.playSound();
        String expected = "Glug Glug, Yum!";
        Assert.assertEquals(expected,result);
    }

    @Test
    public void testSnackSoundWithCandy() {
        Snack genericCandy = new Snack("Candy",new BigDecimal("3.00"),"Candy");
        String result = genericCandy.playSound();
        String expected = "Munch Munch, Yum!";
        Assert.assertEquals(expected,result);
    }




}