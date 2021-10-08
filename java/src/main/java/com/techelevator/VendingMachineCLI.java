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
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };
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
		Map<Snack, Integer> inventoryCopy = new LinkedHashMap<Snack, Integer>(vendingMachine.getInventory());
		/** Displays main menu, prompts for selection **/
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			/** if the choice is 1, display all the vending machine purchase options **/
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				System.out.println(vendingMachine.getidNumber());

				for (Map.Entry<Snack, Integer> item : vendingMachine.getInventory().entrySet()) {
					Snack key = item.getKey();
					Integer inventory = item.getValue();
					System.out.println(key.getItemNumber() + " " + key.getSnackName() + " " + key.getPrice() + " " + inventory);
				}

				/** if the choice is 2, display a new purchase menu, and prompt again for input **/
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				while (true) {
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS); // brings up sub-purchase menu, calls other methods that return Choice as 1 - 3
					if (purchaseChoice.equals(PURCHASE_MENU_FEED_MONEY)) { // Choice one, prompt for feed money
						System.out.println("Enter money");

						String userInput = scanner.nextLine();
						try {
							int dollar = Integer.parseInt(userInput);
						} catch (Exception e) {
							System.out.println("You have not entered a valid whole number");
						}
						BigDecimal dollarEntered = new BigDecimal(userInput);
						vendingMachine.increaseMachineBalance(dollarEntered);
						System.out.println(vendingMachine.getMachineBalance());
						// need to find a way to get out of the loop without going to FIRST while loop
					} else if (purchaseChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)) { // Choice 2, prompt to pick an item

						Scanner scanner = new Scanner(System.in);
						//print("enter item number")
						vendingMachine.isValidSlot(userInput) {
							vendingMachine.purchaseItem();
						} else {
							//print "invalid selection"
						}

						vendingMachine.selectProduct(vendingMachine, inventoryCopy, choice, menu, PURCHASE_MENU_OPTIONS);



						String userInput = scanner.nextLine();
						boolean isValidSelection = vendingMachine.isValidSlot(userInput);
						for (Map.Entry<Snack, Integer> item : vendingMachine.getInventory().entrySet()) { //get rid of the for loop
							Snack key = item.getKey();												     //move updateV
							Integer newInv = item.getValue();
							if (userInput.matches("[A-D]" + "[1-4]")) {
								updateValue(vendingMachine, inventoryCopy, key, newInv);
							} else {
								System.out.println("Invalid option");
								choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
							}
						}


					} else if (choice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) { // complete the transaction
						// make change (reset machine balance to 0, system.out.println the amount of change being given back)
						// update transaction file
					}

					 */
					}
//					choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
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
