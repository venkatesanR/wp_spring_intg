package com.springdev.dbutils;

public class DataBaseInfo {
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDbClassName() {
		return dbClassName;
	}
	public void setDbClassName(String dbClassName) {
		this.dbClassName = dbClassName;
	}
	public String getDriverManager() {
		return driverManager;
	}
	public void setDriverManager(String driverManager) {
		this.driverManager = driverManager;
	}
	private String password;
	private String dbClassName;
	private String driverManager;
	
}
