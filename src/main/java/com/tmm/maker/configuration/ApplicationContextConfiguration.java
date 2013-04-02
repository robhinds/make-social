package com.tmm.maker.configuration;

import java.beans.PropertyVetoException;

import javax.persistence.EntityManagerFactory;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.dbcp.BasicDataSource;
import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.cloudfoundry.runtime.env.MysqlServiceInfo;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ, proxyTargetClass = true)
@ComponentScan("com.tmm.maker")
@PropertySource("classpath:META-INF/spring/database.properties")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ApplicationContextConfiguration {

	@Autowired
	Environment env;

	@Bean
	public PlatformTransactionManager txManager() throws PropertyVetoException {
		JpaTransactionManager bean = new JpaTransactionManager();
		bean.setEntityManagerFactory(entityManagerFactory());
		return bean;
	}

	@Bean
	public CachingConfiguration cachingConfiguration() {
		return new CachingConfiguration();
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() throws PropertyVetoException {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPackagesToScan("com.tmm.maker.domain", "com.tmm.maker.security");
		emf.setPersistenceProvider(new HibernatePersistence());
		emf.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "update");
		emf.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		emf.afterPropertiesSet();
		emf.setJpaDialect(new HibernateJpaDialect());
		return emf.getObject();
	}

	@Bean
	public BasicDataSource dataSource() throws PropertyVetoException {
		//CloudFoundry config
		final CloudEnvironment cloudEnvironment = new CloudEnvironment();
        final MysqlServiceInfo serviceInfo = cloudEnvironment.getServiceInfo("mysql", MysqlServiceInfo.class);
		
		BasicDataSource bean = new BasicDataSource();
		bean.setDriverClassName("com.mysql.jdbc.Driver");
		bean.setUrl(serviceInfo.getUrl());
		bean.setUsername(serviceInfo.getUserName());
		bean.setPassword(serviceInfo.getPassword());
		return bean;
	}

	@Bean
	public ValidatorFactory validatorFactory() {
		ValidatorFactory bean = Validation.buildDefaultValidatorFactory();
		return bean;
	}

	@Bean
	public Validator validator() {
		Validator bean = validatorFactory().getValidator();
		return bean;
	}
}
