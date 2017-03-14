package ro.fortech.caveatEmptor.business.transformers;

import ro.fortech.caveatEmptor.dto.UserDto;
import ro.fortech.caveatEmptor.integration.entities.User;

public class UserTransformer extends GeneralBeanTransformer<UserDto, User> {

    @Override
    public UserDto entityToDto(User origin, boolean deepClone, boolean secondLevelClone) {
	UserDto destination = null;

	if (origin != null) {
	    destination = new UserDto();
	    destination.setId(origin.getId());
	    destination.setFirstName(origin.getFirstName());
	    destination.setLastName(origin.getLastName());
	    destination.setUsername(origin.getUsername());
	    destination.setPassword(origin.getPassword());
	    destination.setEmail(origin.getEmail());
	    destination.setRanking(origin.getRanking());
	    destination.setAdmin(origin.isAdmin());
	    destination.setEnabled(origin.isEnabled());

	    if (deepClone) {
		destination.setItemsBought(
			new ItemTransformer().entityToDtoList(origin.getItemsBought(), secondLevelClone, false));
		destination.setItemsSold(
			new ItemTransformer().entityToDtoList(origin.getItemsSold(), secondLevelClone, false));
	    }
	}

	return destination;
    }

    @Override
    public User dtoToEntity(UserDto origin, boolean deepClone, boolean secondLevelClone) {
	User destination = null;

	if (origin != null) {
	    destination = new User();
	    destination.setId(origin.getId());
	    destination.setFirstName(origin.getFirstName());
	    destination.setLastName(origin.getLastName());
	    destination.setUsername(origin.getUsername());
	    destination.setPassword(origin.getPassword());
	    destination.setEmail(origin.getEmail());
	    destination.setRanking(origin.getRanking());
	    destination.setAdmin(origin.isAdmin());
	    destination.setEnabled(origin.isEnabled());

	    if (deepClone) {
		destination.setItemsBought(
			new ItemTransformer().dtoToEntityList(origin.getItemsBought(), secondLevelClone, false));
		destination.setItemsSold(
			new ItemTransformer().dtoToEntityList(origin.getItemsSold(), secondLevelClone, false));
	    }
	}
	return destination;
    }

}
