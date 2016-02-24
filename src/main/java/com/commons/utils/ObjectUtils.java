package com.commons.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.log4j.Logger;

public class ObjectUtils {
	private static final transient Logger logger = Logger.getLogger(ObjectUtils.class);

	public static String printBeanProperites(Object object) {
		if (object == null) {
			logger.error("\n Null data is not able to process") ;
		}
		StringBuffer buffer = new StringBuffer(object.getClass().getName() + "\n");
		try {
			Field[] fields = object.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				Object fieldObjects = fields[i].get(object);
				buffer.append("\t\t").append(field.getName()).append("\t:\t").append(fieldObjects).append("\n");
				if (!(fieldObjects instanceof Object[]))
					continue;
				Object[] objects = (Object[]) fieldObjects;
				for (int j = 0; j < objects.length; j++)
					buffer.append("\t\t\t\t").append(objects[j]).append("\n");
			}
		} catch (IllegalAccessException iae) {
			buffer.append("\n").append("Error Accessing variable " + iae);
			logger.error(buffer.toString());
		}
		return buffer.toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T convertXmlToObject(String fileLoc, Class clazz) {
		T typeInstance = null;
		try {
			File file = new File(fileLoc);
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			typeInstance = (T) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return typeInstance;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String ObjectToXml(Class clazz, Object object) {
		String xml = "";
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(object, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setProperties(Object object, Statement stmnt, Collection<KeyValuePair> properties) {
		if (object == null)
			return;
		DynaBean wrapper = new WrapDynaBean(object);
		if (properties != null && !properties.isEmpty()) {
			for (KeyValuePair prop : properties) {
				wrapper.set(String.valueOf(prop.getKey()), prop.getValue());
			}
		}
	}

	public <T> T getProperties(Class clazz, ResultSet resultSet,Map<String,Object> properties) {
		return null;
	}
	
	public static <T> T[] arrayCopy(T[] source, T[] dest) {
		if (source != null && dest != null && source.length <= dest.length) {
			int count = 0;
			for (T data : source) {
				dest[count] = data;
				count = count + 1;
			}
		}
		return dest;
	}
	
	public static String printArrayToString() {
		
	}
}
