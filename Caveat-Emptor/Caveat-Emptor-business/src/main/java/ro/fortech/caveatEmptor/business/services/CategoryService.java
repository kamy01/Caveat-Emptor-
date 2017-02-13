package ro.fortech.caveatEmptor.business.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.fortech.caveatEmptor.dto.CategoryDto;
import ro.fortech.caveatEmptor.integration.entities.Category;
import ro.fortech.caveatEmptor.integration.repositories.categories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDto> getAllCategories() {
	return createCategoryList(categoryRepository.getAllCategories());
    }

    private List<CategoryDto> createCategoryList(List<Category> allCategories) {
	List<CategoryDto> categoryDtos = new ArrayList<>();

	if (allCategories != null && !allCategories.isEmpty()) {
	    categoryDtos = allCategories.stream().map(category -> createCategoryDto(category, true))
		    .collect(Collectors.toList());
	}

	return categoryDtos;
    }

    private CategoryDto createCategoryDto(Category category, boolean addChildren) {
	CategoryDto categoryDto = null;

	if (category != null && category.getId() != null) {
	    categoryDto = new CategoryDto();
	    categoryDto.setId(category.getId());
	    categoryDto.setParentId(category.getParent() != null ? category.getParent().getId() : null);
	    categoryDto.setName(category.getName());
	    if (addChildren) {
		categoryDto.setChildren(createCategoryList(category.getChildren()));
	    }
	}

	return categoryDto;
    }

}
