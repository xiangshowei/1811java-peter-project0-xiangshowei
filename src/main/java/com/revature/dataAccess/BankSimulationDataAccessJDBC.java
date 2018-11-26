package com.revature.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.revature.controller.ConnectionUtility;
import com.revature.model.Transaction;
import com.revature.model.User;

public class BankSimulationDataAccessJDBC implements BankSimulationDataAccess {

	private Logger LOGGER = Logger.getLogger(BankSimulationDataAccessJDBC.class);
	private int parameterIndex = 0;
	private String query; 

	@Override
	public boolean registerUser(User user) {
		try (Connection connection = ConnectionUtility.getConnection()){

			query = "INSERT INTO BANKUSER VALUES(?,?,?,?)";

			PreparedStatement pStatement = connection.prepareStatement(query);
			pStatement.setString(++parameterIndex, user.getEmail());
			pStatement.setString(++parameterIndex, user.getUsername());
			pStatement.setString(++parameterIndex, user.getPassword());
			pStatement.setLong(++parameterIndex, user.getBalance());


			if(pStatement.executeUpdate() > 0) {
				pStatement.execute("COMMIT");
				return true;
			}
		} 
		catch (SQLException sqle) {
			LOGGER.error(sqle);
		}
		
		return false;
	}

	@Override
	public boolean login(User user) {
		try (Connection connection = ConnectionUtility.getConnection()){
			query = "SELECT USERNAME FROM BANKUSER "
					+ "WHERE USERNAME = ? AND PASSWD = ?";

			PreparedStatement pStatement = connection.prepareStatement(query);
			pStatement.setString(++parameterIndex, user.getUsername());
			pStatement.setString(++parameterIndex, user.getPassword());
			
			if(pStatement.executeUpdate() > 0) {
				return true;
			}

		} 
		catch (SQLException sqle) {
			LOGGER.error(sqle);
		}

		return false;
	}

	@Override
	public boolean logout() {
		try (Connection connection = ConnectionUtility.getConnection()){
			connection.close();
			
			if(connection.isClosed()) {
				return true;
			}
		}
		catch (SQLException sqle) {
			LOGGER.error(sqle);
		}
		
		return false;
	}

	@Override
	public Transaction deposit(User user, long depositAmount) {
		try (Connection connection = ConnectionUtility.getConnection()){

			GregorianCalendar today = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
			Transaction ta = new Transaction(today, "DEPOSIT");

			query = "INSERT INTO BANKUSER VALUES(?,?,?,?,?)";
			PreparedStatement pStatement = connection.prepareStatement(query);
			pStatement.setString(++parameterIndex, user.getEmail());
			pStatement.setString(++parameterIndex, user.getUsername());
			pStatement.setString(++parameterIndex, user.getPassword());
			pStatement.setLong(++parameterIndex, user.getBalance() + depositAmount);
			pStatement.setLong(++parameterIndex, ta.getTransactionNumber());

			if(pStatement.executeUpdate() > 0) {
				return ta;
			}
		} 

		catch (SQLException sqle) {
			LOGGER.error(sqle);
		}

		return null;
	}

	@Override
	public Transaction withdraw(User user, long withdrawalAmount) {
		try (Connection connection = ConnectionUtility.getConnection()){

			GregorianCalendar today = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
			Transaction ta = new Transaction(today, "WITHDRAW");

			query = "INSERT INTO BANKUSER VALUES(?,?,?,?,?)";
			PreparedStatement pStatement = connection.prepareStatement(query);
			pStatement.setString(++parameterIndex, user.getEmail());
			pStatement.setString(++parameterIndex, user.getUsername());
			pStatement.setString(++parameterIndex, user.getPassword());
			pStatement.setLong(++parameterIndex, user.getBalance() - withdrawalAmount);
			pStatement.setLong(++parameterIndex, ta.getTransactionNumber());

			if(pStatement.executeUpdate() > 0) {
				return ta;
			}
		} 

		catch (SQLException sqle) {
			LOGGER.error(sqle);
		}

		return null;
	}

	@Override
	public boolean viewBalance(User user) {
		try (Connection connection = ConnectionUtility.getConnection()){
			query = "SELECT BALANCE FROM BANKUSER WHERE USERNAME = ?";

			PreparedStatement pStatement = connection.prepareStatement(query);
			pStatement.setString(++parameterIndex, user.getUsername());

			if(pStatement.executeUpdate() > 0) {
				return true;
			}
		} 
		
		catch (SQLException sqle) {
			LOGGER.error(sqle);
		}
		
		return false;
	}

	@Override
	public ArrayList<Transaction> viewAllTransactions(User user) {
		try (Connection connection = ConnectionUtility.getConnection()){
			query = "SELECT * FROM TRANSACTIONS";

			connection.prepareStatement(query);
		} 
		catch (SQLException sqle) {
			LOGGER.error(sqle);
		}
		
		return user.getTransactions();
	}

}
