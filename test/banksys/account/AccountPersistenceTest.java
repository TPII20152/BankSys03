package banksys.account;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountNotFoundException;

public class AccountPersistenceTest {

	@Test
	public void testAccountPersistence() throws IOException {
//		AccountPersistence ap = new AccountPersistence();
		fail("Not yet implemented");
	}

	@Test
	public void testCreate() throws IOException, AccountCreationException {
//		AccountPersistence ap = new AccountPersistence();
//		AbstractAccount account = new OrdinaryAccount("1234");
//		ap.create(account);
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieve() {
		fail("Not yet implemented");
	}

	@Test
	public void testList() {
		fail("Not yet implemented");
	}

	@Test
	public void testNumberOfAccounts() throws IOException, AccountCreationException {
//		AccountPersistence ap = new AccountPersistence();
//		AbstractAccount account1 = new OrdinaryAccount("1234");
//		AbstractAccount account2 = new SpecialAccount("2345");
//		ap.create(account1);
//		ap.create(account2);
//		assertEquals(2, ap.numberOfAccounts(), 0);
		fail("Not yet implemented");
	}
	
	@Test
	public void testRead() throws IOException {
//		AccountPersistence ap = new AccountPersistence();
//		ArrayList<AbstractAccount> testList = new ArrayList<AbstractAccount>();
//		
//		assertEquals(testList, ap.reader());
		fail("Not yet implemented");
	}

	@Test
	public void testWrite() throws IOException, AccountNotFoundException {
//		AccountPersistence ap = new AccountPersistence();
//		AbstractAccount account = new OrdinaryAccount("1234");
//		List<AbstractAccount> list = null;
//		list.add(account);
//		ap.write(list);
//		assertEquals(account.getNumber(), ap.retrieve("1234").getNumber());
		fail("Not yet implemented");
	}
}
