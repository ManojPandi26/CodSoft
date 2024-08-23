package ATMInterface;

public class ATMMain {

	 public static void main(String[] args) {
	        BankAccount account = new BankAccount(1000);
	        ATM atm = new ATM(account);
	        atm.run();
	    }
}
