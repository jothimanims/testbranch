package com.ffss.datax.shiro.config

import javax.servlet.Filter
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import com.ffss.datax.shiro.repository.UserAccountRepository;


@Configuration
class ShiroConfiguration {
	
	@Bean
	public ShiroFilterFactoryBean shiroFilter(){
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		Map<String, String> filterChain = new HashMap<String, String>();
		shiroFilter.setSecurityManager(securityManager());
		filterChain.put("/**","anon")
		shiroFilter.setFilterChainDefinitionMap(filterChain);
		Map<String, Filter> filters = new HashMap<>();
		shiroFilter.setFilters(filters);

		return shiroFilter;
	}
	
	/*@Bean
	public ShiroFilterFactoryBean shiroFilter(){
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		Map<String, String> filterChain = new HashMap<String, String>();
		filterChain.put("/accessUserDetails", "authc,roles[admin]");
		filterChain.put("/login", "authc");
        filterChain.put("/logout", "logout");
		shiroFilter.setLoginUrl("/login");
		shiroFilter.setFilterChainDefinitionMap(filterChain);
		shiroFilter.setSecurityManager(securityManager());
		Map<String, Filter> filters = new HashMap<>();
		filters.put("anon", new AnonymousFilter());
        filters.put("authc", new FormAuthenticationFilter());
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/login?logout");
        filters.put("logout", logoutFilter);
        filters.put("roles", new RolesAuthorizationFilter());
        filters.put("user", new UserFilter());
		shiroFilter.setFilters(filters);

		return shiroFilter;
	}*/
	
	@Bean
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		securityManager.setRealm(realm());
		return securityManager;
	}
	
	@Bean(name = "realm")
	@DependsOn("lifecycleBeanPostProcessor")
	public MySqlDbRealarm realm() {
		final MySqlDbRealarm realm = new MySqlDbRealarm();
		realm.setCredentialsMatcher(credentialsMatcher());
		realm.setDataSource(dataSource())
		realm.init();
		return realm;
	}
	
	@Bean(name="dataSource")
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
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
