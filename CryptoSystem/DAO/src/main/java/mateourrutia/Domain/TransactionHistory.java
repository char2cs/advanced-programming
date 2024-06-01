package mateourrutia.Domain;

import mateourrutia.utils.ObjectWriter;

import java.io.Serializable;
import java.util.Date;

public class TransactionHistory extends ObjectWriter implements Serializable {
	private 	Date 		date 		= new Date();
	private 	Status 		status		;
	private 	Type 		type		;
	private 	double 		amount		;
	private 	Account 	fromAccount	;
	private 	Account 	toAccount 	= null;

	public enum Type {
		DEPOSIT,
		WITHDRAW,
		TRANSFER,
		CONVERT
	}

	public enum Status {
		SUCCESS,
		UNKNOWN_ERROR,
		ERROR_NOT_ENOUGH_BALANCE,
		ERROR_OVERDRAFT_ISSUE,
		ERROR_ACCOUNT_IS_NOT_WALLET,
		ERROR_WALLETS_ARE_NOT_FROM_SAME_CLIENT
	}

	public TransactionHistory(
			Type 	type,
			Status 	status,
			double 	amount,
			Account fromAccount
	) {
		super();
		this.status 		= status;
		this.type 			= type;
		this.amount 		= amount;
		this.fromAccount 	= fromAccount;
		this.toAccount		= null;
	}

	public TransactionHistory(
			Type 	type,
			Status 	status,
			double 	amount,
			Account fromAccount,
			Account toAccount
	) {
		super();
		this.type 			= type;
		this.status		 	= status;
		this.amount 		= amount;
		this.fromAccount 	= fromAccount;
		this.toAccount 		= toAccount;
	}

	public Date getDate() {
		return date;
	}

	public Status getStatus() {
		return status;
	}

	public Type getType() {
		return type;
	}

	public double getAmount() {
		return amount;
	}

	public Account getFromAccount() {
		return fromAccount;
	}

	public Account getToAccount() {
		return toAccount;
	}

	/**
	 * Will return true IF the transaction is only from and to the same account.
	 *
	 * @return boolean
	 */
	public boolean isPersonal() {
		return toAccount == null;
	}

	@Override
	public String toString() {
		return super.toString() + "TransactionHistory{" +
				"date=" + date +
				", status=" + status +
				", type=" + type +
				", amount=" + amount +
				", fromAccount=" + fromAccount +
				", toAccount=" + toAccount +
				"}\n";
	}
}
