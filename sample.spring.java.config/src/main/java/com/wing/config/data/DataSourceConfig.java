package com.wing.config.data;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:properties/datasource.properties")
public class DataSourceConfig {
	
	private String CONNECTION_URL;
	private String DB_USER_NAME;
	private String DB_USER_PASSWORD;
	private String DRIVER_CLASS_NAME;

	@Autowired
	public void setProperties(Environment env) {
		this.CONNECTION_URL = env.getProperty("datasource.URL");
		this.DB_USER_NAME = env.getProperty("datasource.USERNAME");
		this.DB_USER_PASSWORD = env.getProperty("datasource.PASSWORD");
		this.DRIVER_CLASS_NAME = env.getProperty("datasource.DRIVER_NAME");
	}

	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(this.DRIVER_CLASS_NAME);
		dataSource.setUrl(this.CONNECTION_URL);
		dataSource.setUsername(this.DB_USER_NAME);
		dataSource.setPassword(this.DB_USER_PASSWORD);
		return dataSource; 
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory((SessionFactory) localSessionFactoryBean());
		return transactionManager;
	}
	
	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		return sessionFactoryBean;
	}

}
