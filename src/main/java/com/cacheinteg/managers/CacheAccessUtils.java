package com.cacheinteg.managers;


public class CacheAccessUtils {
	private EhCacheImpl ehCache;

	public EhCacheImpl getEhCache() {
		return ehCache;
	}

	public void setEhCache(EhCacheImpl ehCache) {
		this.ehCache = ehCache;
	}

	public void testPut(String cacheName,String keyName,String value) {
		ehCache.put(cacheName, keyName, value);
	}
	public Object testGet(String cacheName,String keyName) {
		return ehCache.get( cacheName,keyName);
	}
}
