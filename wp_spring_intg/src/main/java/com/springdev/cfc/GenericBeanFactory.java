package com.springdev.cfc;

public class GenericBeanFactory {
	private static DependentBean clientService = new DependentBean();

	private GenericBeanFactory() {

	}

	public DependentBean createClientServiceInstance() {
		return clientService;
	}
}
