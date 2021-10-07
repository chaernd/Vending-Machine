package com.techelevator;

import com.techelevator.view.Menu;

import java.util.Map;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.LinkedHashMap;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };
	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		VendingMachine vendingMachine = new VendingMachine(1);
		Map <Snack, Integer> inventoryCopy = new LinkedHashMap<Snack, Integer>(vendingMachine.getInventory());
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				System.out.println(vendingMachine.getidNumber());

				for (Map.Entry<Snack, Integer> item : vendingMachine.getInventory().entrySet()) {
					Snack key = item.getKey();
					Integer inventory = item.getValue();
					System.out.println(key.getItemNumber() + " " + key.getSnackName() + " " + key.getPrice() + " " + inventory);
				}

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				System.out.println("What would you like to purchase?: >>");

				Scanner scanner = new Scanner(System.in);
				String userInput = scanner.nextLine();

			for (Map.Entry<Snack, Integer> item : vendingMachine.getInventory().entrySet()) {
					Snack key = item.getKey();
					Integer newInv = item.getValue();
					switch (userInput) {
								case "A1":
									updateValue(vendingMachine, inventoryCopy, key, newInv);
									//update the value, decremented by one.
									//do this each time a specific item is purchased
									//

									break;
								case "A2":
									updateValue(vendingMachine, inventoryCopy, key, newInv);
									break;
								case "A3":

									updateValue(vendingMachine, inventoryCopy, key, newInv);
									break;
								case "A4":
									updateValue(vendingMachine, inventoryCopy, key, newInv);
									break;
								case "B1":
									updateValue(vendingMachine, inventoryCopy, key, newInv);
									break;
								case "B2":
									updateValue(vendingMachine, inventoryCopy, key, newInv);
									break;
								case "B3":
									updateValue(vendingMachine, inventoryCopy, key, newInv);
									break;
								case "B4":
									updateValue(vendingMachine, inventoryCopy, key, newInv);
									break;
								case "C1":
									updateValue(vendingMachine, inventoryCopy, key, newInv);
									break;
								case "C2":
									updateValue(vendingMachine, inventoryCopy, key, newInv);
									break;
								case "C3":
									updateValue(vendingMachine, inventoryCopy, key, newInv);
									break;
								case "C4":
									updateValue(vendingMachine, inventoryCopy, key, newInv);
									break;
								case "D1":
									updateValue(vendingMachine, inventoryCopy, key, newInv);
									break;
								case "D2":
									updateValue(vendingMachine, inventoryCopy, key, newInv);
									break;
								case "D3":
									updateValue(vendingMachine, inventoryCopy, key, newInv);
								case "D4":
									updateValue(vendingMachine, inventoryCopy, key, newInv);
							}
							break;
					//try {
					//	int selectedOption = Integer.valueOf(userInput);
					//	if (selectedOption > 0 && selectedOption <= options.length) {
					//		choice = options[selectedOption - 1];
					//	}
				}
			}
		}
	}

	private void updateValue(VendingMachine vendingMachine, Map<Snack, Integer> inventoryCopy, Snack key, Integer newInv) {
		newInv--;
		System.out.println(newInv);
		inventoryCopy.put(key, newInv);
		vendingMachine.setInventory(inventoryCopy);
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
		VendingMachine myMachine = new VendingMachine(5);

	}
}
