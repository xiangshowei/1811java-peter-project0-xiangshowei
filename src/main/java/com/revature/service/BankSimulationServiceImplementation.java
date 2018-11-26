package com.revature.service;

import java.util.ArrayList;

import com.revature.dataAccess.BankSimulationDataAccess;
import com.revature.dataAccess.BankSimulationDataAccessJDBC;
import com.revature.exception.DepositNegativeAmountException;
import com.revature.exception.WithdrawalAmountExceedsBalanceException;
import com.revature.model.Transaction;
import com.revature.model.User;

public class BankSimulationServiceImplementation implements BankSimulationDataAccess {
	
	private BankSimulationDataAccess bankSimDAO = new BankSimulationDataAccessJDBC();

	@Override
	public boolean registerUser(User user) {
		return bankSimDAO.registerUser(user);
	}

	@Override
	public boolean login(User user) {
		return bankSimDAO.login(user);
	}

	@Override
	public boolean logout() {
		return bankSimDAO.logout();
	}

	@Override
	public Transaction deposit(User user, long depositAmount) throws DepositNegativeAmountException{	
		if(depositAmount < 0) {
			throw new DepositNegativeAmountException("Deposit amounts must be positive.");
		}
		Transaction t = bankSimDAO.deposit(user, depositAmount);
		user.getTransactions().add(t);
	
		return t;
	}

	@Override
	public Transaction withdraw(User user, long withdrawalAmount) throws WithdrawalAmountExceedsBalanceException{
		if(user.getBalance() - withdrawalAmount < 0) {
			throw new WithdrawalAmountExceedsBalanceException("Insufficient funds.");
		}
		Transaction t = bankSimDAO.withdraw(user, withdrawalAmount);
		user.getTransactions().add(t);
		
		return t;
	}

	@Override
	public boolean viewBalance(User user) {
		return bankSimDAO.viewBalance(user);
	}

	@Override
	public ArrayList<Transaction> viewAllTransactions(User user) {
		return bankSimDAO.viewAllTransactions(user);
	}
}
