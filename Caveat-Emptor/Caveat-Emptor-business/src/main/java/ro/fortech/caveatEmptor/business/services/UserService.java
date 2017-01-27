package ro.fortech.caveatEmptor.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.fortech.caveatEmptor.dto.UserDto;
import ro.fortech.caveatEmptor.exceptions.UserException;
import ro.fortech.caveatEmptor.integration.entities.User;
import ro.fortech.caveatEmptor.integration.repositories.users.UserRepository;
import ro.fortech.caveatEmptor.utils.ObjectUtils;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public boolean isUser(UserDto userDto) throws Exception {
		this.validate(userDto, "login");
		User user = userRepository.getUserByUsernameAndPassword(userDto);

		return user != null && user.getId() != null;
	}

	public Integer createUser(UserDto userDto) throws Exception {
		this.validate(userDto, "create");
		return userRepository.createUser(userDto);
	}

	private void validate(UserDto userDto, String method) throws Exception {
		switch (method) {
		case "login": {
			if (ObjectUtils.isNullOrEmpty(userDto.getUsername()) || ObjectUtils.isNullOrEmpty(userDto.getPassword())) {
				throw new UserException("Provided credentials are not valid!");
			}
			break;
		}
		case "create": {
			if (ObjectUtils.isNullOrEmpty(userDto.getUsername()) || ObjectUtils.isNullOrEmpty(userDto.getPassword())
					|| ObjectUtils.isNullOrEmpty(userDto.getEmail())) {
				throw new UserException("Provided credentials are not valid!");
			}
			break;
		}
		default:
			throw new Exception("Internal server error!");
		}

	}

}
