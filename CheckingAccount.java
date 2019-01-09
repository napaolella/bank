
public class CheckingAccount extends BankAccount {
	
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final int FREE_TRANS;
	
	private int numTransactions;
	
	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans) {
		super(n,b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
		numTransactions = 0;
	}
	
	public CheckingAccount(String n, double odf, double tf, int freeTrans) {
		this(n,0,odf,tf,freeTrans);
	}
	
	public void deposit(double amt) {
		if (amt <= 0) throw new IllegalArgumentException();
		super.deposit(amt);
		if (numTransactions >= FREE_TRANS) super.withdraw(TRANSACTION_FEE);
		numTransactions++;
	}
	
	public void withdraw(double amt) {
		if (getBalance()<0||amt<=0) throw new IllegalArgumentException();
		super.withdraw(amt);
		if (numTransactions >= FREE_TRANS) super.withdraw(TRANSACTION_FEE);
		if (getBalance()<0) super.withdraw(OVER_DRAFT_FEE);
		numTransactions++;
	}
	
	public void transfer(BankAccount other, double amt) {
		if (!other.getName().equals(this.getName())) throw new IllegalArgumentException();
		if (getBalance()-amt<0) throw new IllegalArgumentException();
		withdraw(amt);
		other.deposit(amt);
	}
	
	public void endOfMonthUpdate() {
		numTransactions = 0;
	}

}
