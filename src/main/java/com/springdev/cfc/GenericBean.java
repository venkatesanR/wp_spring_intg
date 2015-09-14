package com.springdev.cfc;

public class GenericBean {
	private String propertyName;
	private String propertyValue;

	private NestedBean nestedRef;

	public GenericBean() {

	}

	public GenericBean(String setPropertyName, String setPropertyVal) {
		propertyName = setPropertyName;
		propertyValue = setPropertyVal;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	public NestedBean getNestedRef() {
		return nestedRef;
	}

	public void setNestedRef(NestedBean nestedRef) {
		this.nestedRef = nestedRef;
	}

	public static class NestedBean {
		public NestedBean() {
			System.out.println("Generice Nested Bean Initialized");
		}
	}
}
