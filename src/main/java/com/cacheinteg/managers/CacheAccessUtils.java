package com.cacheinteg.managers;


public class CacheAccessUtils {
	private EhCacheImpl ehCacheUtility;

	public EhCacheImpl getEhCacheUtility() {
		return ehCacheUtility;
	}

	public void setEhCacheUtility(EhCacheImpl ehCacheUtility) {
		this.ehCacheUtility = ehCacheUtility;
	}
}
