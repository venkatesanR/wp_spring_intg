package com.commons.utils;

import java.util.List;

public class ExcelInfo {
	private List<String> headerDetails;
	private List<Object> rowDetails;
	private String fileName;
	private List<String> includeRowData;

	public List<String> getHeaderDetails() {
		return headerDetails;
	}

	public void setHeaderDetails(List<String> headerDetails) {
		this.headerDetails = headerDetails;
	}

	public List<Object> getRowDetails() {
		return rowDetails;
	}

	public void setRowDetails(List<Object> rowDetails) {
		this.rowDetails = rowDetails;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<String> getIncludeRowData() {
		return includeRowData;
	}

	public void setIncludeRowData(List<String> includeRowData) {
		this.includeRowData = includeRowData;
	}

}
