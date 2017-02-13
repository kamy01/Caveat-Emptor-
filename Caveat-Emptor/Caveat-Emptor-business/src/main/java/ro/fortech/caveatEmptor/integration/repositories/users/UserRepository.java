package ro.fortech.caveatEmptor.integration.repositories.users;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ro.fortech.caveatEmptor.dto.UserDto;
import ro.fortech.caveatEmptor.exceptions.UserException;
import ro.fortech.caveatEmptor.integration.entities.User;
import ro.fortech.caveatEmptor.integration.queries.UserQueries;

@Repository
public class UserRepository {

	Logger logger = LoggerFactory.getLogger(UserRepository.class);

	@Autowired
	private SessionFactory sessionFactory;

	public User getUserByUsernameAndPassword(UserDto userDto) throws Exception {
		logger.info("<<<START>>> UserRepository.getUserByUsernameAndPassword with params username: "
				+ userDto.getUsername() + ", password: ***********");

		User user = null;

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(UserQueries.GET_USER_BY_USERNAME)
				.setParameter("username", userDto.getUsername()).setParameter("password", userDto.getPassword());
		user = (User) query.uniqueResult();
		session.flush();
		tx.commit();
		session.close();

		logger.info("<<<END>>> UserRepository.getUserByUsernameAndPassword");

		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() throws Exception {
		logger.info("<<<START>>> UserRepository.getAllUsers");

		List<User> users = null;

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(UserQueries.GET_ALL_USERS);
		users = query.list();
		session.flush();
		tx.commit();
		session.close();

		logger.info("<<<END>>> UserRepository.getAllUsers");

		return users;
	}

	public User getUserById(UserDto user) throws Exception {
		return getUserById(user.getId());
	}

	public User getUserById(Integer id) {
		logger.info("<<<START>>> UserRepository.getUserById with params id: " + id);

		User user = null;

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		user = session.get(User.class, id);
		session.flush();
		tx.commit();
		session.close();

		logger.info("<<<END>>> UserRepository.getUserById");

		return user;
	}

	public Integer createUser(User user) throws Exception {
		logger.info("<<<START>>> UserRepository.createUser with params username: " + user.getUsername()
				+ ", password: *********** , email: " + user.getEmail());

		Integer id = -1;

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			id = (Integer) session.save(user);
			session.flush();
			tx.commit();
		} catch (ConstraintViolationException e) {
			System.out.println();
			Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
			Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
			if (iterator.hasNext()) {
				ConstraintViolation<?> next = iterator.next();
				throw new UserException(next.getMessage());
			}
		} catch (Exception e) {
			throw new UserException(e.getMessage());
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		logger.info("<<<END>>> UserRepository.createUser");

		return id;
	}

}
