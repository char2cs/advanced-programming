package mateourrutia.Domain;

import java.util.List;
import java.io.Serializable;

import mateourrutia.utils.Listed;
import mateourrutia.utils.ObjectWriter;

/**
 * Client --- Account relationship dilemma. Each Account would be saved inside each Client object.
 *
 * Both will contain some interface and implementation, Client for obvious reasons, however account
 * also would have, the account will retrieve all object, for ex., from all the users and interact
 * with that data from then. Although being to resource intensive, we make sure to not have repeated
 * data stored, or even inconsistencies through all the application, as data could easily change in
 * one place, but not the other.
 *
 * Also, each TransactionHistory object will contain a copy of each account state, and their users,
 * that will repeat data, but that is for historic and perseveration sake.
 */

public class Client extends ObjectWriter implements Serializable {
	private Long 		cuit;
	private String 		name;
	private String 		lastname;
	private Long 		phone;
	private String 		email;
	private String 		address;
	private Listed<Account> accounts = new Listed<>();

	public Client() { super(); }

	public Client(
			Long cuit,
			String name,
			String lastname,
			Long phone,
			String email,
			String address
	) {
		super();
		this.cuit = cuit;
		this.name = name;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

	public Client(
			Long cuit,
			String name,
			String lastname,
			Long phone,
			String email,
			String address,
			List<Account> accounts
	) {
		super();
		this.cuit 		= cuit;
		this.name 		= name;
		this.lastname 	= lastname;
		this.phone 		= phone;
		this.email 		= email;
		this.address 	= address;
		this.accounts 	= new Listed<>(accounts);
	}

	public Long getCuit() {
		return cuit;
	}

	public void setCuit(Long cuit) {
		this.cuit = cuit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Listed<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Listed<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return super.toString() + "Client{" +
				"cuit=" + cuit +
				", name='" + name + '\'' +
				", lastname='" + lastname + '\'' +
				", phone=" + phone +
				", email='" + email + '\'' +
				", address='" + address + '\'' +
				", accounts=" + accounts +
				"}\n";
	}
}
