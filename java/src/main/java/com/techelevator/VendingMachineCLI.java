package com.techelevator;

import com.techelevator.view.Menu;
import java.util.Scanner;

public class VendingMachineCLI {
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT};
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
		VendingMachine vendingMachine = new VendingMachine();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			switch (choice) {
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					System.out.println(vendingMachine.displayItems());
					break;

				case MAIN_MENU_OPTION_PURCHASE:
					while (true) {
						System.out.println("\nYour balance: $" + (vendingMachine.getMachineBalance()).setScale(2));
						String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

						if (purchaseChoice.equals(PURCHASE_MENU_FEED_MONEY)) {
							System.out.print("Enter money ");
							String userInput = scanner.nextLine();
							System.out.println(vendingMachine.increaseMachineBalance(userInput));

						} else if (purchaseChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
							System.out.println(vendingMachine.displayItems());
							System.out.print("Enter a product number: ");
							String userInput = scanner.nextLine();
							System.out.println(vendingMachine.purchaseItem(userInput));

						} else if (purchaseChoice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
							System.out.printf("Your change: %.2f", vendingMachine.getMachineBalance());
							vendingMachine.makeChange();
							System.out.printf("\nMachine Balance: %.2f", vendingMachine.getMachineBalance());

							break;
						}
					}

					break;


				case MAIN_MENU_OPTION_EXIT:
					System.out.println("Goodbye!");
					System.exit(0);

				case MAIN_MENU_OPTION_SALES_REPORT:
					vendingMachine.makeSalesReportFile();
					break;
			}
		}
	}


	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}