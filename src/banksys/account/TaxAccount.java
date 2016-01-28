package banksys.account;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;

@XStreamAlias(value = "TaxAccount")
public class TaxAccount extends AbstractAccount {

	public TaxAccount(String number) {
		super(number);
	}

	public void debit(double amount) throws NegativeAmountException, InsufficientFundsException {
		if (amount > 0) {
			if (this.balance >= (amount + (amount * 0.001))) {
				this.balance = this.balance - (amount + (amount * 0.001));
			} else {
				throw new InsufficientFundsException(number, amount);
			}
		} else {
			throw new NegativeAmountException(amount);
		}
	}
}
