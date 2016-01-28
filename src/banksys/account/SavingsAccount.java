package banksys.account;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import banksys.account.exception.NegativeAmountException;

@XStreamAlias(value = "SavingsAccount")
public class SavingsAccount extends OrdinaryAccount {

	public SavingsAccount(String number) {
		super(number);
	}

	public void earnInterest() {
		try {
			this.credit(this.getBalance() * 0.001);
		} catch (NegativeAmountException e) {
		}
	}
}
