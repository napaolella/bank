
public abstract class BankAccount {
	
	/**
	 * Fields
	 * static int nextAccNum keeps track of used acc nums
	 * private int accNum contains accs unique num
	 * private String name contains acc holder name
	 * private double balance contains accs balance
	 */
	static int nextAccNum;
	private int accNum;
	private String name;
	private double balance;
	
	/**
	 * Constructs new bank account with balance of 0
	 * @param n becomes acc holder name
	 */
	public BankAccount(String n) {
		name = n;
		accNum = nextAccNum;
		nextAccNum++;
	}
	
	/**
	 * Constructs new bank acc with custom balance
	 * @param n holder name
	 * @param b initial balance
	 */
	public BankAccount(String n, double b) {
		this(n);
		balance = b;
	}
	
	/**
	 * deposits money into account
	 * @param amt money in dollars
	 */
	public void deposit(double amt) {
		balance += amt;
	}
	
	/**
	 * withdraws money from account
	 * @param amt money in dollars
	 */
	public void withdraw(double amt) {
		balance -= amt;
	}
	
	/**
	 * returns acc holder name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * returns acc's balance
	 * @return balance
	 */
	public double getBalance(){
		return balance;
	}
	
	/**
	 * returns acc's number
	 * @return accNum
	 */
	public int getAccNum() {
		return accNum;
	}
	
	/**
	 * Performs necessary actions for specific account type at end of month
	 */
	public abstract void endOfMonthUpdate();
	
	/**
	 * transfers money FROM this acc, to another
	 * @param other BankAccount to recieve money
	 * @param amt dollar amount
	 */
	public void transfer(BankAccount other, double amt) {
		this.withdraw(amt);
		other.deposit(amt);
	}
	
	/**
	 * Returns string representation of acc, including name, balance, number, and type
	 */
	public String toString() {
		String s = "Num: " + accNum + "\tOwner: " + name + "\t$" + balance;
		if (this instanceof SavingsAccount) s += " -Savings-";
		else if (this instanceof CheckingAccount) s += " -Checking-";
		return s;
	}

}
