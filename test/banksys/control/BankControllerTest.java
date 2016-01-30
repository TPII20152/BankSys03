package banksys.control;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.control.exception.BankTransactionException;
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
	public void testGetBalance() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoTransfer() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoEarnInterest() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoEarnBonus() {
		fail("Not yet implemented");
	}

}
