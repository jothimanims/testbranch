package com.ffss.datax.shiro.config

import javax.servlet.Filter
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;


@Configuration
class ShiroConfiguration {

	@Bean
	public ShiroFilterFactoryBean shiroFilter(){
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		Map<String, String> filterMap = new HashMap<String, String>();
		filterMap.put("/**","authcBasic")
		shiroFilter.setSecurityManager(securityManager());
		shiroFilter.setFilterChainDefinitionMap(filterMap);
		return shiroFilter;
	}
	
	@Bean
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		securityManager.setRealm(realm());
		//securityManager.setSessionManager(sessionManager());
		return securityManager;
	}
	
	@Bean(name = "realm")
	@DependsOn("lifecycleBeanPostProcessor")
	public MySqlDbAlarm realm() {
		final MySqlDbAlarm realm = new MySqlDbAlarm();
		realm.setCredentialsMatcher(credentialsMatcher());
		return realm;
	}
	
	@Bean(name = "credentialsMatcher")
	public PasswordMatcher credentialsMatcher() {
		final PasswordMatcher credentialsMatcher = new PasswordMatcher();
		credentialsMatcher.setPasswordService(passwordService());
		return credentialsMatcher;
	}

	@Bean(name = "passwordService")
	public DefaultPasswordService passwordService() {
		return new DefaultPasswordService();
	}
	
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
}
