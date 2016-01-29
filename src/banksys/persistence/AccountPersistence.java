package banksys.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import banksys.account.AbstractAccount;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;

public class AccountPersistence implements IAccountRepository {

	private File file;
	
	public AccountPersistence() throws IOException {
		file = new File("data.xml");
		
		if (!file.exists()) {
			file.createNewFile();
			XStream xs = new XStream();
			xs.toXML(new ArrayList<AbstractAccount>(), new FileOutputStream(file));
		}
	}

	@Override
	public void create(AbstractAccount account) throws AccountCreationException {
		List<AbstractAccount> list = null;
		try {
			list = read();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		if (search(account.getNumber(), list) == null) {
			list.add(account);
		}
		else {
			throw new AccountCreationException("OrdinaryAccount alredy exist!", account.getNumber());
		}
		
		try {
			write(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String number) throws AccountDeletionException {
		List<AbstractAccount> list = null;
		try {
			list = read();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		AbstractAccount account = search(number, list);
		if (account != null) {
			list.remove(account);
		}
		else {
			throw new AccountDeletionException("OrdinaryAccount doesn't exist!", number);
		}
		
		try {
			write(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public AbstractAccount retrieve(String number) throws AccountNotFoundException {
		List<AbstractAccount> list = null;
		try {
			list = read();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if (!list.isEmpty()) {
			for (AbstractAccount account : list) {
				if (account.getNumber().equals(number)) {
					return account;
				}
			}
		}
		throw new AccountNotFoundException("OrdinaryAccount not found!", number);
	}

	@Override
	public AbstractAccount[] list() {
		List<AbstractAccount> list = null;
		try {
			list = read();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		AbstractAccount[] array = null;
		
		if (!list.isEmpty()) {
			int i = 0;
			array = new AbstractAccount[list.size()];
			
			for (AbstractAccount account : list) {
				array[i++] = account;
			}
		}
		return array;
	}

	@Override
	public int numberOfAccounts() {
		List<AbstractAccount> list = null;
		try {
			list = read();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list.size();
	}
	
	@SuppressWarnings("unchecked")
	private List<AbstractAccount> read() throws FileNotFoundException {
		List<AbstractAccount> list = null;
		XStream xs = new XStream();
						
		list = (List<AbstractAccount>) xs.fromXML(new FileReader(file));
		
		if (list != null) {
			return list;
		}
		else {
			return new ArrayList<AbstractAccount>();
		}
	}
	
	private void write(List<AbstractAccount> list) throws FileNotFoundException {
		XStream xs = new XStream();
		xs.toXML(list, new FileOutputStream(file));
	}
	
	private AbstractAccount search(String number, List<AbstractAccount> list) {
		for (AbstractAccount account : list) {
			if (account.getNumber().equals(number)) {
				return account;
			}
		}
		return null;
	}

	@Override
	public void update(AbstractAccount account) throws FileNotFoundException {
		List<AbstractAccount> list = null;
		
		list = read();
		
		if (!list.isEmpty()) {
			for (AbstractAccount acc : list) {
				if (acc.getNumber().equals(account.getNumber())) {
					list.set(list.indexOf(acc), account);
				}
			}
		}
		
		write(list);
		
	}

}
