package ro.fortech.caveatEmptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.fortech.caveatEmptor.dto.UserDto;
import ro.fortech.caveatEmptor.integration.entities.User;
import ro.fortech.caveatEmptor.integration.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public boolean isUser(UserDto userDto) throws Exception {
		this.validate(userDto);
		User user = userRepository.getUserByUsername(userDto.getUsername(), userDto.getPassword());

		
		
		return user != null && user.getId() != null;
	}

	private void validate(UserDto userDto) {
		// TODO Auto-generated method stub

	}

}
