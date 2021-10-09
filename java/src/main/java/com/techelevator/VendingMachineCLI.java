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
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
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
			/** if the choice is 2, bring the purchase submenu **/
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				while (true) {
					//
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS); // validates user Choice
					if (purchaseChoice.equals(PURCHASE_MENU_FEED_MONEY)) { // Choice one, prompt for feed money
						System.out.print("Enter money ");
						String userInput = scanner.nextLine();
						if (vendingMachine.isValidDollarEntered(userInput)) { // pass in user input to Vending Machine. If returns true (is valid dollar)
							vendingMachine.increaseMachineBalance(userInput); // add money to balance and internally call method to write to audit log.
							System.out.println("Current balance: " + vendingMachine.getMachineBalance());
						}

					} else if (purchaseChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)) { // Choice two, prompt to pick an item
						vendingMachine.displayItems();
						System.out.print("Enter a product number: ");
						String userInput = scanner.nextLine();
						if (vendingMachine.isValidSlot(userInput)) { // is it a key that exists in the map (and if machineBalance >= item price
							System.out.print(vendingMachine.purchaseItem(userInput)); // if it does exist in the map, make the purchase.
						} else {
							System.out.println("That was not a valid selection");//print "invalid selection"
						}

					} else if (purchaseChoice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) { // Choice 3, complete the transaction

						System.out.printf("Your change: %.2f", vendingMachine.getMachineBalance());
						vendingMachine.makeChange(); // makes change (resets balance to 0, write to audit log)
						System.out.printf("\nMachine Balance: %.2f", vendingMachine.getMachineBalance());
						break;

					}
				}
			/** if the choice is 3, Exit **/
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Goodbye!");
				System.exit(0);
			}
		}
	}


	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
