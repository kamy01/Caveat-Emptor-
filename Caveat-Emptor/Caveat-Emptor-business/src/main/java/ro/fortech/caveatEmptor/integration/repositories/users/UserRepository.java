package ro.fortech.caveatEmptor.integration.repositories.users;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ro.fortech.caveatEmptor.dto.UserDto;
import ro.fortech.caveatEmptor.integration.entities.User;
import ro.fortech.caveatEmptor.integration.queries.UserQueries;

@Repository
public class UserRepository {

	Logger logger = LoggerFactory.getLogger(UserRepository.class);

	@Autowired
	private SessionFactory sessionFactory;

	public User getUserByUsernameAndPassword(UserDto userDto) throws Exception {
		logger.info(
				"<<<START>>> UserRepository.getUserByUsernameAndPassword with params username: " + userDto.getUsername() + ", password: ***********");

		User user = null;

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(UserQueries.GET_USER_BY_USERNAME).setParameter("username", userDto.getUsername()).setParameter("password",
				userDto.getPassword());
		user = (User) query.uniqueResult();
		session.flush();
		tx.commit();
		session.close();

		logger.info("<<<END>>> UserRepository.getUserByUsernameAndPassword");

		return user;
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

	public Integer createUser(UserDto userDto) {
		logger.info("<<<START>>> UserRepository.createUser with params username: " + userDto.getUsername() + ", password: *********** , email: "
				+ userDto.getEmail());

		Integer id = -1;

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		id = (Integer) session.save(createUserEntity(userDto));
		session.flush();
		tx.commit();
		session.close();

		logger.info("<<<END>>> UserRepository.createUser");

		return id;
	}

	private User createUserEntity(UserDto userDto) {

		User user = new User();
		user.setAdmin(false);
		user.setUsername(userDto.getUsername());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());

		return user;
	}

}
