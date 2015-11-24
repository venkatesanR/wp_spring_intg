package com.cacheinteg.managers;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.springdev.intg.SpringTestBeanImpl;

import net.spy.memcached.util.CacheLoader;

public class MemCacheImpl implements CacheInterFace {
	private static final transient Logger logger = Logger.getLogger(SpringTestBeanImpl.class);
	private CacheLoader cac;
	@Override
	public Object get(String cacheName, Object key) {
		return null;
	}

	@Override
	public void put(String cacheName, Object key, Object value) {
		
	}

	@Override
	public void remove(String cacheName, Object key) {
		
	}

	@Override
	public void getWriteLock(String cacheName, Object key) {
		
	}

	@Override
	public void releaseWriteLock(String cacheName, Object key) {
		
	}

	@Override
	public List getKeys(String cacheName) {
		return null;
	}

	@Override
	public Map get(String cacheName) {
		return null;
	}

	@Override
	public boolean containsKey(String cacheName, Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size(String cacheName) {
		return 0;
	}

	@Override
	public void clear(String cacheName) {
		
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flush(String cacheName) {
		
	}

}
