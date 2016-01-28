package com.springdev.intg;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextLoader {
	protected ApplicationContext appContextRef = null;

	public ApplicationContextLoader() {
		appContextRef = new ClassPathXmlApplicationContext("classpath:main_springroot_applicationcontext.xml");
	}
}
