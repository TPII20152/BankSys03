package banksys.control;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;
import banksys.control.exception.BankTransactionException;
import banksys.control.exception.IncompatibleAccountException;
import banksys.persistence.AccountPersistence;
import banksys.persistence.exception.AccountNotFoundException;

public class BankControllerTest {

	@Test
	public void testBankController() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "1234";
		AbstractAccount account = new OrdinaryAccount(number);
		bank.addAccount(account);
		assertEquals(0, bank.getBalance(number), 0);
	}

	@Test
	public void testExists() throws IOException, AccountNotFoundException {
		BankController bank = new BankController(new AccountPersistence());
		assertTrue(bank.exists("1234"));
	}
	
	@Test (expected = AccountNotFoundException.class)
	public void testNotExists() throws IOException, AccountNotFoundException {
		BankController bank = new BankController(new AccountPersistence());
		assertFalse(bank.exists("9999"));
	}

	@Test
	public void testAddAccount() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "2345";
		AbstractAccount account = new OrdinaryAccount(number);
		bank.addAccount(account);
		assertEquals(0, bank.getBalance(number), 0);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testAddAccountTwice() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "1111";
		AbstractAccount account = new OrdinaryAccount(number);
		bank.addAccount(account);
		assertEquals(0, bank.getBalance(number), 0);
	}

	@Test (expected = AccountNotFoundException.class)
	public void testRemoveAccount() throws IOException, BankTransactionException, AccountNotFoundException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "2345";
		bank.removeAccount(number);
		assertFalse(bank.exists(number));
	}
	
	@Test (expected = BankTransactionException.class)
	public void testRemoveAccountTwice() throws IOException, BankTransactionException, AccountNotFoundException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "2345";
		bank.removeAccount(number);
		assertFalse(bank.exists(number));
	}

	@Test
	public void testDoPositiveCredit() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "1234";
		double amount = 10.0;
		bank.doCredit(number, amount);
		assertEquals(amount, bank.getBalance(number), 0);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testDoNegativeCredit() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "1234";
		double amount = -10.0;
		bank.doCredit(number, amount);
		assertEquals(0, bank.getBalance(number), 0);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testNoAccountCredit() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "9999";
		double amount = 10.0;
		bank.doCredit(number, amount);
		assertEquals(0, bank.getBalance(number), 0);
	}

	@Test
	public void testDoPositiveDebit() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "3456";
		AbstractAccount account = new OrdinaryAccount(number);
		double creditAmount = 10.0;
		double debitAmount = 2.0;
		bank.addAccount(account);
		bank.doCredit(number, creditAmount);
		bank.doDebit(number, debitAmount);
		assertEquals(8.0, bank.getBalance(number), 0);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testDoNegativeDebit() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "3456";
		double amount = -2.0;
		bank.doDebit(number, amount);
		assertEquals(8.0, bank.getBalance(number), 0);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testNoFundsDebit() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "1111";
		double amount = 100000000;
		bank.doDebit(number, amount);
		assertEquals(10.0, bank.getBalance(number), 0);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testNoAccountDebit() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "9999";
		double amount = 10.0;
		bank.doDebit(number, amount);
		assertEquals(0, bank.getBalance(number), 0);
	}

	@Test
	public void testGetBalance() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "2222";
		bank.getBalance(number);
		assertEquals(10.0, bank.getBalance(number), 0);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testNoAccountBalance() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "9999";
		bank.getBalance(number);
		assertEquals(0, bank.getBalance(number), 0);
	}

	@Test
	public void testDoPositiveTransfer() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String fromNumber = "4567";
		String toNumber = "5678";
		AbstractAccount fromAccount = new OrdinaryAccount(fromNumber);
		AbstractAccount toAccount = new OrdinaryAccount(toNumber);
		bank.addAccount(fromAccount);
		bank.addAccount(toAccount);
		bank.doCredit(fromNumber, 15.0);
		bank.doCredit(toNumber, 10.0);
		double amount = 2.0;
		bank.doTransfer(fromNumber, toNumber, amount);
		assertEquals(13.0, bank.getBalance(fromNumber), 0);
		assertEquals(12.0, bank.getBalance(toNumber), 0);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testDoNegativeTransfer() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String fromNumber = "2222";
		String toNumber = "4444";
		double amount = -2.0;
		bank.doTransfer(fromNumber, toNumber, amount);
		assertEquals(10.0, bank.getBalance(fromNumber), 0);
		assertEquals(40.4, bank.getBalance(toNumber), 0);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testNoFundsTransfer() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String fromNumber = "2222";
		String toNumber = "4444";
		double amount = 1000000;
		bank.doTransfer(fromNumber, toNumber, amount);
		assertEquals(10.0, bank.getBalance(fromNumber), 0);
		assertEquals(40.4, bank.getBalance(toNumber), 0);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testNoAccountTransfer() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String fromNumber = "0000";
		String toNumber = "9999";
		double amount = 1000000;
		bank.doTransfer(fromNumber, toNumber, amount);
		assertEquals(0, bank.getBalance(fromNumber), 0);
		assertEquals(0, bank.getBalance(toNumber), 0);
	}

	@Test
	public void testDoEarnInterest() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "6789";
		AbstractAccount account = new SavingsAccount(number);
		double amount = 10.0;
		bank.addAccount(account);
		bank.doCredit(number, amount);
		bank.doEarnInterest(number);
		assertEquals(10.01, bank.getBalance(number), 0);
	}
	
	@Test (expected = IncompatibleAccountException.class)
	public void testIncompatibleEarnInterest() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "1111";
		bank.doEarnInterest(number);
		assertEquals(0, bank.getBalance(number), 0);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testNoAccountEarnInterest() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "0000";
		bank.doEarnInterest(number);
		assertEquals(0, bank.getBalance(number), 0);
	}

	@Test
	public void testDoEarnBonus() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "7890";
		AbstractAccount account = new SpecialAccount(number);
		double amount = 10.0;
		bank.addAccount(account);
		bank.doCredit(number, amount);
		bank.doEarnBonus(number);
		assertEquals(10.1, bank.getBalance(number), 0);
	}
	
	@Test (expected = IncompatibleAccountException.class)
	public void testIncompatibleEarnBonus() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "1111";
		bank.doEarnBonus(number);
		assertEquals(0, bank.getBalance(number), 0);
	}
	
	@Test (expected = BankTransactionException.class)
	public void testNoAccountEarnBonus() throws IOException, BankTransactionException {
		BankController bank = new BankController(new AccountPersistence());
		String number = "0000";
		bank.doEarnBonus(number);
		assertEquals(0, bank.getBalance(number), 0);
	}

}
