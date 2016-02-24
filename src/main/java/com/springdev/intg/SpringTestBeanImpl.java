package com.springdev.intg;

import org.apache.log4j.Logger;

import com.cacheinteg.managers.CacheAccessUtils;
import com.commons.utils.ObjectUtils;
import com.springdev.ldap.CustomLdapUtil;

public class SpringTestBeanImpl extends ObjectUtils implements SpringTestBean {
	private static final transient Logger logger = Logger
			.getLogger(SpringTestBeanImpl.class);
	private CacheAccessUtils cacheUtils;

	public CacheAccessUtils getCacheUtils() {
		return cacheUtils;
	}

	public void setCacheUtils(CacheAccessUtils cacheUtils) {
		this.cacheUtils = cacheUtils;
	}

	@Override
	public void testCacheImpl() {
		logger.info("test Cache started");
		// #1-Eh Cache Test
		logger.info("test Cache finished");
	}

	@Override
	public void testORMImpl() {

	}

	@Override
	public void testDAOImpl() {

	}

	@Override
	public void testMSGImpl() {

	}

	@Override
	public void testAOPImpl() {

	}

	@SuppressWarnings({ "rawtypes" })
	public void testAllBeans(BeanHelper helper) {
		String[] beanDefinations = helper.appContextRef
				.getBeanDefinitionNames();
		if (beanDefinations != null && beanDefinations.length > 0) {
			for (String beanType : beanDefinations) {
				printBeanProperites(helper.getBean(beanType));
			}
		}
	}

	@Override
	public boolean testCredentials(BeanHelper helper, String userName, String password) {
		CustomLdapUtil ldapUtl = (CustomLdapUtil) helper.getBean("ldapUtils");
		return ldapUtl.authenticate(userName, password);
	}
}
