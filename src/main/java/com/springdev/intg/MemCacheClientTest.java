package com.springdev.intg;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.commons.utils.LocalStorageOfObject;

import net.spy.memcached.MemcachedClient;

public class MemCacheClientTest extends LocalStorageOfObject {
	// 24 * 60 * 60 * 365
	private static final Integer TTL = 10000;
	private static String inetAdrs = "172.18.8.143";
	private static String inetAdrs2 = "10.10.37.120";
	private static String remoteCacheKeys = "3_1_6_20151025_20151123_6_102,3_1_7_20151101_20151130_6_102,2_1_6_20151025_20151123_6_102,2_2_6_20151025_20151123_6_102,1_1_6_20151025_20151123_6_102,1_2_6_20151025_20151123_6_102_0";
	private static final String keyafix = "155_275";
	private static final String datRangId = "20151025_20151124";

	public static void main(String[] args) {
		MemcachedClient local = null;
		MemcachedClient remote = null;
		try {
			local = new MemcachedClient(new InetSocketAddress(inetAdrs, 11211));
			remote = new MemcachedClient(new InetSocketAddress(inetAdrs2, 11211));
			prepareAndLoadDataFromRemote(remote, local);
			// printAllBeans();
			printLocalCachedData(local);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			local.shutdown();
			remote.shutdown();
		}

	}

	public static String prepareLocalKey(String prefix, String zeroPadding) {
		String key = prefix + "_" + datRangId + "_" + keyafix;
		if (zeroPadding != null) {
			return key = key + "_" + zeroPadding;
		}
		return key;
	}

	public static void printLocalCachedData(MemcachedClient local) throws IOException {
		String[] remoteKeys = remoteCacheKeys.split(",");
		String localKey;
		Object data = null;
		for (int index = 0; index < remoteKeys.length; index++) {
			localKey = "YFP:Report:" + prepareLocalKey(remoteKeys[index].substring(0, 5), null);
			data = local.get(localKey);
			System.out.println("KEY:" + localKey + "  Bean::" + printBeanProperites(data));
		}
	}

	public static void loadInLocal(MemcachedClient local, Object preparedObject, String key) throws IOException {
		if (preparedObject == null) {
			return;
		}
		local.delete(key);
		local.add(key, TTL, preparedObject);
	}

	public static void prepareAndLoadDataFromRemote(MemcachedClient remote, MemcachedClient local) throws IOException {
		String[] remoteKeys = remoteCacheKeys.split(",");
		String localKey;
		String remoteKey;
		boolean isInLocal = false;
		Object data = null;
		for (int index = 0; index < remoteKeys.length; index++) {
			remoteKey = "YFP:Report:" + remoteKeys[index];
			localKey = "YFP:Report:" + prepareLocalKey(remoteKeys[index].substring(0, 5),
					String.valueOf(remoteKey.charAt(remoteKey.length() - 1)).equals("0") ? "0" : null);
			isInLocal = LocalStorageOfObject.getLocalObjectUsingKey(remoteKey) != null;
			data = isInLocal ? LocalStorageOfObject.getLocalObjectUsingKey(remoteKey) : remote.get(remoteKey);
			System.out.println("KEY:" + localKey + "  Bean::" + printBeanProperites(data));
			loadInLocal(local, data, localKey);
			if (!isInLocal) {
				LocalStorageOfObject.storeObjectInLocal(data, remoteKey);
			}
		}
	}
}