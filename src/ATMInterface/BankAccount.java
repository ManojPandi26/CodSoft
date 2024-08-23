package ATMInterface;

public class BankAccount {

	private double balance;

	public BankAccount(double initialBalance) {
		balance = initialBalance;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
		} else {
			throw new IllegalArgumentException("Deposit amount must be positive");
		}
	}

	public void withdraw(double amount) throws InsufficientFundsException {
		if (amount > 0 && amount <= balance) {
			balance -= amount;
		} else {
			throw new InsufficientFundsException("Please Enter the Valid Amount!.");
		}
	}

}
