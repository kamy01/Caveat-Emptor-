package ro.fortech.caveatEmptor.business.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.fortech.caveatEmptor.business.transformers.UserTransformer;
import ro.fortech.caveatEmptor.dto.UserDto;
import ro.fortech.caveatEmptor.exceptions.UserException;
import ro.fortech.caveatEmptor.integration.entities.User;
import ro.fortech.caveatEmptor.integration.repositories.users.UserRepository;
import ro.fortech.caveatEmptor.utils.ObjectUtils;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MailService eMailService;

	private BCryptPasswordEncoder encoder;

	@PostConstruct
	public void init() {
		encoder = new BCryptPasswordEncoder();
	}

	public UserDto authenticateUser(UserDto userDto) throws Exception {
		this.validate(userDto, "login");
		User user = userRepository.getUserByUsername(userDto);

		if (user == null || user.getId() == null) {
			throw new UserException("Provided credentials are not valid!");
		}

		// if (!user.isEnabled()) {
		// throw new UserException(
		// "Account has not been activated yet. Please activate using the
		// provided link in the mail!");
		// }

		if (!encoder.matches(userDto.getPassword(), user.getPassword())) {
			throw new UserException("Provided credentials are not valid!");
		}

		return new UserTransformer().entityToDto(user, false, false);
	}

	public List<UserDto> getAll() throws Exception {
		List<UserDto> userDtos = new ArrayList<>();

		userDtos = new UserTransformer().entityToDtoList(userRepository.getAllUsers(), true, false);

		if (userDtos == null || userDtos.isEmpty()) {
			throw new UserException("No users found");
		}

		return userDtos;
	}

	public UserDto enableUser(UserDto userDto) throws Exception {
		this.validate(userDto, "enable");

		User user = userRepository.enableUser(userDto);

		if (user == null || user.getId() == null) {
			throw new UserException("Provided credentials are not valid!");
		}

		return new UserTransformer().entityToDto(user, false, false);

	}

	public Long registerUser(UserDto userDto) throws Exception {
		this.validate(userDto, "register");

		userDto.setEnabled(false);

		User user = new UserTransformer().dtoToEntity(userDto, false, false);
		user.setPassword(encoder.encode(user.getPassword()));
		Long userId = userRepository.saveUser(user);
		userDto.setId(userId);

		eMailService.sendEmail(userDto);

		return userId;
	}

	private void validate(UserDto userDto, String method) throws Exception {
		switch (method) {
		case "login": {
			if (ObjectUtils.isNullOrEmpty(userDto.getUsername()) || ObjectUtils.isNullOrEmpty(userDto.getPassword())) {
				throw new UserException("Provided credentials are not valid!");
			}
			break;
		}
		case "register": {
			if (ObjectUtils.isNullOrEmpty(userDto.getUsername()) || ObjectUtils.isNullOrEmpty(userDto.getPassword())
					|| ObjectUtils.isNullOrEmpty(userDto.getEmail())) {
				throw new UserException("Provided credentials are not valid!");
			}
			break;
		}
		case "enable": {

			break;
		}
		default:
			throw new Exception("Internal server error!");
		}

	}

}
