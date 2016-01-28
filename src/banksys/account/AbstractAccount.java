package banksys.account;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;

public abstract class AbstractAccount {

	@XStreamAlias(value = "number")
	@XStreamAsAttribute
	protected String number;
	
	@XStreamAlias(value = "balance")
	protected double balance;

	public AbstractAccount(String number) {
		this.number = number;
		this.balance = 0;
	}

	public void credit(double amount) throws NegativeAmountException {
		if (amount > 0) {
			this.balance += amount;
		} else {
			throw new NegativeAmountException(amount);
		}
	}

	public abstract void debit(double amount) throws NegativeAmountException, InsufficientFundsException;

	public String getNumber() {
		return number;
	}

	public double getBalance() {
		return balance;
	}
}