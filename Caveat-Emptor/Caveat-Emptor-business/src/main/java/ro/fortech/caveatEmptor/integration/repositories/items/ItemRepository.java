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

	@SuppressWarnings("unchecked")
	public List<Item> getAllUserItems(ItemCriteriaDto itemCriteriaDto) throws Exception {
		List<Item> items = null;

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(Item.class, "item");
			if (itemCriteriaDto.isSold()) {
				criteria.add(Restrictions.eq("seller.id", itemCriteriaDto.getId()));
			} else {
				criteria.add(Restrictions.eq("buyer.id", itemCriteriaDto.getId()));
			}

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			items = criteria.list();

			for (Item item : items) {
				Hibernate.initialize(item.getCategory());
				Hibernate.initialize(item.getBids());
				Hibernate.initialize(item.getSuccessfullBid());
				Hibernate.initialize(item.getBuyer());
				Hibernate.initialize(item.getSeller());
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

	@SuppressWarnings("unchecked")
	public List<Item> getItemsByCategoryId(List<Long> categoryIds) {

		List<Item> items = null;

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Item.class, "item").add(Restrictions.in("category.id", categoryIds))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		items = criteria.list();

		if (items != null && !items.isEmpty()) {
			items.stream().forEach(item -> {
				Hibernate.initialize(item.getBids());
			});
		}

		session.flush();
		tx.commit();
		session.close();

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
