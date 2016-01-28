package com.springdev.cfc;

import java.util.Collection;

import com.commons.utils.KeyValuePair;

@SuppressWarnings("rawtypes")
public interface SqlInterPolator {
	public String getSqlKey();

	public void setSqlKey(String sqlKey);

	Collection<KeyValuePair> getInputParams();

	Collection<KeyValuePair> getOuputParams();

	void setOuputParams(Collection<KeyValuePair> keyValuePair);

	void setInputParams(Collection<KeyValuePair> keyValuePair);

	void setSqlQuery(String sqlQuery);

	String getSqlQuery();
}
