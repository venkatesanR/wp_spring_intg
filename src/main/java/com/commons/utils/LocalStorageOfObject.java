package com.commons.utils;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class LocalStorageOfObject extends ObjectUtils {
	private static final transient String LOCAL_STORAGE = "/home/YUME.COM/vrengasamy/Desktop/YuMe-WorkPlace/wp_spring_intg/LOCAL_STORAGE/app_data_store.ser";
	// private static final transient String SESSS_LOCAL_STORAGE =
	// "/home/yumecorp/Desktop/YuMe-WorkPlace/wp_spring_intg/LOCAL_STORAGE/app_data_store.ser";
	FileOutputStream fos=null;
	FileInputStream fis = null;
	public LocalStorageOfObject() {
		try {
			fos =  new FileOutputStream(LOCAL_STORAGE, true);
			fis = new FileInputStream(LOCAL_STORAGE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void storeObjectInLocal(Object object, String cacheKey) {
		if (object == null) {
			return;
		}
		
		ObjectOutputStream oos = null;
		Map<String, Object> result = null;
		try {
			oos = new ObjectOutputStream(fos);
			result = retriveAllObjectLocal();
			if (result != null && result.size() > 0) {
				result.put(cacheKey, object);
			} else {
				result = new HashMap<String, Object>();
				result.put(cacheKey, object);
			}
			oos.writeObject(result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> retriveAllObjectLocal() {
		
		ObjectInputStream ois = null;
		Map<String, Object> result = null;
		try {
			ois = new ObjectInputStream(fis);
			//printBeanProperites( ois.readObject());
			result = (Map<String, Object>) ois.readObject();
		} catch (EOFException eof) {
			System.out.println("End Of Stream");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Stream issue");
			return result;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void printAllBeans() {
		Map<String, Object> result = retriveAllObjectLocal();
		if (result != null && result.size() > 0) {
			for (Map.Entry<String, Object> entrySet : result.entrySet()) {
				printBeanProperites(result.get(entrySet.getKey()));
			}
		}
	}

	public Object getLocalObjectUsingKey(String cacheKey) {
		Map<String, Object> result = retriveAllObjectLocal();
		if (result != null && result.size() > 0) {
			return (Object) result.get(cacheKey);
		}
		return null;
	}
}
