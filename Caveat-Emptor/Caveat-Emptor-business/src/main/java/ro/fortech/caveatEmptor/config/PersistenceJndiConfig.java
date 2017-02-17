package ro.fortech.caveatEmptor.config;

import java.io.IOException;
import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@ComponentScan("ro.fortech.caveatEmptor.business.services")
// @EnableJpaRepositories("ro.fortech.caveatEmptor.integration.repositories")
public class PersistenceJndiConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws NamingException, IOException {
	LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
	sessionFactoryBean.setPackagesToScan("ro.fortech.caveatEmptor.integration.entities");
	sessionFactoryBean.setDataSource(dataSource());
	sessionFactoryBean.setHibernateProperties(getHibernateProperties());
	sessionFactoryBean.afterPropertiesSet();
	return sessionFactoryBean;
    }

    @Bean
    public DataSource dataSource() throws NamingException {
	return (DataSource) new JndiTemplate().lookup("java:/cae-mSQL-DS");
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
	HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	transactionManager.setSessionFactory(sessionFactory);
	return transactionManager;
    }

    private Properties getHibernateProperties() {
	Properties props = new Properties();
	// props.put("hibernate.hbm2ddl.auto", "create");
	props.put("hibernate.show_sql", "true");
	props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	props.put("hibernate.current_session_context_class", "thread");
	return props;
    }

}
