package com.revature;

import java.util.Scanner;

import com.revature.model.User;
import com.revature.service.BankSimulationServiceImplementation;

/** 
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class BankSimulationDriver {

	private static final String EXIT = "exit";
	private static final String REGISTER = "register";
	private static final String LOGIN = "login";
	private static final String LOGOUT = "logout";
	private static final String WITHDRAW = "withdaw";
	private static final String DEPOSIT = "deposit";
	private static final String VIEW_BALANCE = "view balance";
	private static final String VIEW_ALL_TRANSACTIONS = "view all transactions";

	public static void main(String[] args) {
		BankSimulationServiceImplementation bankService = new BankSimulationServiceImplementation();

		Scanner scr = new Scanner(System.in);

		System.out.println("Welcome to WeiX bank!");
		System.out.println("What can we help you with today?");
		printWelcomeSceenOptions(false);

		boolean pastWelcomeScreen = false;
		boolean loggedIn = false;
		String welcomeScreenAction = scr.nextLine();
		User user = null;

		while(true) {
			if(pastWelcomeScreen && loggedIn) {
				printWelcomeSceenOptions(true);
				welcomeScreenAction = scr.nextLine();
			}
			
			else {
				welcomeScreenAction = "l";
			}

			String email;
			String username;
			String password;
			long balance;

			if(welcomeScreenAction.equalsIgnoreCase(REGISTER) || 
					welcomeScreenAction.equalsIgnoreCase("r")) {
				
				System.out.print("email address: ");
				email = scr.nextLine();
				
				System.out.print("Desired Username: ");				
				username = scr.nextLine();
				
				System.out.print("Desired Password: ");
				password = scr.nextLine();
		
				System.out.print("Initial balance: ");
				balance = scr.nextLong();
				
				user = new User(email, username, password, balance);
				
				bankService.registerUser(user);
				System.out.println();

				System.out.println("Thank you for creating an account with us.");
				System.out.println("What else can we help you with today?");
				System.out.println();

				pastWelcomeScreen = true;
			}

			else if (welcomeScreenAction.equalsIgnoreCase(LOGIN) || 
					welcomeScreenAction.equalsIgnoreCase("l")){
				
				if(!loggedIn) {

					System.out.print("Username: ");
					username = scr.nextLine();

					System.out.print("Password: ");
					password = scr.nextLine();
					
					user.setUsername(username);
					user.setPassword(password);
					
					bankService.login(user);
					System.out.println();

					System.out.println("Login successful.");
					System.out.println("What would like to with your account?");
					System.out.println("Select from one of the following options:");
					System.out.println();
				}
				
				printTransactionOptions();

				String transactionOption = scr.nextLine();
				if(transactionOption.equalsIgnoreCase(WITHDRAW) || 
						transactionOption.equalsIgnoreCase("w")) {
					
					long withdrawalAmount;
					System.out.print("Withdrawal amount: ");
					withdrawalAmount = scr.nextLong();
					
					bankService.withdraw(user, withdrawalAmount);

				}

				else if(transactionOption.equalsIgnoreCase(DEPOSIT) ||
						transactionOption.equalsIgnoreCase("d")) {

					long depositAmount;
					System.out.print("Deposit amount: ");
					depositAmount = scr.nextLong();
					
					bankService.deposit(user, depositAmount);
				}
				
				else if(transactionOption.equalsIgnoreCase(VIEW_BALANCE) ||
						transactionOption.equalsIgnoreCase("b")) {
					
					bankService.viewBalance(user);
				}
					

				else if(transactionOption.equalsIgnoreCase(VIEW_ALL_TRANSACTIONS) ||
						transactionOption.equalsIgnoreCase("t")) {

					bankService.viewAllTransactions(user);
				}

				else if(transactionOption.equalsIgnoreCase(LOGOUT) || 
						transactionOption.equalsIgnoreCase("l")) {
					
					System.out.println("You have been logged out.");
					username = null;
					password = null;
					user = null;
					
					pastWelcomeScreen = true;
				}
				
				else {
					printInValidOptionSelected();
					loggedIn = true;
				}
				
				
			}

			else if(welcomeScreenAction.equalsIgnoreCase(EXIT) || 
					welcomeScreenAction.equalsIgnoreCase("e")) {

				System.out.println("Thank you for visiting WeiX Bank. We hope to see you again!");
				break;
			}

			else if(welcomeScreenAction.equals("gimmeyourmoney")) {

				System.out.println("Deleting all accounts...");
				break;
			}

			else {
				printInValidOptionSelected();

				pastWelcomeScreen = true;
			}

		}

		scr.close();
	}

	private static void printWelcomeSceenOptions(boolean hasAccount) {
		System.out.println("Select from one of the following options:");
		if(!hasAccount) {
			System.out.println("(R)egister for an account");
		}
		System.out.println("(L)ogin to an existing account" + "\n"
				+ "(E)xit");
		System.out.println();
	}

	private static void printTransactionOptions() {
		System.out.println("(W)ithdraw" + "\n" 
				+ "(D)eposit" + "\n" 
				+ "View (b)alance" + "\n" 
				+ "View all (t)ransactions" + "\n" 
				+ "(L)ogout");
		System.out.println();
	}
	
	private static void printInValidOptionSelected() {
		System.out.println("Invalid input, plesae select a valid option.");
		System.out.println();
	}
}
