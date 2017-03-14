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

    public Item getItemById(Long itemId) {

	logger.info("<<<START>>> ItemRepository.getItemById with params itemId: " + itemId);

	Item item = null;

	Session session = sessionFactory.openSession();
	Transaction tx = session.beginTransaction();

	item = session.get(Item.class, itemId);

	if (item != null && item.getId() != null) {
	    Hibernate.initialize(item.getBids());
	}

	session.flush();
	tx.commit();
	session.close();

	logger.info("<<<END>>> ItemRepository.getItemById");

	return item;

    }

    @SuppressWarnings("unchecked")
    public List<Item> getAllUserItems(ItemCriteriaDto itemCriteriaDto) throws Exception {

	logger.info("<<<START>>> ItemRepository.getAllUserItems");

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

	    if (items != null && !items.isEmpty()) {
		items.stream().forEach(item -> {
		    Hibernate.initialize(item.getBids());
		});
	    }

	    session.flush();
	    tx.commit();
	} catch (ConstraintViolationException e) {
	    System.out.println();
	    Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
	    Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
	    if (iterator.hasNext()) {
		ConstraintViolation<?> next = iterator.next();
		logger.error(next.getMessage());
		throw new CaveatException(next.getMessage());
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    throw new CaveatException(e.getMessage());
	} finally {
	    try {
		session.close();
	    } catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage());
	    }
	}

	if (items == null) {
	    items = new ArrayList<>();
	}

	logger.info("<<<END>>> ItemRepository.getAllUserItems");

	return items;
    }

    @SuppressWarnings("unchecked")
    public List<Item> getItemsByCategoryId(List<Long> categoryIds) {

	logger.info("<<<START>>> ItemRepository.getItemsByCategoryId with params: categoryId = " + categoryIds);

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

	logger.info("<<<END>>> ItemRepository.getItemsByCategoryId");

	return items;
    }

    public Long saveItem(Item item) throws Exception {

	logger.info("<<<START>>> ItemRepository.saveItem");

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
	    logger.error(e.getMessage());
	    throw new CaveatException(e.getMessage());
	} finally {
	    try {
		session.close();
	    } catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage());
	    }
	}

	logger.info("<<<END>>> ItemRepository.saveItem");

	return id;

    }

    public boolean changeItem(Item item) {
	logger.info("<<<START>>> ItemRepository.changeItem with parameters: itemId = " + item.getId());

	Session session = sessionFactory.openSession();
	Transaction tx = session.beginTransaction();

	session.get(Item.class, item.getId());

	session.merge(item);

	session.flush();
	tx.commit();
	session.close();

	logger.info("<<<END>>> ItemRepository.changeItem");

	return true;

    }

    public boolean changeItemState(Item item) {
	logger.info("<<<START>>> ItemRepository.changeItemState with parameters: itemId = " + item.getId());

	Session session = sessionFactory.openSession();
	Transaction tx = session.beginTransaction();

	Item itemToBeChanged = session.get(Item.class, item.getId());
	itemToBeChanged.setState(item.getState());
	session.update(itemToBeChanged);

	session.flush();
	tx.commit();
	session.close();

	logger.info("<<<END>>> ItemRepository.changeItemState");

	return true;

    }

}
