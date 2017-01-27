package ro.fortech.caveatEmptor.integration.repositories.users;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ro.fortech.caveatEmptor.exceptions.UserException;
import ro.fortech.caveatEmptor.integration.entities.User;
import ro.fortech.caveatEmptor.integration.queries.UserQueries;

@Repository
public class UserRepository {

	Logger logger = LoggerFactory.getLogger(UserRepository.class);

	@Autowired
	private SessionFactory sessionFactory;

	public User getUserByUsername(String username, String password) throws Exception {
		logger.info("<<<START>>> UserRepository.getUserByUsername with params username: " + username + ", password: ***********");

		User user = null;

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(UserQueries.GET_USER_BY_USERNAME).setParameter("username", username).setParameter("password", password);
			user = (User) query.uniqueResult();
		} catch (NoResultException e) {
			logger.info("No results found");
			throw new UserException("Username or password incorrect!");
		}
		tx.commit();
		session.flush();
		sessionFactory.close();

		logger.info("<<<END>>> UserRepository.getUserByUsername");

		return user;

	}

}
