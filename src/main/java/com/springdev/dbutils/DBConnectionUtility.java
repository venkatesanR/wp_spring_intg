package com.springdev.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBConnectionUtility {
	private static final transient Logger logger=Logger.getLogger(DBConnectionUtility.class);
	private static Connection connection = null;
	private static DataBaseInfo dbInfo;

	public static DataBaseInfo getDbInfo() {
		return dbInfo;
	}

	public void setDbInfo(DataBaseInfo dbInfo) {
		DBConnectionUtility.dbInfo = dbInfo;
	}

	public static void createDBConnection() {
		try {
			Class.forName(getDbInfo().getDbClassName());
			connection = DriverManager.getConnection(getDbInfo()
					.getDriverManager(), getDbInfo().getUserName(), getDbInfo()
					.getPassword());
		} catch (ClassNotFoundException e) {
			logger.error("Error while creating connection", e);
		} catch (SQLException e) {
			logger.error("Error while creating connection", e);
		}
	}

	public static void commitConnection() {
		if (connection != null) {
			try {
				connection.commit();
			} catch (SQLException e) {
				logger.error("Error while commitConnection", e);
			}
		}
	}

	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("Error while closeConnection", e);
			}
		}
	}
}
