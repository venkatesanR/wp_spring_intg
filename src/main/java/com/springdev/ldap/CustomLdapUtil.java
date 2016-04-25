package com.springdev.ldap;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;

import com.commons.utils.ObjectUtils;
import com.springdev.cfc.LdapResponse;
import com.springdev.cfc.User;

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
@SuppressWarnings("rawtypes")
public class CustomLdapUtil implements LdapActions {
	private static final Logger logger = Logger.getLogger(CustomLdapUtil.class);
	private ActiveDirectoryLdapAuthenticationProvider yumeAuthProvider;
	private static final String default_mail_appender = "@yume.com";
	private static final String cust_name = "cn";
	
	@Value("${yume.ldap.domain}")
	private String domainName;

	public void setYumeAuthProvider(ActiveDirectoryLdapAuthenticationProvider yumeAuthProvider) {
		this.yumeAuthProvider = yumeAuthProvider;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public LdapResponse authAndGetUserInfo(String domainName, String password) {
		Authentication authentication = null;
		Authentication authResult = null;
		LdapResponse response = null;
		try {
			authentication = new UsernamePasswordAuthenticationToken(domainName, password);
			if (yumeAuthProvider != null) {
				yumeAuthProvider = preProcessAuth(yumeAuthProvider);
			}
			authResult = yumeAuthProvider.authenticate(authentication);
			response = ldapResponseTranslator(authResult);

		} catch (AuthenticationException authEx) {
			logger.error("Authentication failed because of : " + authEx.getMessage());
			return prepareErrorLdap(authEx.getMessage());
		} catch (Exception ex) {
			logger.error("Authentication failed because of : ", ex);
			return prepareErrorLdap(ex.getMessage());
		}
		return response;
	}

	private ActiveDirectoryLdapAuthenticationProvider preProcessAuth(
			ActiveDirectoryLdapAuthenticationProvider authProvider) {
		authProvider.setConvertSubErrorCodesToExceptions(true);
		authProvider.setUseAuthenticationRequestCredentials(true);
		authProvider.setUseAuthenticationRequestCredentials(true);
		return authProvider;
	}

	private LdapResponse prepareErrorLdap(String errorMsg) {
		LdapResponse response = new LdapResponse();
		response.setAuthenticated(false);
		response.setErrorText(errorMsg);
		return response;
	}

	@SuppressWarnings("unchecked")
	private LdapResponse ldapResponseTranslator(Authentication authResult) {
		LdapResponse response = new LdapResponse();
		if (authResult != null) {
			LdapUserDetailsImpl userDeatils = (LdapUserDetailsImpl) authResult.getPrincipal();
			if (userDeatils != null) {
				User user = new User();
				String dnName = userDeatils.getDn();
				if (dnName != null) {
					Properties prop = ObjectUtils.getPropFromString(dnName.split(","));
					user.setFullName(prop.getProperty(cust_name));
				}
				if (userDeatils.getUsername() != null && userDeatils.getUsername().contains("@")) {
					user.setFullName(userDeatils.getUsername());
				} else {
					user.setFullName(userDeatils.getUsername()
							.concat(domainName != null ? "@".concat(domainName) : default_mail_appender));
				}
				response.setEntity(user);
				response.setAccountNonExpired(userDeatils.isAccountNonExpired());
				response.setAccountNonLocked(userDeatils.isAccountNonLocked());
			}
			response.setAuthenticated(authResult.isAuthenticated());
		}
		return response;
	}

}
