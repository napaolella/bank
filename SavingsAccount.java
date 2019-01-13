
public class SavingsAccount extends BankAccount{
	
	/**
	 * Fields
	 * private double intRate interest rate monthly
	 * private final double MIN_BAL minimum balance allowed without fee
	 * private final double MIN_BAL_FEE fee charged if balance too low
	 */
	
	private double intRate;
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	
	/**
	 * Constructs new Savings acc with custom balance
	 * @param n holder name
	 * @param b balance
	 * @param r interest rate
	 * @param mb minimum balance in dollars
	 * @param mbf minimum balance fee in dollars
	 */
	public SavingsAccount(String n, double b, double r, double mb, double mbf){
		super(n,b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	/**
	 * Constructs new Savings acc with balance 0
	 * @param n holder name
	 * @param r interest rate
	 * @param mb minimum balance in dollars
	 * @param mbf minimum balance fee in dollars
	 */
	public SavingsAccount(String n, double r, double mb, double mbf) {
		this(n,0,r,mb,mbf);
	}
	
	/**
	 * withdraws money from account, applies necessary fees, throws exception if not authorized
	 * @param amt money in dollars
	 */
	public void withdraw(double amt) {
		if (amt <= 0) throw new IllegalArgumentException();
		if (getBalance()-amt<0) throw new IllegalArgumentException();
		super.withdraw(amt);
		if (getBalance()<MIN_BAL) super.withdraw(MIN_BAL_FEE);
	}
	
	/**
	 * deposits money into account, applies necessary fees, throws exception if not authorized
	 * @param amt money in dollars
	 */
	public void deposit(double amt) {
		if (amt <= 0) throw new IllegalArgumentException();
		super.deposit(amt);
	}
	
	/**
	 * transfers money FROM this acc, to another, throws exception if not authorized
	 * @param other BankAccount to recieve money
	 * @param amt dollar amount
	 */
	public void transfer(BankAccount other, double amt) {
		if (!other.getName().equals(this.getName())) throw new IllegalArgumentException();
		withdraw(amt);
		other.deposit(amt);
	}
	
	/**
	 * compounds interest once
	 */
	public void addInterest() {
		deposit(intRate*getBalance());
	}
	
	/**
	 * adds interest for the month
	 */
	public void endOfMonthUpdate() {
		addInterest();
	}

}
