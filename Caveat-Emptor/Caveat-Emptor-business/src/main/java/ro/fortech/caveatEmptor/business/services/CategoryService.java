package ro.fortech.caveatEmptor.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.fortech.caveatEmptor.business.transformers.CategoryTransformer;
import ro.fortech.caveatEmptor.dto.CategoryDto;
import ro.fortech.caveatEmptor.integration.repositories.categories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<CategoryDto> getAllCategories() {
		return new CategoryTransformer().entityToDtoList(categoryRepository.getAllCategories(), true, true);
	}

}
