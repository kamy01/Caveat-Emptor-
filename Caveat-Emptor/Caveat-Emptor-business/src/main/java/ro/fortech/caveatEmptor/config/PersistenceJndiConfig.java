package ro.fortech.caveatEmptor.config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("ro.fortech.caveatEmptor.business.services")
@EnableJpaRepositories("ro.fortech.caveatEmptor.integration.repositories")
public class PersistenceJndiConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setPackagesToScan("ro.fortech.caveatEmptor.integration.entities");
		em.setDataSource(dataSource());
		em.setPersistenceProvider(new HibernatePersistenceProvider());
		em.setJpaProperties(getHibernateProperties());
		return em;
	}

	@Bean
	public DataSource dataSource() throws NamingException {
		return (DataSource) new JndiTemplate().lookup("java:/cae-mSQL-DS");
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory em) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(em);
		return transactionManager;
	}

	private Properties getHibernateProperties() {
		Properties props = new Properties();
		// props.put("hibernate.hbm2ddl.auto", "create");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		return props;
	}

}
