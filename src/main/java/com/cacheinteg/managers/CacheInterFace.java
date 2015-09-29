package com.cacheinteg.managers;

import java.util.List;
import java.util.Map;
/**
 * #1.Get cache using name
 * #2.Put cache using name
 * #3.Remove cache using name
 * #4.Get Write lock if the purpose is to modify the value for the key
 * #5.Must release the write lock after modification (usually in a finally block)
 * #6.Returns the list of keys for the named cache
 * #7.Retrieve all cached elements and return the named cache Note that this  could be an expensive operation
 * #8.Returns true if the key exists in the cache
 * #9.Returns the size of the cache (in memory + off memory) Note that this could be an expensive operation
 * #10.Deletes all cache entries of the named cache
 * #10.Calling system should call shutdown
 * #10.Allows cache manager to flush cache elements to off memory storage
 *
 */
public interface CacheInterFace {
	public Object get(String cacheName, Object key);

	public void put(String cacheName, Object key, Object value);

	public void remove(String cacheName, Object key);

	public void getWriteLock(String cacheName, Object key);

	public void releaseWriteLock(String cacheName, Object key);

	public List getKeys(String cacheName);

	public Map get(String cacheName);

	public boolean containsKey(String cacheName, Object key);

	public int size(String cacheName);

	public void clear(String cacheName);

	public void shutdown();

	public void flush(String cacheName);
}
