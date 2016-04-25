package com.springdev.intg;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.commons.utils.LocalStorageOfObject;

import net.spy.memcached.MemcachedClient;

public class MemCacheClientTest extends LocalStorageOfObject {
	private static final Integer TTL = 10000;
	private static String inetAdrs = "172.18.8.143";
	private static String inetAdrs2 = "10.10.10.161";
	private static String remoteCacheKeys = "3_1_6__20151101_20151130_6_102," + "3_1_7__20151101_20151130_6_102,"
			+ "2_1_6__20151101_20151130_6_102," + "2_2_6__20151101_20151130_6_102," + "1_1_6__20151101_20151130_6_102,"
			+ "1_2_6__20151101_20151130_6_271_0";
	private static final String keyafix = "95_225";
	private static final String datRangId = "20151201_20151231";
	//YFP:Report:3_1233
	private static LocalStorageOfObject localStorageOfObject = new LocalStorageOfObject();

	public static void main(String[] args) {
		MemcachedClient local = null;
		MemcachedClient remote = null;
		try {
			local = new MemcachedClient(new InetSocketAddress(inetAdrs, 11211));
			remote = new MemcachedClient(new InetSocketAddress(inetAdrs2, 11211));
			//loadInLocal(local, remote.get("YFP:Report:1_1_7_20160101_20160131_2_11"),
					//"YFP:Report:1_1_6_20151221_20160120_95_305");
			//printBeanProperites(local.get("YFP:Report:1_1_6_20151221_20160120_95_305"));
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
			printBeanProperites(data);
		}
	}

	public static void loadInLocal(MemcachedClient local, Object preparedObject, String key) throws IOException {
		if (preparedObject == null) {
			return;
		}
		local.delete(key);
		local.add(key, TTL, preparedObject);
	}

	public static void setRemoteToLocalSingle(MemcachedClient remote, MemcachedClient local, String remoteKey,
			String localKey) throws IOException {
		Object data = remote.get(remoteKey);
		loadInLocal(local, data, localKey);
	}

	public static void printAllRemoteBeans(MemcachedClient remote, MemcachedClient local) throws IOException {
		String[] remoteKeys = remoteCacheKeys.split(",");
		String remoteKey;
		for (int index = 0; index < remoteKeys.length; index++) {
			remoteKey = "YFP:Report:" + remoteKeys[index];
			printBeanProperites(localStorageOfObject.getLocalObjectUsingKey(remoteKey));
		}
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
			isInLocal = localStorageOfObject.getLocalObjectUsingKey(remoteKey) != null;
			data = isInLocal ? localStorageOfObject.getLocalObjectUsingKey(remoteKey) : remote.get(remoteKey);
			System.out.println("KEY:" + localKey);
			printBeanProperites(data);
			System.out.println("KEY:" + remoteKey);
			printBeanProperites(localStorageOfObject.getLocalObjectUsingKey(remoteKey));
			loadInLocal(local, data, localKey);
			if (!isInLocal) {
				localStorageOfObject.storeObjectInLocal(data, remoteKey);
			}
		}
	}
}