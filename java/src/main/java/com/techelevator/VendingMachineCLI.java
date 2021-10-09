package com.techelevator;

import com.techelevator.view.Menu;

import java.math.BigDecimal;
import java.util.Map;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.LinkedHashMap;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE};
	private static final String PURCHASE_MENU_FEED_MONEY = "Feed money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION};
	private Menu menu;
	private Scanner scanner = new Scanner(System.in);

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		VendingMachine vendingMachine = new VendingMachine(1);
		/** Displays main menu, prompts for selection **/
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			/** if the choice is 1, display all the vending machine purchase options **/
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				vendingMachine.displayItems();
				// todo: create a method in the Vending Machine that returns out a nicely-displaying list of menu items (convert below into a method to call)
				// todo: System.out.println(call the above method);
				/*
				for (Map.Entry<String, Snack> item : vendingMachine.getInventory().entrySet()) {
					String key = item.getKey();
					Snack value = item.getValue();
					System.out.println(key +  " " + value.getSnackName() + " " + value.getPrice() + " " + value.getInventory());
				}
				/*
				/** if the choice is 2, display a new purchase menu, and prompt again for input **/
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				while (true) {
					//
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS); // validates user Choice
					if (purchaseChoice.equals(PURCHASE_MENU_FEED_MONEY)) { // Choice one, prompt for feed money
						System.out.print("Enter money ");
						String userInput = scanner.nextLine();
						if (vendingMachine.isValidDollarEntered(userInput)) { // pass in user input to Vending Machine. If returns true (is valid dollar)
							BigDecimal dollarEntered = new BigDecimal(userInput); // convert user input to Big Decimal
							vendingMachine.increaseMachineBalance(dollarEntered); // add money to balance
							System.out.println("Current balance: " + vendingMachine.getMachineBalance());
							vendingMachine.writeToTransactionLedger(dollarEntered, "FEED MONEY");
						} // if false, nothing happens. Goes back to submenu.
//
					} else if (purchaseChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)) { // Choice two, prompt to pick an item
						// todo: display products ^^ using the method described above.
						vendingMachine.displayItems();
						System.out.print("Enter a product number: ");
						String userInput = scanner.nextLine();
						if (vendingMachine.isValidSlot(userInput)) { // is it a key that exists in the map (and if machineBalance >= item price
							System.out.print(vendingMachine.purchaseItem(userInput)); // if it does exist in the map, make the purchase.

						} else {
							System.out.println("That was not a valid selection");//print "invalid selection"
						}
						// todo: dispense sound OR print out failure message (e.g. "not enough money", "Item doesn't exist");

					} else if (choice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) { // Choice 3, complete the transaction

						// todo: system.out.println the amount of change being given back
						// todo: reset the machine's balance to 0
						// todo: update log file (haven't created yet)
						// todo: add a third Static Variable Exit button to the main menu

						// todo: optional sales report

					}
				}
			}
		}
	}




/*
	private void updateValue(VendingMachine vendingMachine, Map<Snack, Integer> inventoryCopy, Snack key, Integer newInv) {
		newInv--;
		System.out.println(newInv);
		inventoryCopy.put(key, newInv);
		vendingMachine.setInventory(inventoryCopy);
	}
*/
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
