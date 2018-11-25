package com.revature.model;

import java.util.ArrayList;

public class Account {
	
	private long accountNumber;
	private long balance;
	private ArrayList<Transaction> transactions;
	
	public Account(long accountNumber, long balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		transactions = new ArrayList<Transaction>();
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Transactions associated with this account:" + "\n");
		
		if(transactions != null) {
			for (Transaction t : transactions) {
				sb.append(t.toString() + "\n");
			}
		}
		
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + "\n" + sb.toString()
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountNumber ^ (accountNumber >>> 32));
		result = prime * result + (int) (balance ^ (balance >>> 32));
		result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (balance != other.balance)
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		return true;
	}
}
