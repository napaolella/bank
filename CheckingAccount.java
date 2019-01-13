
public class CheckingAccount extends BankAccount {
	
	/**
	 * Feilds
	 * private final double OVER_DRAFT_FEE amount automatically withdrawn when acc goes negative
	 * private final double TRANSACTION_FEE amount automatically withdrawn when any transaction is made
	 * private final int FREE_TRANS amount of transactions allowed before transaction fee applies
	 * private int numTransactions keeps track of total transactions this month
	 */
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final int FREE_TRANS;
	
	private int numTransactions;
	
	/**
	 * Constructs new Checking acc with custom balance
	 * @param n holder name
	 * @param b balance
	 * @param odf over draft fee
	 * @param tf transaction fee
	 * @param freeTrans free transactions allowed
	 */
	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans) {
		super(n,b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
		numTransactions = 0;
	}
	
	/**
	 * Constructs new Checking acc with balance 0
	 * @param n holder name
	 * @param odf over draft fee
	 * @param tf transaction fee
	 * @param freeTrans free transactions allowed
	 */
	public CheckingAccount(String n, double odf, double tf, int freeTrans) {
		this(n,0,odf,tf,freeTrans);
	}
	
	/**
	 * deposits money into account, applies necessary fees, throws exception if not authorized
	 * @param amt money in dollars
	 */
	public void deposit(double amt) {
		if (amt <= 0) throw new IllegalArgumentException();
		super.deposit(amt);
		if (numTransactions >= FREE_TRANS) super.withdraw(TRANSACTION_FEE);
		numTransactions++;
	}
	
	/**
	 * withdraws money from account, applies necessary fees, throws exception if not authorized
	 * @param amt money in dollars
	 */
	public void withdraw(double amt) {
		if (getBalance()<0||amt<=0) throw new IllegalArgumentException();
		super.withdraw(amt);
		if (numTransactions >= FREE_TRANS) super.withdraw(TRANSACTION_FEE);
		if (getBalance()<0) super.withdraw(OVER_DRAFT_FEE);
		numTransactions++;
	}
	
	/**
	 * transfers money FROM this acc, to another, throws exception if not authorized
	 * @param other BankAccount to recieve money
	 * @param amt dollar amount
	 */
	public void transfer(BankAccount other, double amt) {
		if (!other.getName().equals(this.getName())) throw new IllegalArgumentException();
		if (getBalance()-amt<0) throw new IllegalArgumentException();
		withdraw(amt);
		other.deposit(amt);
	}
	
	/**
	 * Resets transactions this month to 0
	 */
	public void endOfMonthUpdate() {
		numTransactions = 0;
	}

}
