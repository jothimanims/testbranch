package com.ffss.datax.shiro.config

import java.util.LinkedHashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ffss.datax.shiro.domain.Role
import com.ffss.datax.shiro.domain.UserAccount;
import com.ffss.datax.shiro.repository.UserAccountRepository;
import com.ffss.datax.shiro.domain.Permission;

/**
 * Authenticated & Authorization information will be fetched from the database.
 * 
 */
@Component
class MySqlDbRealarm extends JdbcRealm {
	
	@Autowired
	UserAccountRepository userRepository
	
	@Autowired
	DefaultPasswordService passwordService
	

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			final AuthenticationToken token)
	throws AuthenticationException {
		println "doGetAuthenticationInfo :*******************"
		AuthenticationInfo authenticationInfo = null;
		final UsernamePasswordToken credentials = (UsernamePasswordToken) token;
		final String username = credentials.getUsername();
		UserAccount user = userRepository.findUser(username);
		
		if(user == null) {
			throw new UnknownAccountException("Account does not exist");
		}
		
		authenticationInfo =  new SimpleAuthenticationInfo(user.getUserName(), passwordService.encryptPassword(user.getPassword()),getName())
		return authenticationInfo;
		
	}

	@Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            final PrincipalCollection principals) {
		println "Authorization Parts Starts*******************"
        final String username = (String) principals.getPrimaryPrincipal();
		println "Authorized username : "+ username
        final UserAccount user = userRepository.findUser(username);
        if (user == null) {
            throw new UnknownAccountException("Account does not exist");
        }
        final int totalRoles = user.getRoles().size();
        final Set<String> roleNames = new LinkedHashSet<>(totalRoles);
		final Set<Object> permissionNames = new LinkedHashSet<>();
        if (totalRoles > 0) {
            for (Role role : user.getRoles()) {
                roleNames.add(role.getRoleName());
				println "Rolenames :" + roleNames
				for (Permission permission : role.getPermissions()) {
					permissionNames.add(permission.getPermissionName());
				}
            }
        }
        final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames)
		info.setStringPermissions(permissionNames)
		println("Permission name : *********" + permissionNames)
        return info;
    }
}
