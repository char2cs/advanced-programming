package mateourrutia.domain;

import java.util.List;

public class Client {
	private Integer 		cuit;
	private String 			name;
	private String 			lastname;
	private Integer 		phone;
	private String 			email;
	private String 			address;
	List<SavingsAccount> 	savingsAccounts;
	List<CheckingAccount> 	checkingAccounts;
	List<Wallet> 			wallets;

	public Client() {}

	public Client(
			Integer cuit,
			String name,
			String lastname,
			Integer phone,
			String email,
			String address
	) {
		this.cuit = cuit;
		this.name = name;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

	public Client(
			Integer cuit,
			String name,
			String lastname,
			Integer phone,
			String email,
			String address,
			List<SavingsAccount> savingsAccounts,
			List<CheckingAccount> checkingAccounts,
			List<Wallet> wallets
	) {
		this.cuit = cuit;
		this.name = name;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.savingsAccounts = savingsAccounts;
		this.checkingAccounts = checkingAccounts;
		this.wallets = wallets;
	}

	public Integer getCuit() {
		return cuit;
	}

	public void setCuit(Integer cuit) {
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

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
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

	public List<SavingsAccount> getSavingsAccounts() {
		return savingsAccounts;
	}

	public List<CheckingAccount> getCheckingAccounts() {
		return checkingAccounts;
	}

	public List<Wallet> getWallets() {
		return wallets;
	}

	public void addSavingsAccount(
			SavingsAccount savingsAccount
	) {
		this.savingsAccounts.add(savingsAccount);
	}

	public void addCheckingAccount(
			CheckingAccount checkingAccount
	) {
		this.checkingAccounts.add(checkingAccount);
	}

	public void addWallet(
			Wallet wallet
	) {
		this.wallets.add(wallet);
	}
}
