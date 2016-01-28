package banksys.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import banksys.account.AbstractAccount;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;

public class AccountPersistence implements IAccountRepository {

	private File file;
	private List<AbstractAccount> accounts;
	private XStream xs = new XStream(new DomDriver());

	
	public AccountPersistence() throws IOException {
		file = new File("data.xml");
		accounts = null;
		
		if(!file.exists()) {
			file.createNewFile();
			
			xs.toXML(new ArrayList<AbstractAccount>(), new FileOutputStream(file));
		}
	}

	@Override
	public void create(AbstractAccount account) throws AccountCreationException {
		List<AbstractAccount> list = reader();
		
		if (search(account.getNumber()) == null) {
			list.add(account);
		}
		else {
			throw new AccountCreationException("OrdinaryAccount alredy exist!", account.getNumber());
		}
		
		try {
			save();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String number) throws AccountDeletionException {
		List<AbstractAccount> list = reader();
		
		AbstractAccount acc = search(number);
		if (acc != null) {
			list.remove(acc);
		} else {
			throw new AccountDeletionException("OrdinaryAccount doesn't exist!", number);
		}
		
		try {
			save();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public AbstractAccount retrieve(String number) throws AccountNotFoundException {
		List<AbstractAccount> lista = reader();
		
		if (!lista.isEmpty()) {
			for (AbstractAccount conta : lista) {
				if (conta.getNumber().equals(number)) {
					return conta;
				}
			}
		}
		throw new AccountNotFoundException("OrdinaryAccount not found!", number);
	}

	@Override
	public AbstractAccount[] list() {
		List<AbstractAccount> list = reader();
		AbstractAccount[] array = null;
		
		if (!list.isEmpty()) {
			array = new AbstractAccount[list.size()];
			int i = 0;
			for (AbstractAccount acc : list) {
				array[i++] = acc;
			}
		}
		return array;
	}

	@Override
	public int numberOfAccounts() {
		List<AbstractAccount> list = reader();
		return list.size();
	}
	
	private List<AbstractAccount> reader() {
		List<AbstractAccount> list = null;
		
		list = (List<AbstractAccount>) xs.fromXML(file);

		if(list != null) {
			return list;
		}
		else {
			return new ArrayList<AbstractAccount>();
		}
	}
	
	private AbstractAccount search(String number) {
		for (AbstractAccount account : accounts) {
			if (account.getNumber().equals(number)) {
				return account;
			}
		}
		return null;
	}
	
	private void save() throws FileNotFoundException {
		xs.toXML(accounts, new FileOutputStream(file));
	}
	
}
