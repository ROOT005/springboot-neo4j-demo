package com.yiqian.personrel.config;


import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableNeo4jRepositories(
		basePackages="com.yiqian.personrel.neo4j",
		transactionManagerRef="neo4jTransactionManager")
@EnableJpaRepositories(
		basePackages="com.yiqian.personrel.mysql",
		transactionManagerRef="mysqlTransactionManager")
@EnableTransactionManagement
public class DataSourceConfig extends Neo4jDataAutoConfiguration{	
	
	@Bean(name = "neo4jDataSource")
    @Qualifier("neo4jDataSource")
	public org.neo4j.ogm.config.Configuration getConfiguration(){
		org.neo4j.ogm.config.Configuration config = 
				new org.neo4j.ogm.config.Configuration.Builder()
					.uri("http://admin:000000@localhost:7474")
					.build();
		return config;
	}
	
	@Bean(name = "neo4jDataSource")
    @Qualifier("neo4jDataSource")
	public SessionFactory getSessionFactory() {
		return new SessionFactory(getConfiguration(),"com.yiqian.personrel.neo4j");
	}
		
	@Bean(name="mysqlDataSource")
	@Qualifier("mysqlDataSource")
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder
				.create()
				.url("jdbc:mysql://localhost:3306/rel")
				.driverClassName("com.mysql.jdbc.Driver")
				.build();
	}
	
	
	@Bean(name="mysqlDataSource")
	@Qualifier("mysqlDataSource")
	@Autowired
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setPackagesToScan("com.yiqian.personrel.mysql");
		entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
		
		Map<String, String> jpaProperties = new HashMap<>();
		
		jpaProperties.put("hibernate.connection.charSet", "UTF-8");
	    jpaProperties.put("spring.jpa.hibernate.ddl-auto", "none");
	    jpaProperties.put("spring.jpa.hibernate.naming-strategy", "org.springframework.boot.orm.jpa.SpringNamingStrategy");
	    jpaProperties.put("hibernate.bytecode.provider", "javassist");
	    jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
	    jpaProperties.put("hibernate.hbm2ddl.auto", "none");
	    jpaProperties.put("hibernate.order_inserts", "true");
	    jpaProperties.put("hibernate.jdbc.batch_size", "50");
	    
	    entityManagerFactory.setJpaPropertyMap(jpaProperties);
	    entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());
		
		return entityManagerFactory;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Autowired
	@Bean(name="neo4jTransactionManager")
	public Neo4jTransactionManager neo4jTransactionManager(SessionFactory sessionFactory) {
		return new Neo4jTransactionManager(sessionFactory);
	}
	
	
	@Autowired
	@Bean(name="mysqlTransactionManager")
	public JpaTransactionManager mysqlTransactionManager(
			LocalContainerEntityManagerFactoryBean entityManagerFactory) throws Exception{
		return new JpaTransactionManager(entityManagerFactory.getObject());
	}
	
	@Autowired
	@Bean(name="transactionManager")
	public PlatformTransactionManager transactionManager(
			Neo4jTransactionManager neo4jTransactionManager,
			JpaTransactionManager mysqlTransactionManager) {
		return new ChainedTransactionManager(
				mysqlTransactionManager,
				neo4jTransactionManager);
	}
}

