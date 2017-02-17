package ro.fortech.caveatEmptor.integration.repositories.categories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ro.fortech.caveatEmptor.integration.entities.Category;

@Repository
public class CategoryRepository {

    Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<Category> getAllCategories() {
	logger.info("<<<START>>> CaterogyRepository.getAllCategories");

	List<Category> categories = null;

	Session session = sessionFactory.openSession();
	Transaction tx = session.beginTransaction();

	categories = (List<Category>) session.createCriteria(Category.class).add(Restrictions.isNull("parent")).list();
	categories = categories.parallelStream().distinct().collect(Collectors.toList());

	session.flush();
	tx.commit();

	logger.info("<<<END>>> CaterogyRepository.getAllCategories");

	if (categories == null) {
	    categories = new ArrayList<>();
	}

	return categories;
    }

}
