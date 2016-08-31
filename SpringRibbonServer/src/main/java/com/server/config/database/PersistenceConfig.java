package com.server.config.database;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.server.model.EntityJpaClass;
import com.server.repository.RepositoryPackage;
import com.server.util.Catalago;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = { RepositoryPackage.class })
public class PersistenceConfig {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(Catalago.DB_DRIVER);
		dataSource.setUrl(Catalago.DB_URL);
		dataSource.setUsername(Catalago.DB_USERNAME);
		dataSource.setPassword(Catalago.DB_PASSWORD);
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		EntityManagerFactory factory = entityManagerFactory().getObject();
		return new JpaTransactionManager(factory);
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	@Autowired
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(Boolean.TRUE);
		vendorAdapter.setShowSql(Boolean.TRUE);

		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(EntityJpaClass.class.getPackage().getName());

		Properties jpaProperties = new Properties();
		jpaProperties.put(Catalago.DB_DDL, Catalago.DB_DDL_VALUE);
		jpaProperties.put(Catalago.DB_DIALECT, Catalago.DB_DIALECT_VALUE);
		jpaProperties.put(Catalago.DB_SHOW_SQL, Catalago.DB_SHOW_SQL_VALUE);
		factory.setJpaProperties(jpaProperties);

		return factory;
	}

}
