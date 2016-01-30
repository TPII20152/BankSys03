package banksys.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;

public class AccountVectorTest {

	@Test
	public void testAccountVector() {
		AccountVector accountVector = new AccountVector();
		assertEquals(0, accountVector.numberOfAccounts(), 0);
	}

	@Test
	public void testDelete() throws AccountCreationException, AccountDeletionException {
		AccountVector accountVector = new AccountVector();
		AbstractAccount account = new OrdinaryAccount("1234");
		accountVector.create(account);
		assertEquals(1, accountVector.numberOfAccounts(), 0);
		accountVector.delete(account.getNumber());
		assertEquals(0, accountVector.numberOfAccounts(), 0);
	}
	
	@Test (expected = AccountDeletionException.class)
	public void testNoAccountDelete() throws AccountCreationException, AccountDeletionException {
		AccountVector accountVector = new AccountVector();
		String number = "9999";
		accountVector.delete(number);
		assertEquals(0, accountVector.numberOfAccounts(), 0);
	}

	@Test
	public void testCreate() throws AccountCreationException {
		AccountVector accountVector = new AccountVector();
		String number = "1234";
		AbstractAccount account = new OrdinaryAccount(number);
		accountVector.create(account);
		assertEquals(1, accountVector.numberOfAccounts(), 0);
	}
	
	@Test (expected = AccountCreationException.class)
	public void testCreateTwice() throws AccountCreationException {
		AccountVector accountVector = new AccountVector();
		String number = "1234";
		AbstractAccount account = new OrdinaryAccount(number);
		accountVector.create(account);
		accountVector.create(account);
		assertEquals(1, accountVector.numberOfAccounts(), 0);
	}

	@Test
	public void testList() throws AccountCreationException {
		AbstractAccount[] array = null;
		AccountVector accountVector = new AccountVector();
		AbstractAccount account1 = new OrdinaryAccount("1234");
		AbstractAccount account2 = new OrdinaryAccount("2345");
		AbstractAccount account3 = new OrdinaryAccount("3456");
		accountVector.create(account1);
		accountVector.create(account2);
		accountVector.create(account3);
		array = accountVector.list();
		assertEquals(array.length, accountVector.numberOfAccounts(), 0);
	}

	@Test
	public void testNumberOfAccounts() throws AccountCreationException {
		AccountVector accountVector = new AccountVector();
		AbstractAccount account1 = new OrdinaryAccount("1234");
		AbstractAccount account2 = new OrdinaryAccount("2345");
		AbstractAccount account3 = new OrdinaryAccount("3456");
		accountVector.create(account1);
		accountVector.create(account2);
		accountVector.create(account3);
		assertEquals(3, accountVector.numberOfAccounts(), 0);
	}

	@Test
	public void testRetrieve() throws AccountCreationException, AccountNotFoundException {
		AccountVector accountVector = new AccountVector();
		AbstractAccount account1 = new OrdinaryAccount("1234");
		AbstractAccount account2 = new OrdinaryAccount("2345");
		AbstractAccount account3 = new OrdinaryAccount("3456");
		accountVector.create(account1);
		accountVector.create(account2);
		accountVector.create(account3);
		assertEquals(account2.getNumber(), accountVector.retrieve("2345").getNumber());
	}
	
	@Test (expected = AccountNotFoundException.class)
	public void testNoAccountRetrieve() throws AccountCreationException, AccountNotFoundException {
		AccountVector accountVector = new AccountVector();
		String number = "9999";
		assertEquals(number, accountVector.retrieve(number).getNumber());
	}

}
