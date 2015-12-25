package com.springdev.dbutils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.commons.utils.KeyValuePair;
import com.commons.utils.ObjectUtils;
import com.commons.utils.SQLMapper;

public class DBDataUtils extends ObjectUtils{
	private static final transient Logger logger = Logger.getLogger(DBConnectionUtility.class);

	public static Map<String, SQLMapper> prepareXmlToDbObject(String fileName) {
		DocumentBuilder dBuilder;
		Map<String, SQLMapper> sqlObjList = null;
		try {
			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("sql-info");
			sqlObjList = getConvertedSqlInfo(nList);
		} catch (ParserConfigurationException e) {
			logger.error("Error while parsing xml::", e);
			e.printStackTrace();
		} catch (SAXException e) {
			logger.error("Error while parsing xml SAX::", e);
		} catch (IOException e) {
			logger.error("IO exception::", e);
		}
		return sqlObjList;
	}

	private static Map<String, SQLMapper> getConvertedSqlInfo(NodeList nodeList) {
		Map<String, SQLMapper> sqlMapper = new HashMap<String, SQLMapper>(0);
		
		return sqlMapper;
	}

	private void finiteLookupMachine(NodeList nodeList) {
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				if ("sql-info".equals(tempNode.getNodeName())) {
					mapperInfo.setSqlKey(getNodeAttrsValueByName(tempNode, "query-key"));
					if (tempNode.hasChildNodes() && tempNode.getChildNodes().getLength() ==3) {
						
					}
				}
				if ("sql-query".equals(tempNode.getNodeName())) {
					mapperInfo.setSqlQuery(getNodeValueByName(tempNode, "sql-query"));
				}
				if ("set-properties".equals(tempNode.getNodeName())) {
					if (tempNode.hasChildNodes()) {
						mapperInfo.setInputParams(getAllChildNodesKVProps(tempNode.getChildNodes()));
					}
				}
				if ("get-properties".equals(tempNode.getNodeName())) {
					if (tempNode.hasChildNodes()) {
						mapperInfo.setOuputParams(getAllChildNodesKVProps(tempNode.getChildNodes()));
					}
				}
				sqlMapper.put(mapperInfo.getSqlKey(), mapperInfo);
			}
		}
	}
	@SuppressWarnings("rawtypes")
	private static Collection<KeyValuePair> getAllChildNodesKVProps(NodeList nodeList) {
		Collection<KeyValuePair> keyVals = new ArrayList<KeyValuePair>();
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			keyVals.add(getCollKVFromNode(tempNode));
		}
		return keyVals;
	}

	private static String getNodeValueByName(Node tempNode, String nodeName) {
		String nodeValue = null;
		if (tempNode.getNodeName() != null && tempNode.getNodeName().equals(nodeName)) {
			nodeValue = tempNode.getNodeValue();
		}
		return nodeValue;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static KeyValuePair getCollKVFromNode(Node tempNode) {
		KeyValuePair keyVal = null;
		if (tempNode.hasAttributes() && tempNode.getAttributes().getLength() == 2) {
			keyVal = new KeyValuePair();
			keyVal.setKey(tempNode.getAttributes().item(0).getNodeValue());
			keyVal.setKey(tempNode.getAttributes().item(0).getNodeValue());
		}
		return keyVal;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static String getNodeAttrsValueByName(Node tempNode, String attrsName) {
		String attrValue = null;
		if (tempNode.getNodeName() != null) {
			if (tempNode.hasAttributes() && tempNode.getAttributes().getLength() == 1) {
				if (attrsName.equals(tempNode.getAttributes().item(0).getNodeName())) {
					attrValue = tempNode.getAttributes().item(0).getNodeValue();
				}
			}
		}
		return attrValue;
	}
}
