import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		final double OVER_DRAFT_FEE = 15;
		final double RATE = .0025;
		final double TRANSACTION_FEE = 1.5;
		final double MIN_BAL = 300;
		final double MIN_BAL_FEE = 10;
		
		final int FREE_TRANSACTIONS = 10;
		
		//master array list to hold all accounts
		ArrayList<BankAccount> accs = new ArrayList<BankAccount>();
		
		Scanner s = new Scanner(System.in);
		
		boolean contPlay = true;
		//temp string to handle all user input
		String input = "";
		
		while (contPlay) {
		
			System.out.print("Main menu options: Add Account (add) Make Transaction (trans) View Your Accounts (view) Terminate (quit)\n(Enter Option): ");
			input = s.nextLine();
			
			switch (input) {
			//add an account
			case "add":
			case "Add":
				System.out.println("Savings/Checking? (s/c): ");
				input = s.nextLine();
				switch (input){
				case "s":
				case "Savings":
				case "savings":
					String name = "";
					System.out.println("Enter Acc Holder Name: ");
					name = s.nextLine();
					System.out.println("Would you like to make an initial deposit? (y/n): ");
					input = s.nextLine();
					switch (input) {
					case "y":
					case "yes":
						String bal = "x";
						while (!isNumeric(bal)) {
							System.out.println("Input amount: ");
							bal = s.nextLine();
						}
						try {
							accs.add(new SavingsAccount(name,Double.parseDouble(bal),RATE,MIN_BAL,MIN_BAL_FEE));
							System.out.println("Account creation successful.");
						}
						catch (IllegalArgumentException e) {
							System.out.println("Invalid initial deposit, account creation canceled");
						}
						break;
					case "n":
					case "no":
						accs.add(new SavingsAccount(name,RATE,MIN_BAL,MIN_BAL_FEE));
						System.out.println("Account creation successful.");
						break;
					default: System.out.println("Invalid Input, Action Canceled");
					}
					break;
				case "Checking":
				case "checking":
				case "c":
					System.out.println("Enter Acc Holder Name: ");
					name = s.nextLine();
					System.out.println("Would you like to make an initial deposit? (y/n): ");
					input = s.nextLine();
					switch (input) {
					case "y":
					case "yes":
						String bal = "x";
						while (!isNumeric(bal)) {
							System.out.println("Input amount: ");
							bal = s.nextLine();
						}
						try {
							accs.add(new CheckingAccount(name,Double.parseDouble(bal),OVER_DRAFT_FEE,TRANSACTION_FEE,FREE_TRANSACTIONS));
							System.out.println("Account creation successful.");
						}
						catch (IllegalArgumentException e) {
							System.out.println("Invalid initial deposit, account creation canceled");
						}
						break;
					case "n":
					case "no":
						accs.add(new CheckingAccount(name,OVER_DRAFT_FEE,TRANSACTION_FEE,FREE_TRANSACTIONS));
						System.out.println("Account creation successful.");
						break;
					default: System.out.println("Invalid Input, Action Canceled");
				}
				}
						break;
			//make a transaction
			case "Transaction":
			case "transaction":
			case "trans":
				boolean hasValidAcc = false;
				int accnum = -1;
				BankAccount acc = null;
				while (!hasValidAcc) {
					System.out.println("Enter the account # from which you would like to transfer, withdraw, or deposit money: ");
					input = s.nextLine();
					if (isInt(input)) {
						for (BankAccount a: accs) {
							if (a.getAccNum()==Integer.parseInt(input)) {
								hasValidAcc = true;
								accnum = Integer.parseInt(input);
								acc = a;
							}
						}
						if (!hasValidAcc) {
							System.out.println("Account does not exist. Enter your name if you'd like to see a list of any accounts under your name. Otherwise, press Enter to try again: ");
							input = s.nextLine();
							for (BankAccount a: accs) {
								if (a.getName().equals(input)) System.out.println(a.toString());
							}
						}
					}
					else System.out.println("Invalid Acc #");
				}
				System.out.println("Transaction Type? Withdraw (w) Deposit (d) Transfer (t): ");
				input = s.nextLine();
				switch (input) {
				case "Withdraw":
				case "withdraw":
				case "w":
					System.out.println(acc.getName()+", how much would you like to withdraw?: ");
					input = s.nextLine();
					if (isNumeric(input)) {
						try {
							acc.withdraw(Double.parseDouble(input));
							System.out.println("Successfully withdrew $" + input);
						}
						catch (IllegalArgumentException e) {
							System.out.println("Transaction not authorized");
						}
					}
					else System.out.println("Invalid amount, action canceled.");
					break;
				case "Deposit":
				case "deposit":
				case "d":
					System.out.println(acc.getName()+", how much would you like to deposit?: ");
					input = s.nextLine();
					if (isNumeric(input)) {
						try {
							acc.deposit(Double.parseDouble(input));
							System.out.println("Successfully deposited $" + input);
						}
						catch (IllegalArgumentException e) {
							System.out.println("Transaction not authorized");
						}
					}
					else System.out.println("Invalid amount, action canceled.");
					break;
				case "Transfer":
				case "transfer":
				case "t":
					boolean hasValidReceiver = false;
					BankAccount receiving = null;
					while (!hasValidReceiver) {
						System.out.println("You are transfering money FROM your account:" + acc.toString());
						System.out.println(acc.getName()+", input the account # you would like to transfer into: ");
						input = s.nextLine();
						if (isInt(input)) {
							for (BankAccount a: accs) {
								if (a.getAccNum()==Integer.parseInt(input)) {
									hasValidAcc = true;
									accnum = Integer.parseInt(input);
									receiving = a;
								}
							}
							if (!hasValidAcc) {
								System.out.println("Account does not exist. Enter your name if you'd like to see a list of any accounts under your name.\n Otherwise, press Enter to try again: ");
								input = s.nextLine();
								for (BankAccount a: accs) {
									if (a.getName().equals(input)) System.out.println(a.toString());
								}
							}
						}
						else System.out.println("Invalid Acc #");
					}
					System.out.println(acc.getName()+", how much would you like to transfer?: ");
					input = s.nextLine();
					if (isNumeric(input)) {
						try {
							acc.transfer(receiving,Double.parseDouble(input));
							System.out.println("Successfully transfered $" + input);
						}
						catch (IllegalArgumentException e) {
							System.out.println("Transaction not authorized");
						}
					}
					else System.out.println("Invalid amount, action canceled.");
					break;
				}
				break;
				default:
					System.out.println("Invalid Input, Action Canceled");
				break;
			//get your account numbers
			case "view":
			case "View":
				System.out.println("Enter your name: ");
				input = s.nextLine();
				System.out.println("Accounts found under your name: ");
				for (BankAccount a: accs) {
					if (a.getName().equals(input)) System.out.println(a.toString());
				}
				break;
			//terminate
			case "quit":
				contPlay = false;
				break;
		}
		}
	}

	/**
	 * tests to see if input is numeric
	 * @param string of input
	 * @return true if string can be parsed to a double, false if not
	 */
	private static boolean isNumeric(String str)
		{
		try
		{
		Double.parseDouble(str);
		return true;
		}
			catch(IllegalArgumentException e)
			{
				return false;
			}
		}
	/**
	 * tests to see if acc# input is an int
	 * @param string of input
	 * @return true if string can be parsed to an int, false if not
	 */
	public static boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		}
		catch (IllegalArgumentException e) {
			return false;
		}
}
}
