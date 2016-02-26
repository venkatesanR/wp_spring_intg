package com.springdev.ldap;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.Name;
import javax.naming.ldap.LdapName;

import org.apache.log4j.Logger;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

import com.springdev.cfc.User;
import com.springdev.intg.TestSpringPoC;

/**
 * This class is responsible for producing all CRUD operations<br>
 * as well as authentication based on user name and password.<br>
 * <b>skeleton:</b><br>
 * <b>template1:</b>local ldif file which will have user information<br>
 * <b>template2:</b>remote ldap template ideally<outlook or any other
 * third-party ldap interface><br>
 * <b>usage:</b><br>
 * all authentication is validated against by checking their user name and
 * password<br>
 * and rest other crud utils handled based on user object information<br>
 * 
 * @author vrengasamy
 *
 */
public class CustomLdapUtil implements LdapActions {
	private static final Logger logger=Logger.getLogger(TestSpringPoC.class);
	private LdapTemplate ldapTemplate;
	private LdapTemplate ldapServerTemplate;
	private ActiveDirectoryLdapAuthenticationProvider yumeAuthProvider;
	private static final String object_class = "objectclass";

	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

	public void setLdapServerTemplate(LdapTemplate ldapServerTemplate) {
		this.ldapServerTemplate = ldapServerTemplate;
	}

	
	public void setYumeAuthProvider(ActiveDirectoryLdapAuthenticationProvider yumeAuthProvider) {
		this.yumeAuthProvider = yumeAuthProvider;
	}

	@Override
	public User get(String cnName) {
		return ldapTemplate.lookup(cnName, PERSON_CONTEXT_MAPPER);
	}

	@Override
	public List<User> getAll(boolean isFromServer) {
		List<User> userList = null;
		if (isFromServer) {
			userList = ldapServerTemplate.search(query(), PERSON_CONTEXT_MAPPER);
		} else {
			userList = ldapTemplate.search(query().where(object_class).is("Yume Users"), PERSON_CONTEXT_MAPPER);
		}
		return userList;
	}

	@Override
	public boolean authenticate(String userName, String credentials,boolean isServer) {
		boolean isValid = false;
		try {
			if(isServer) {
				isValid = ldapServerTemplate.authenticate(LdapUtils.emptyLdapName(), prepeareLoginFilter(userName).toString(),
						credentials);
			} else {
				isValid = ldapTemplate.authenticate(LdapUtils.emptyLdapName(), prepeareLoginFilter(userName).toString(),
						credentials);
			}

		} catch (Exception e) {
			isValid = false;
			return isValid;
		}
		return isValid;
	}

	private AndFilter prepeareLoginFilter(String userName) {
		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("sAMAccountName", userName));
		return filter;
	}

	@Override
	public void create(User person) {
		Name dn = buildDn(person);
		DirContextAdapter context = new DirContextAdapter(dn);
		mapToContext(person, context);
		ldapTemplate.bind(dn, context, null);
	}

	@Override
	public void update(User person) {
		Name dn = buildDn(person);
		DirContextAdapter context = (DirContextAdapter) ldapTemplate.lookup(dn);
		mapToContext(person, context);
		ldapTemplate.modifyAttributes(dn, context.getModificationItems());
	}

	@Override
	public void syncDataFromEnterpriseServer() {
		List<User> serverUserList = getAll(true);
		List<User> localUserList = getAll(false);
		if (serverUserList != null && localUserList != null && !serverUserList.isEmpty() && !localUserList.isEmpty()) {
			for (User user : serverUserList) {
				if (localUserList.contains(user)) {
					create(user);
				} else {
					update(user);
				}
			}
		}
	}

	private LdapName buildDn(User person) {
		return buildDn(person.getFullName());
	}

	private LdapName buildDn(String fullname) {
		return LdapNameBuilder.newInstance().add("cn", fullname).build();
	}

	private void mapToContext(User user, DirContextAdapter context) {
		context.setAttributeValues("objectclass", new String[] { "top", "person" });
		context.setAttributeValue("cn", user.getFullName());
		context.setAttributeValue("sn", user.getLastName());
	}

	/**
	 * user object translator
	 */
	private final static ContextMapper<User> PERSON_CONTEXT_MAPPER = new AbstractContextMapper<User>() {
		@Override
		public User doMapFromContext(DirContextOperations context) {
			User user = new User();
			user.setFullName(context.getStringAttribute("cn"));
			user.setLastName(context.getStringAttribute("sn"));
			return user;
		}
	};

	@Override
	public boolean yumeServerAuthentication(String domainName, String password) {
		boolean isValid = false;
		UsernamePasswordAuthenticationToken authentication = null;
		Authentication authResult = null;
		try {
			authentication = new UsernamePasswordAuthenticationToken(domainName, password);
			authResult = yumeAuthProvider.authenticate(authentication);
			isValid = (authResult != null && authResult.isAuthenticated());
		} catch (AuthenticationException authEx) {
			logger.error("Authentication failed because of :", authEx);
		} catch (Exception ex) {
			logger.error("Authentication failed because of :", ex);
		}
		return isValid;
	}

	/**
	 * use this callback service's if required after authentication
	 * 
	 * private final static AuthenticatedLdapEntryContextCallback authCallBack =
	 * new AuthenticatedLdapEntryContextCallback() { public void
	 * executeWithContext(DirContext ctx, LdapEntryIdentification
	 * ldapEntryIdentification) { try { // TODO do all necessary transformation
	 * } finally { if (ctx != null) { LdapUtils.closeContext(ctx); } } } };
	 */
}
