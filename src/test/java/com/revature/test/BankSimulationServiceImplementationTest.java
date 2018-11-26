package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dataAccess.BankSimulationDataAccess;
import com.revature.exception.DepositNegativeAmountException;
import com.revature.exception.WithdrawalAmountExceedsBalanceException;
import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.service.BankSimulationServiceImplementation;

public class BankSimulationServiceImplementationTest {
	
	private BankSimulationDataAccess bankSimDAO = new BankSimulationServiceImplementation();
	private User user;
	private String today;
	
	@Before
	public void setUp( ) {
		user = new User("username@email.com", "jdoe", "pass", 500);
		today = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	}
	
	@Test
	public void testRegisterUser_Successful() {
		assertTrue("user was not inserted into the table correctly", bankSimDAO.registerUser(user));
		assertEquals("invalid email address", "username@email.com",user.getEmail());
		assertEquals("username was not set properly", "jdoe", user.getUsername());
		assertEquals("password was not set properly", "pass", user.getPassword());
		assertEquals("wrong balance", 500, user.getBalance());
		assertTrue("no transactions should be tied to a new accont", user.getTransactions().isEmpty());
	}
	
	@Test
	public void testViewBalance_Successful( ) {
		assertEquals("incorrect post-deposit balance", 500, user.getBalance());
	}
	
	@Test
	public void testDeposit_Successful( ) {
		Transaction t = bankSimDAO.deposit(user, 100);
		assertNotNull("transaction object was null", t);
		assertEquals("incorrect transaction number", 1, t.getTransactionNumber());
		assertEquals("incorrect transaction date", today, t.getTransactionDate());
		assertEquals("incorrect transaction type", "deposit", t.getTransactionType());
		assertEquals("incorrect post-deposit balance", 600, user.getBalance());
	}
	
	@Test(expected=DepositNegativeAmountException.class)
	public void testDeposit_NegativeDepositAmount() {
		bankSimDAO.deposit(user, -100);
	}
	
	@Test
	public void testWithdraw_Successful( ) {
		Transaction t = bankSimDAO.withdraw(user, 200);
		assertNotNull("transaction object was null", t);
		assertEquals("incorrect transaction number", 1, t.getTransactionNumber());
		assertEquals("incorrect transaction date", today, t.getTransactionDate());
		assertEquals("incorrect transaction type", "withdraw", t.getTransactionType());
		assertEquals("incorrect post-withdraw balance", 300, user.getBalance());
	}
	
	@Test(expected = WithdrawalAmountExceedsBalanceException.class)
	public void testWithraw_InsufficientFunds( ) {
		bankSimDAO.withdraw(user, 700);
	}
	
	@Test
	public void testViewAllTransactions() {
		bankSimDAO.deposit(user, 100);		
		Transaction t1 = user.getTransactions().get(0);
		assertEquals("incorrect transaction number", 1, t1.getTransactionNumber());
		assertEquals("incorrect post-deposit balance", 600, user.getBalance());
		assertEquals("wrong transation date", today, t1.getTransactionDate().getTime());
		assertEquals("wrong transaction type", "deposit", t1.getTransactionType());
		
		bankSimDAO.withdraw(user, 300);
		Transaction t2 = user.getTransactions().get(1);
		assertEquals("incorrect transaction number", 2, t2.getTransactionNumber());
		assertEquals("incorrect post-withdraw balance", 300, user.getBalance());
		assertEquals("wrong transation date", today, t2.getTransactionDate().getTime());
		assertEquals("wrong transaction type", "withdraw", t1.getTransactionType());
	}
	
	@Test
	public void testLogin_Successful() {
		assertTrue(bankSimDAO.login(user));
	}
	
	@Test
	public void testLogout() {
		assertTrue(bankSimDAO.logout());
	}
	
	@After
	public void tearDown() {
		bankSimDAO = null;
	}
}
