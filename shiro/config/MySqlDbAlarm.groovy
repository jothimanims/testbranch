package com.ffss.datax.shiro.config

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ffss.datax.shiro.domain.UserAccount;
import com.ffss.datax.shiro.repository.UserRepository;

/**
 * @author SVIGNESHWARAN
 *
 */
@Component
class MySqlDbAlarm extends AuthorizingRealm {
	
	@Autowired
	UserRepository userRepository

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			final AuthenticationToken token)
	throws AuthenticationException {
		final UsernamePasswordToken credentials = (UsernamePasswordToken) token;
		final String username = credentials.getUsername();
		if(username == null) {
			UserAccount user = userRepository.findUser(username);
			println "Username :*******************"+ user.firstName
		}
		
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			final PrincipalCollection principals) {
			
			final UsernamePasswordToken credentials = (UsernamePasswordToken) token;
			final String username = credentials.getUsername();
			
			println " PrincipalCollection Username :*********"+ username
	}
}
