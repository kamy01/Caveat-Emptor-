package ro.fortech.caveatEmptor.business.transformers;

import ro.fortech.caveatEmptor.dto.RegistrationDto;
import ro.fortech.caveatEmptor.integration.entities.Registration;

public class RegistrationTransformer extends GeneralBeanTransformer<RegistrationDto, Registration> {

	@Override
	public RegistrationDto entityToDto(Registration origin, boolean deepClone, boolean secondLevelClone) {
		RegistrationDto destination = new RegistrationDto();

		if (origin != null && origin.getId() != null) {
			destination.setId(origin.getId());
			destination.setCreationDate(origin.getCreationDate());
			destination.setExpiryDate(origin.getExpiryDate());
			destination.setEnabled(origin.isEnabled());

			if (deepClone) {
				destination.setUser(new UserTransformer().entityToDto(origin.getUser(), secondLevelClone, false));
			}
		}

		return destination;
	}

	@Override
	public Registration dtoToEntity(RegistrationDto origin, boolean deepClone, boolean secondLevelClone) {
		Registration destination = new Registration();

		if (origin != null && origin.getId() != null) {
			destination.setId(origin.getId());
			destination.setCreationDate(origin.getCreationDate());
			destination.setExpiryDate(origin.getExpiryDate());
			destination.setEnabled(origin.isEnabled());

			if (deepClone) {
				destination.setUser(new UserTransformer().dtoToEntity(origin.getUser(), secondLevelClone, false));
			}

		}

		return destination;
	}

}
