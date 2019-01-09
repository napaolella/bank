
public class SavingsAccount extends BankAccount{
	
	private double intRate;
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	
	public SavingsAccount(String n, double b, double r, double mb, double mbf){
		super(n,b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	public SavingsAccount(String n, double r, double mb, double mbf) {
		this(n,0,r,mb,mbf);
	}
	
	public void withdraw(double amt) {
		if (amt <= 0) throw new IllegalArgumentException();
		if (getBalance()-amt<0) throw new IllegalArgumentException();
		super.withdraw(amt);
		if (getBalance()<MIN_BAL) super.withdraw(MIN_BAL_FEE);
	}
	
	public void deposit(double amt) {
		if (amt <= 0) throw new IllegalArgumentException();
		super.deposit(amt);
	}
	
	public void transfer(BankAccount other, double amt) {
		if (!other.getName().equals(this.getName())) throw new IllegalArgumentException();
		withdraw(amt);
		other.deposit(amt);
	}
	
	public void addInterest() {
		deposit(intRate*getBalance());
	}
	
	public void endOfMonthUpdate() {
		addInterest();
	}

}
