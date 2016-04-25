package com.springdev.ldap;

import com.springdev.cfc.LdapResponse;

@SuppressWarnings("rawtypes")
public interface LdapActions {
	LdapResponse authAndGetUserInfo(String domainName, String password);
}