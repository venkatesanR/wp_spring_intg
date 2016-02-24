package com.springdev.intg;

@SuppressWarnings("rawtypes")
public interface SpringTestBean {
	public abstract void testAllBeans(BeanHelper helper);

	public abstract void testCacheImpl();

	public abstract void testORMImpl();

	public abstract void testDAOImpl();

	public abstract void testMSGImpl();

	public abstract void testAOPImpl();
	
	public abstract boolean testCredentials(BeanHelper helper,String userName,String password);

}
