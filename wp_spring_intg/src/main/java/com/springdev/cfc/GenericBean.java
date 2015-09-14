package com.springdev.cfc;

public class GenericBean {
	private String propertyName;
	private NestedBean nestedRef;

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
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
