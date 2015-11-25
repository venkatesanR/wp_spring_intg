package com.cacheinteg.managers;

import java.net.URL;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import com.springdev.intg.SpringTestBeanImpl;

public class EhCacheImpl implements CacheInterFace {
	private static final transient Logger logger = Logger
			.getLogger(SpringTestBeanImpl.class);
	private CacheManager cacheManager;

	public EhCacheImpl(String ehCacheConfigLoc) {
		cacheManager = new CacheManager(
				this.getCacheConfiguration(ehCacheConfigLoc));
	}

	public EhCacheImpl() {
		cacheManager = new CacheManager();
	}

	@Override
	public Object get(String cacheName, Object key) {
		Element elem = getCache(cacheName).get(key);
		return elem == null ? null : elem.getObjectValue();
	}

	@Override
	public void put(String cacheName, Object key, Object value) {
		getCache(cacheName).put(new Element(key, value));
	}

	@Override
	public void remove(String cacheName, Object key) {
		getCache(cacheName).remove(key);
	}

	@Override
	public void getWriteLock(String cacheName, Object key) {
		if (!getCache(cacheName).isWriteLockedByCurrentThread(key)) {
			getCache(cacheName).acquireWriteLockOnKey(key);
		}
	}

	@Override
	public void releaseWriteLock(String cacheName, Object key) {
		if (getCache(cacheName).isWriteLockedByCurrentThread(key)) {
			getCache(cacheName).releaseWriteLockOnKey(key);
		}
	}

	@Override
	public List getKeys(String cacheName) {
		return getCache(cacheName).getKeys();
	}

	@Override
	public Map get(String cacheName) {
		Ehcache ehc = getCache(cacheName);
		if (ehc == null)
			return null;
		logger.error(cacheName + "Cache found");
		return ehc.getAllWithLoader(ehc.getKeys(), null);
	}

	@Override
	public boolean containsKey(String cacheName, Object key) {
		return this.getCache(cacheName).isKeyInCache(key);
	}

	@Override
	public int size(String cacheName) {
		return this.getCache(cacheName).getSize();
	}

	@Override
	public void clear(String cacheName) {
		this.getCache(cacheName).removeAll();
	}

	@Override
	public void shutdown() {
		try {
			cacheManager.shutdown();
		} catch (Exception e) {
			logger.error("error while shutting down EH Cache");
		}
	}

	@Override
	public void flush(String cacheName) {
		getCache(cacheName).flush();
	}

	private Ehcache getCache(String cacheName) {
		return cacheManager.getEhcache(cacheName);
	}

	private URL getCacheConfiguration(String cacheConfigFile) {
		URL url = this.getClass().getClassLoader().getResource(cacheConfigFile);
		if (url == null)
			url = this.getClass().getResource(cacheConfigFile);
		return url;
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			if (cacheManager != null) {
				cacheManager.shutdown();
			}
		} catch (Exception e) {
			logger.error("error while doing manual GC");
		}
		super.finalize();
	}
}
