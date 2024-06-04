package mateourrutia.Domain;

import mateourrutia.utils.ObjectWriter;

import java.io.Serializable;
import java.util.Date;

/**
 * Esta clase no esta relacionada de manera directa con el cliente.
 *
 * Esto es debido a que TransactionHistory esta pensado como un tipo
 * historico, por ende se capta y se guardan las respectivas cuentas
 * de cada cliente y no se vuelven a actualizar para mantener el estado
 * final de aquella transaccion.
 *
 * Denominamos transaccion a cualquier tipo de movimiento dentro de las
 * cuentas, como por ej, conversion, transaccion, deposito y retiro.
 */
public class TransactionHistory extends ObjectWriter implements Serializable {
	private 	Date 		date 		= new Date();
	private 	Status 		status		;
	private 	Type 		type		;
	private 	double 		amount		;
	private 	Account 	fromAccount	;
	private 	Account 	toAccount 	= null;

	public enum Type {
		ALL, // Solo para los filtros
		DEPOSIT,
		WITHDRAW,
		TRANSFER,
		CONVERT
	}

	public enum Status {
		ALL, // Solo para los filtros
		SUCCESS,
		UNKNOWN_ERROR,
		ERROR_NOT_ENOUGH_BALANCE,
		ERROR_OVERDRAFT_ISSUE,
		ERROR_ACCOUNT_IS_NOT_WALLET,
		ERROR_ACCOUNT_IS_WALLET,
		ERROR_WALLETS_ARE_NOT_FROM_SAME_CLIENT,
		ERROR_ACCOUNTS_ARE_THE_SAME_ACCOUNT,
		ERROR_WALLETS_ARE_DIFFERENT_TYPE
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
