package com.springdev.dbutils;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.commons.utils.KeyValuePair;
import com.springdev.cfc.SqlInterPolator;

@SuppressWarnings("rawtypes")

@XmlRootElement
public class SQLMapper implements SqlInterPolator {
	private String sqlKey;
	private String sqlQuery;
	private Collection<KeyValuePair> inputParams;
	private Collection<KeyValuePair> ouputParams;

	public String getSqlKey() {
		return sqlKey;
	}

	@XmlElement
	public void setSqlKey(String sqlKey) {
		this.sqlKey = sqlKey;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}

	@XmlElement
	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public Collection<KeyValuePair> getInputParams() {
		return inputParams;
	}
	
	@XmlElement
	public void setInputParams(Collection<KeyValuePair> inputParams) {
		this.inputParams = inputParams;
	}

	public Collection<KeyValuePair> getOuputParams() {
		return ouputParams;
	}
	
	@XmlElement
	public void setOuputParams(Collection<KeyValuePair> ouputParams) {
		this.ouputParams = ouputParams;
	}

}
