package ro.fortech.caveatEmptor.business.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.fortech.caveatEmptor.business.transformers.UserTransformer;
import ro.fortech.caveatEmptor.dto.EmailDto;
import ro.fortech.caveatEmptor.dto.RegistrationDto;
import ro.fortech.caveatEmptor.dto.UserDto;
import ro.fortech.caveatEmptor.exceptions.CaveatException;
import ro.fortech.caveatEmptor.integration.entities.User;
import ro.fortech.caveatEmptor.integration.repositories.users.UserRepository;
import ro.fortech.caveatEmptor.utils.ObjectUtils;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MailService mailService;

	@Autowired
	private RegistrationService registrationService;

	private BCryptPasswordEncoder encoder;

	private static String EMAIL_BODY;

	@PostConstruct
	public void init() {
		encoder = new BCryptPasswordEncoder();

		StringBuilder sb = new StringBuilder();
		sb.append("<h3>Hello %s,</h3>");
		sb.append("<p>We are glad you have registered to Caveat Emptor.</p></br>");
		sb.append(
				"<p>Please, activate your account by clicking this <a href=\"%s\">link</a> or use the following one in your browser: </p></br>");
		sb.append("<p>%s</p></br>");
		sb.append("<p>The activation link will expire after 24 hours.<p></br>");
		sb.append("<p>Best regards,<p></br>");
		sb.append("<p>The Caveat Emptor team<p></br>");

		EMAIL_BODY = sb.toString();
	}

	public UserDto authenticateUser(UserDto userDto) throws Exception {
		this.validate(userDto, "login");
		User user = userRepository.getUserByUsername(userDto);

		if (user == null || user.getId() == null) {
			throw new CaveatException("Provided credentials are not valid!");
		}

		if (!user.isEnabled()) {
			throw new CaveatException("Account is either not activated or disabled by an admin!");
		}

		if (!encoder.matches(userDto.getPassword(), user.getPassword())) {
			throw new CaveatException("Provided credentials are not valid!");
		}

		return new UserTransformer().entityToDto(user, false, false);
	}

	public List<UserDto> getAll() throws Exception {
		List<UserDto> userDtos = new ArrayList<>();

		userDtos = new UserTransformer().entityToDtoList(userRepository.getAllUsers(), true, false);

		if (userDtos == null || userDtos.isEmpty()) {
			throw new CaveatException("No users found");
		}

		return userDtos;
	}

	public UserDto enableUser(UserDto userDto) throws Exception {
		this.validate(userDto, "enable");

		User user = userRepository.enableUser(userDto);

		if (user == null || user.getId() == null) {
			throw new CaveatException("Provided credentials are not valid!");
		}

		return new UserTransformer().entityToDto(user, false, false);

	}

	@Transactional
	public Long registerUser(UserDto userDto) throws Exception {
		this.validate(userDto, "register");

		userDto.setEnabled(false);

		User user = new UserTransformer().dtoToEntity(userDto, false, false);
		user.setPassword(encoder.encode(user.getPassword()));
		Long userId = userRepository.saveUser(user);
		userDto.setId(userId);

		RegistrationDto registration = registrationService.createRegistration(userDto);

		String activationLink = "http://localhost:8080/Caveat-Emptor-webservices/ws/activation/" + registration.getId();
		String emailBody = String.format(EMAIL_BODY, userDto.getFullName(), activationLink, activationLink);
		String emailSubject = "Caveat Emptor - Account activation!";

		EmailDto emailDto = new EmailDto();
		emailDto.getRecipients().add(userDto.getEmail());
		emailDto.setBody(emailBody);
		emailDto.setSubject(emailSubject);

		mailService.sendEmail(emailDto);

		return userId;
	}

	private void validate(UserDto userDto, String method) throws Exception {
		switch (method) {
		case "login": {
			if (ObjectUtils.isNullOrEmpty(userDto.getUsername()) || ObjectUtils.isNullOrEmpty(userDto.getPassword())) {
				throw new CaveatException("Provided credentials are not valid!");
			}
			break;
		}
		case "register": {
			if (ObjectUtils.isNullOrEmpty(userDto.getUsername()) || ObjectUtils.isNullOrEmpty(userDto.getPassword())
					|| ObjectUtils.isNullOrEmpty(userDto.getEmail())) {
				throw new CaveatException("Provided credentials are not valid!");
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
