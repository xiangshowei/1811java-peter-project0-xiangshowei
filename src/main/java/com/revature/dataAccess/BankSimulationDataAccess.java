package com.revature.dataAccess;

import java.util.ArrayList;

import com.revature.model.Transaction;
import com.revature.model.User;

public interface BankSimulationDataAccess {
	
	public boolean registerUser(User user);
	
	public boolean login(User user);
	
	public boolean logout();
	
	public Transaction deposit(User user, long depositAmount);
	
	public Transaction withdraw(User user, long withdrawalAmount);
	
	public boolean viewBalance(User user);
	
	public ArrayList<Transaction> viewAllTransactions(User user);
}
