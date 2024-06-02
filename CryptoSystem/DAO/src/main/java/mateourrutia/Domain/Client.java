package mateourrutia.Domain;

import java.util.List;
import java.io.Serializable;

import mateourrutia.utils.Listed;
import mateourrutia.utils.ObjectWriter;

/**
 * Dilema Client --- Account.
 *
 * Con el fin de evitar la excesiva repeticion de datos y
 * evitar la fragmentacion de la informacion, todas las
 * Cuentas seran guardadas junto a cliente y seran accedidas
 * atraves de el.
 */
public class Client extends ObjectWriter implements Serializable {
	private Long 			cuit;
	private String 			name;
	private String 			lastname;
	private Long 			phone;
	private String 			email;
	private String 			address;
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
