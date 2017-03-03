package ro.fortech.caveatEmptor.integration.repositories.items;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ro.fortech.caveatEmptor.dto.ItemCriteriaDto;
import ro.fortech.caveatEmptor.exceptions.CaveatException;
import ro.fortech.caveatEmptor.integration.entities.Item;

@Repository
public class ItemRepository {

	Logger logger = LoggerFactory.getLogger(ItemRepository.class);

	@Autowired
	private SessionFactory sessionFactory;

	public List<Item> getAllUserItems(ItemCriteriaDto itemCriteriaDto) throws Exception {
		List<Item> items = null;

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria citeria = session.createCriteria(Item.class, "item")
					.createAlias("item." + itemCriteriaDto.getOption(), "itemCriteriaOption", JoinType.LEFT_OUTER_JOIN)
					.add(Restrictions.eq("itemCriteriaOption.id", itemCriteriaDto.getId()))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			items = citeria.list();

			for (Item item : items) {
				Hibernate.initialize(item.getCategories());
				Hibernate.initialize(item.getBids());
				Hibernate.initialize(item.getSuccessfullBid());
				Hibernate.initialize(item.getBuyers());
				Hibernate.initialize(item.getSellers());
			}

			session.flush();
			tx.commit();
		} catch (ConstraintViolationException e) {
			System.out.println();
			Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
			Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
			if (iterator.hasNext()) {
				ConstraintViolation<?> next = iterator.next();
				throw new CaveatException(next.getMessage());
			}
		} catch (Exception e) {
			throw new CaveatException(e.getMessage());
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (items == null) {
			items = new ArrayList<>();
		}

		return items;
	}

	public Long saveItem(Item item) throws Exception {
		Long id = -1L;

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			id = (Long) session.save(item);
			session.flush();
			tx.commit();
		} catch (ConstraintViolationException e) {
			System.out.println();
			Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
			Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
			if (iterator.hasNext()) {
				ConstraintViolation<?> next = iterator.next();
				throw new CaveatException(next.getMessage());
			}
		} catch (Exception e) {
			throw new CaveatException(e.getMessage());
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return id;

	}

}
