package com.springdev.dbutils;

import java.util.List;

public class AbstractManagerDAO implements DAOInterFace{
	private DBConnectionUtility dbConn;
	
	public DBConnectionUtility getDbConn() {
		return dbConn;
	}

	public void setDbConn(DBConnectionUtility dbConn) {
		this.dbConn = dbConn;
	}

	@Override
	public void insert(String sqlKey, Object objectInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(String sqlKey, Object objectInfo) {
		return false;
	}

	@Override
	public <T> List<T> select(String sqlKey, Object objectInfo) {
		return null;
	}

	@Override
	public boolean delete(String sqlKey, Object objectInfo) {
		return false;
	}

	@Override
	public long get_count(String sqlKey, Object objectInfo) {
		return 0;
	}


}
