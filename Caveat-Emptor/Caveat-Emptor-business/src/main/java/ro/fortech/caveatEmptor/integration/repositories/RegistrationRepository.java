package ro.fortech.caveatEmptor.integration.repositories;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ro.fortech.caveatEmptor.integration.entities.Registration;

@Repository
public class RegistrationRepository {

	Logger logger = LoggerFactory.getLogger(RegistrationRepository.class);

	@Autowired
	private SessionFactory sessionFactory;

	public Registration getRegistrationById(String registrationId) {
		logger.info("<<<START>>> RegistrationRepository.getRegistrationById with params id: " + registrationId);

		Registration registration = null;

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		registration = (Registration) session.createCriteria(Registration.class)
				.add(Restrictions.eq("id", registrationId)).uniqueResult();

		session.flush();
		tx.commit();
		session.close();

		logger.info("<<<END>>> RegistrationRepository.getRegistrationById");

		return registration;

	}

	public String saveRegistration(Registration registration) throws Exception {
		logger.info("<<<START>>> RegistrationRepository.saveRegistration with params id: " + registration.getId());

		String id = null;

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			id = (String) session.save(registration);
			session.flush();
			tx.commit();
		} catch (ConstraintViolationException e) {
			System.out.println();
			Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
			Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
			if (iterator.hasNext()) {
				ConstraintViolation<?> next = iterator.next();
				throw new Exception(next.getMessage());
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
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
