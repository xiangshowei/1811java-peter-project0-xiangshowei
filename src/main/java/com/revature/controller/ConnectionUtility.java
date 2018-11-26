package com.revature.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionUtility {

	private static final Logger LOGGER = Logger.getLogger(ConnectionUtility.class);

	public static Connection getConnection() throws SQLException{
		String url = System.getenv("BANKSIM_DATABASEURL");
		String username = System.getenv("BANKSIM_USERNAME");
		String password = System.getenv("BANKSIM_PASSWORD");

		return DriverManager.getConnection(url, username, password);
	}

	public static void main(String[] args) {

		LOGGER.trace("Attempting to connect to database...");

		try(Connection connection = ConnectionUtility.getConnection()) {
			LOGGER.info("Connection successful");
		} 
		catch (SQLException sqle) {
			LOGGER.error("Couldn't connect the database", sqle);;
		}
	}

}
