
public abstract class BankAccount {
	
	static int nextAccNum;
	private int accNum;
	private String name;
	private double balance;
	
	public BankAccount(String n) {
		name = n;
		accNum = nextAccNum;
		nextAccNum++;
	}
	
	public BankAccount(String n, double b) {
		this(n);
		balance = b;
	}
	
	public void deposit(double amt) {
		balance += amt;
	}
	
	public void withdraw(double amt) {
		balance -= amt;
	}
	
	public String getName() {
		return name;
	}
	
	public double getBalance(){
		return balance;
	}
	
	public int getAccNum() {
		return accNum;
	}
	public abstract void endOfMonthUpdate();
	
	public void transfer(BankAccount other, double amt) {
		this.withdraw(amt);
		other.deposit(amt);
	}
	
	public String toString() {
		String s = "Num: " + accNum + "\tOwner: " + name + "\t$" + balance;
		if (this instanceof SavingsAccount) s += " -Savings-";
		else if (this instanceof CheckingAccount) s += " -Checking-";
		return s;
	}

}
