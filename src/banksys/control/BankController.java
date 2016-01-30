package banksys.control;

import java.io.IOException;

import banksys.account.AbstractAccount;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;
import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;
import banksys.control.exception.BankTransactionException;
import banksys.control.exception.IncompatibleAccountException;
import banksys.logging.Logger;
import banksys.persistence.IAccountRepository;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;

public class BankController {

	private IAccountRepository repository;

	public BankController(IAccountRepository repository) {
		this.repository = repository;
	}
	
	public boolean exists(String number) throws AccountNotFoundException {		
		AbstractAccount account = this.repository.retrieve(number);
		if (account != null) {
			return true;
		} else {
			throw new AccountNotFoundException("OrdinaryAccount not found!", number);
		}
	}

	public void addAccount(AbstractAccount account) throws BankTransactionException, IOException {
		try {
			this.repository.create(account);
		} catch (AccountCreationException ace) {
			Logger.register("[Error] <Add Account> Account number " + account.getNumber() + " wasn't created (account already exist)");
			throw new BankTransactionException(ace);
		}
		Logger.register("[Success] <Add Account> Account number " + account.getNumber() + " was created");
	}

	public void removeAccount(String number) throws BankTransactionException, IOException {
		try {
			this.repository.delete(number);
		} catch (AccountDeletionException ade) {
			Logger.register("[Error] <Remove Account> Account number " + number + " wasn't removed (account doesn't exist)");
			throw new BankTransactionException(ade);
		}
		Logger.register("[Success] <Remove Account> Account number " + number + " was removed");
	}

	public void doCredit(String number, double amount) throws BankTransactionException, IOException {
		AbstractAccount account;
		try {
			account = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			Logger.register("[Error] <Retrieve> Account number " + number + " not found");
			throw new BankTransactionException(anfe);
		}
		
		try {
			account.credit(amount);
			this.repository.update(account);
		} catch (NegativeAmountException nae) {
			Logger.register("[Error] <Credit> Account number " + number + " didn't get the credit of " + amount + " (negative amount)");
			throw new BankTransactionException(nae);
		}
		Logger.register("[Success] <Credit> Account number " + number + " got the credit of " + amount);

	}

	public void doDebit(String number, double amount) throws BankTransactionException, IOException {
		AbstractAccount account;
		try {
			account = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			Logger.register("[Error] <Retrieve> Account number " + number + " not found");
			throw new BankTransactionException(anfe);
		}
		try {
			account.debit(amount);
			this.repository.update(account);
		} catch (InsufficientFundsException ife) {
			Logger.register("[Error] <Debit> Account number " + number + " didn't get the debit of " + amount + " (insufficient funds)");
			throw new BankTransactionException(ife);
		} catch (NegativeAmountException nae) {
			Logger.register("[Error] <Debit> Account number " + number + " didn't get the debit of " + amount + " (negative amount)");
			throw new BankTransactionException(nae);
		}
		Logger.register("[Success] <Debit> Account number " + number + " got the debit of " + amount);
	}

	public double getBalance(String number) throws BankTransactionException, IOException {
		AbstractAccount account;
		try {
			account = this.repository.retrieve(number);
			Logger.register("[Success] <Balance> Account number " + number + " consulted balance: " + account.getBalance());
			return account.getBalance();
		} catch (AccountNotFoundException anfe) {
			Logger.register("[Error] <Retrieve> Account number " + number + " not found");
			throw new BankTransactionException(anfe);
		}
	}

	public void doTransfer(String fromNumber, String toNumber, double amount) throws BankTransactionException, IOException {
		AbstractAccount fromAccount;
		try {
			fromAccount = this.repository.retrieve(fromNumber);
		} catch (AccountNotFoundException anfe) {
			Logger.register("[Error] <Retrieve> Account number " + fromNumber + " not found");
			throw new BankTransactionException(anfe);
		}

		AbstractAccount toAccount;
		try {
			toAccount = this.repository.retrieve(toNumber);
		} catch (AccountNotFoundException anfe) {
			Logger.register("[Error] <Retrieve> Account number " + toNumber + " not found");
			throw new BankTransactionException(anfe);
		}

		try {
			fromAccount.debit(amount);
			toAccount.credit(amount);
			this.repository.update(fromAccount);
			this.repository.update(toAccount);

		} catch (InsufficientFundsException sie) {
			Logger.register("[Error] <Transfer> The amount of " + amount + " wasn't transfered from " + fromNumber + " to " + toNumber + " (insufficient funds)");
			throw new BankTransactionException(sie);
		} catch (NegativeAmountException nae) {
			Logger.register("[Error] <Transfer> The amount of " + amount + " wasn't transfered from " + fromNumber + " to " + toNumber + " (negative amount)");
			throw new BankTransactionException(nae);
		}
		
		Logger.register("[Success] <Transfer> The amount of " + amount + " was transfered from " + fromNumber + " to " + toNumber);
	}

	public void doEarnInterest(String number) throws BankTransactionException, IncompatibleAccountException, IOException {
		AbstractAccount auxAccount;
		try {
			auxAccount = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			Logger.register("[Error] <Retrieve> Account number " + number + " not found");
			throw new BankTransactionException(anfe);
		}

		if (auxAccount instanceof SavingsAccount) {
			((SavingsAccount) auxAccount).earnInterest();
			this.repository.update(auxAccount);
		} else {
			Logger.register("[Error] <Earn Interest> Account number " + number + " is not compatible with this operation (not a Savings Account)");
			throw new IncompatibleAccountException(number);
		}
		Logger.register("[Success] <Earn Interest> Account number " + number + " got interest credited");
	}

	public void doEarnBonus(String number) throws BankTransactionException, IncompatibleAccountException, IOException {
		AbstractAccount auxAccount;
		try {
			auxAccount = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			Logger.register("[Error] <Retrieve> Account number " + number + " not found");
			throw new BankTransactionException(anfe);
		}

		if (auxAccount instanceof SpecialAccount) {
			((SpecialAccount) auxAccount).earnBonus();
			this.repository.update(auxAccount);
		} else {
			Logger.register("[Error] <Earn Bonus> Account number " + number + " is not compatible with this operation (not a Special Account)");
			throw new IncompatibleAccountException(number);
		}
		Logger.register("[Success] <Earn Bonus> Account number " + number + " got bonus credited");
	}
}
