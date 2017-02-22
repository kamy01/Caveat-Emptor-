package ro.fortech.caveatEmptor.business.transformers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GeneralBeanTransformer<D, E> {

    public List<E> dtoToEntityList(List<D> dtos, boolean deepClone, boolean secondLevelClone) {
	List<E> entities = new ArrayList<>();

	if (dtos != null && !dtos.isEmpty()) {
	    entities = dtos.stream().map(dto -> dtoToEntity(dto, deepClone, secondLevelClone))
		    .collect(Collectors.toList());
	}

	return entities;
    }

    public List<D> entityToDtoList(List<E> entities, boolean deepClone, boolean secondLevelClone) {
	List<D> dtos = new ArrayList<>();

	if (entities != null && !entities.isEmpty()) {
	    dtos = entities.stream().map(entity -> entityToDto(entity, deepClone, secondLevelClone))
		    .collect(Collectors.toList());
	}

	return dtos;
    }

    public abstract D entityToDto(E origin, boolean deepClone, boolean secondLevelClone);

	public abstract E dtoToEntity(D origin, boolean deepClone, boolean secondLevelClone);

}
