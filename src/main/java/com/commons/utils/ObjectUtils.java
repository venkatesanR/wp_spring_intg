package com.commons.utils;

import java.io.File;
import java.lang.reflect.Field;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.springdev.dbutils.DBConnectionUtility;

public class ObjectUtils {
	private static final transient Logger logger = Logger.getLogger(DBConnectionUtility.class);

	public static String printBeanProperites(Object object) {
		if (object == null)
			return "";
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
				// handle the Objects present as array here
				Object[] objects = (Object[]) fieldObjects;
				for (int j = 0; j < objects.length; j++)
					buffer.append("\t\t\t\t").append(objects[j]).append("\n");
			}
			return buffer.toString();
		} catch (IllegalAccessException iae) {
			buffer.append("\n").append("Error Accessing variable " + iae);
			return buffer.toString();
		}
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
}
