package ro.fortech.caveatEmptor.integration.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ro.fortech.caveatEmptor.exceptions.UserException;
import ro.fortech.caveatEmptor.integration.entities.User;
import ro.fortech.caveatEmptor.integration.entities.fields.UserFields;

@Repository
public class UserRepository {

	Logger logger = LoggerFactory.getLogger(UserRepository.class);

	@Autowired
	private EntityManagerFactory emf;

	public User getUserByUsername(String username, String password) throws Exception {
		logger.info("<<<START>>> UserRepository.getUserByUsername with params username: " + username
				+ ", password: ***********");

		User user = null;

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			Query query = em
					.createQuery("FROM " + User.class.getName() + " WHERE " + UserFields.USER_NAME + " = :username "
							+ "AND " + UserFields.PASSWORD + " = :password")
					.setParameter("username", username).setParameter("password", password);
			user = (User) query.getSingleResult();
		} catch (NoResultException e) {
			logger.info("No results found");
			throw new UserException("Username or password incorrect!");
		}
		em.getTransaction().commit();
		em.close();

		logger.info("<<<END>>> UserRepository.getUserByUsername");

		return user;

	}

}
