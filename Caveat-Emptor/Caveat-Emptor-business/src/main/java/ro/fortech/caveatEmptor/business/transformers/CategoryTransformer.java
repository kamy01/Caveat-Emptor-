package ro.fortech.caveatEmptor.business.transformers;

import ro.fortech.caveatEmptor.dto.CategoryDto;
import ro.fortech.caveatEmptor.integration.entities.Category;

public class CategoryTransformer extends GeneralBeanTransformer<CategoryDto, Category> {

	@Override
	public CategoryDto entityToDto(Category origin, boolean deepClone, boolean secondLevelClone) {
		CategoryDto destination = null;

		if (origin != null && origin.getId() != null) {
			destination = new CategoryDto();
			destination.setId(origin.getId());
			destination.setName(origin.getName());
			destination.setParent(new CategoryTransformer().entityToDto(origin.getParent(), false, false));

			if (deepClone) {
				destination.setChildren(
						new CategoryTransformer().entityToDtoList(origin.getChildren(), secondLevelClone, false));
			}

		}

		return destination;
	}

	@Override
	public Category dtoToEntity(CategoryDto origin, boolean deepClone, boolean secondLevelClone) {
		Category destination = null;

		if (origin != null) {
			destination = new Category();
			destination.setId(origin.getId());
			destination.setName(origin.getName());
			destination.setParent(new CategoryTransformer().dtoToEntity(origin.getParent(), false, false));

			if (deepClone) {
				destination.setChildren(
						new CategoryTransformer().dtoToEntityList(origin.getChildren(), secondLevelClone, false));
			}

		}

		return destination;
	}

}
