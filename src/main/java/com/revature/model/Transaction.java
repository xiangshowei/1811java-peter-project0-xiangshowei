package com.revature.model;

import java.util.GregorianCalendar;

public class Transaction {

	private long transactionNumber;
	private GregorianCalendar transactionDate;
	private String transactionType;

	public Transaction(long transactionNumber, GregorianCalendar transacionDate, String transactionType) {
		this.transactionNumber = transactionNumber;
		this.transactionDate = transacionDate;
		this.transactionType = transactionType;
	}
	
	public long getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(long transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public GregorianCalendar getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(int year, int month, int date) {
		transactionDate.set(year, month, date);
	}
	
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	@Override
	public String toString() {
		return "Transaction [transactionNumber=" + transactionNumber + "\n" 
				+ "transactionDate=" + transactionDate.getTime() + "\n"
				+ ", transactionType=" + transactionType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result + (int) (transactionNumber ^ (transactionNumber >>> 32));
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
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
		Transaction other = (Transaction) obj;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (transactionNumber != other.transactionNumber)
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}
}
