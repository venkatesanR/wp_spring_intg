package com.springdev.dbutils;

import java.util.List;

@SuppressWarnings("rawtypes")
public interface DAOInterFace {
	public void insert(String sqlKey, Object objectInfo);

	public boolean update(String sqlKey, Object objectInfo);

	public <T> List<T> select(String sqlKey, Class clazz, Object objectInfo);

	public boolean delete(String sqlKey, Object objectInfo);

	public long get_count(String sqlKey, Object objectInfo);
}
