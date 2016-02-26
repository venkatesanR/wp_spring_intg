package com.springdev.ldap;

import java.util.List;

import com.springdev.cfc.User;

public interface LdapActions {
	List<User> getAll(boolean isFromServer);

	boolean authenticate(String userDn, String credentials,boolean isServer);
	
	boolean yumeServerAuthentication(String domainName, String password);

	public void syncDataFromEnterpriseServer();

	void update(User user);

	void create(User user);

	User get(String cnName);
}
