package banksys.persistence;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.account.exception.NegativeAmountException;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;

public class AccountPersistenceTest {

	@Test
	public void testAccountPersistence() throws IOException {
		AccountPersistence ap = new AccountPersistence();
		assertEquals(12, ap.numberOfAccounts(), 0);
	}

	@Test
	public void testCreate() throws IOException, AccountCreationException {
		AccountPersistence ap = new AccountPersistence();
		AbstractAccount account = new OrdinaryAccount("2345");
		ap.create(account);
		assertEquals(13, ap.numberOfAccounts(), 0);
	}
	
	@Test (expected = AccountCreationException.class)
	public void testCreateTwice() throws IOException, AccountCreationException {
		AccountPersistence ap = new AccountPersistence();
		AbstractAccount account = new OrdinaryAccount("8901");
		ap.create(account);
		ap.create(account);
		assertEquals(6, ap.numberOfAccounts(), 0);
	}

	@Test
	public void testDelete() throws IOException, AccountDeletionException, AccountCreationException {
		AccountPersistence ap = new AccountPersistence();
		AbstractAccount account = new OrdinaryAccount("9012");
		ap.create(account);
		ap.delete("9012");
		assertEquals(13, ap.numberOfAccounts(), 0);
	}
	
	@Test (expected = AccountDeletionException.class)
	public void testDeleteTwice() throws IOException, AccountDeletionException, AccountCreationException {
		AccountPersistence ap = new AccountPersistence();
		ap.delete("0000");
		assertEquals(7, ap.numberOfAccounts(), 0);
	}

	@Test
	public void testRetrieve() throws IOException, AccountNotFoundException {
		AccountPersistence ap = new AccountPersistence();
		String number = "1111";
		AbstractAccount account = null;
		account = ap.retrieve(number);
		assertEquals(account.getNumber(), ap.retrieve(number).getNumber());
	}
	
	@Test (expected = AccountNotFoundException.class)
	public void testNoAccountRetrieve() throws IOException, AccountNotFoundException {
		AccountPersistence ap = new AccountPersistence();
		String number = "0000";
		AbstractAccount account = null;
		account = ap.retrieve(number);
		assertEquals(account.getNumber(), ap.retrieve(number).getNumber());
	}

	@Test
	public void testList() throws IOException {
		AbstractAccount[] array = null;
		AccountPersistence ap = new AccountPersistence();
		array = ap.list();
		assertEquals(array.length, ap.numberOfAccounts());
	}

	@Test
	public void testNumberOfAccounts() throws IOException {
		AccountPersistence ap = new AccountPersistence();
		assertEquals(11, ap.numberOfAccounts(), 0);
	}

	@Test
	public void testUpdate() throws IOException, AccountCreationException, NegativeAmountException {
		AccountPersistence ap = new AccountPersistence();
		String number = "0123";
		AbstractAccount account = new OrdinaryAccount(number);
		ap.create(account);
		account.credit(42.0);
		ap.update(account);
		assertEquals(42.0, account.getBalance(), 0);
	}

}
