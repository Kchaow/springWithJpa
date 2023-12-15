package example;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import jakarta.persistence.EntityManagerFactory;

@Configuration
@Import(BasicDataSourceCfg.class)
@EnableTransactionManagement
@ComponentScan("example.service")
public class JpaConfig 
{
	private static Logger logger = LoggerFactory.getLogger(JpaConfig.class);
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	public PlatformTransactionManager transactionManager()
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter()
	{
		return new HibernateJpaVendorAdapter();
	}
	
	@Bean
	public Properties jpaProperties()
	{
		Properties jpaProps = new Properties();
		jpaProps.put(Environment.HBM2DDL_AUTO, "none");
		jpaProps.put(Environment.FORMAT_SQL, false);
		jpaProps.put(Environment.USE_SQL_COMMENTS, false);
		jpaProps.put(Environment.SHOW_SQL, false);
		return jpaProps;
	}
	
	@Bean
	public org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean entityManagerFactory()
	{
		LocalContainerEntityManagerFactoryBean factory =
				new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setJpaVendorAdapter(jpaVendorAdapter());
		factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		factory.setPackagesToScan("example.entities");
		factory.setJpaProperties(jpaProperties());
		return factory;
	}
}
