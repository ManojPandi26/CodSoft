package ATMInterfaceGUI;

import java.math.BigDecimal;

public class Account {

	private BigDecimal balance;

    public Account(BigDecimal initialBalance) {
        balance = initialBalance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

	public void withdraw(BigDecimal amount) throws InsufficientFundsException {
		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}

		if (amount.compareTo(balance) > 0) {
			throw new InsufficientFundsException("Insufficient funds");
		}

		balance = balance.subtract(amount);
	}

	public void deposit(BigDecimal amount) throws IllegalArgumentException {
	        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
	            throw new IllegalArgumentException("Amount must be positive");
	        }

	        balance = balance.add(amount);
	    }
    
}
