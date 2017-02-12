package com.ffss.datax.shiro.config

import javax.sql.DataSource

import liquibase.integration.spring.SpringLiquibase

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LiquibaseConfiguration {
	
	@Bean
	public SpringLiquibase liquibase(DataSource dataSource) {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setDataSource(dataSource);
		liquibase.setChangeLog("classpath:config/liquibase/master.xml");
		liquibase.setShouldRun(true);
		return liquibase;
	}

}
