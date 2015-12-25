package com.springdev.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBConnectionUtility {
	private static final transient Logger logger = Logger.getLogger(DBConnectionUtility.class);
	private DataBaseInfo dbInfo;

	public DataBaseInfo getDbInfo() {
		return dbInfo;
	}

	public void setDbInfo(DataBaseInfo dbInfo) {
		this.dbInfo = dbInfo;
	}

	public Connection createDBConnection() {
		Connection connection = null;
		try {
			Class.forName(getDbInfo().getDbClassName());
			connection = DriverManager.getConnection(getDbInfo().getDriverManager(), getDbInfo().getUserName(),
					getDbInfo().getPassword());
		} catch (ClassNotFoundException e) {
			logger.error("Error while creating connection", e);
		} catch (SQLException e) {
			logger.error("Error while creating connection", e);
		}
		return connection;
	}

	public void commitConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.commit();
			} catch (SQLException e) {
				logger.error("Error while commitConnection", e);
			}
		}
	}

	public void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("Error while closeConnection", e);
			}
		}
	}
}
