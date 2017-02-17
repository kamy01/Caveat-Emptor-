package ro.fortech.caveatEmptor.business.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	if (!encoder.matches(userDto.getPassword(), user.getPassword())) {
	    throw new UserException("Provided credentials are not valid!");
	}

	return createUserDto(user);
    }

    public List<UserDto> getAll() throws Exception {
	List<UserDto> userDtos = new ArrayList<>();

	userDtos = userRepository.getAllUsers().stream().map(user -> createUserDto(user)).collect(Collectors.toList());

	if (userDtos == null || userDtos.isEmpty()) {
	    throw new UserException("No users found");
	}

	return userDtos;
    }

    public Long createUser(UserDto userDto) throws Exception {
	this.validate(userDto, "create");
	return userRepository.createUser(createUserEntity(userDto));
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

    private UserDto createUserDto(User user) {
	UserDto userDto = new UserDto();
	userDto.setId(user.getId());
	userDto.setUsername(user.getUsername());
	userDto.setAdmin(user.isAdmin());
	userDto.setEmail(user.getEmail());
	userDto.setFirstName(user.getFirstName());
	userDto.setLastName(user.getLastName());
	return userDto;
    }

    private User createUserEntity(UserDto userDto) {

	User user = new User();
	user.setAdmin(false);
	user.setUsername(userDto.getUsername());
	user.setFirstName(userDto.getFirstName());
	user.setLastName(userDto.getLastName());
	user.setEmail(userDto.getEmail());
	user.setPassword(encoder.encode(userDto.getPassword()));

	return user;
    }

}
