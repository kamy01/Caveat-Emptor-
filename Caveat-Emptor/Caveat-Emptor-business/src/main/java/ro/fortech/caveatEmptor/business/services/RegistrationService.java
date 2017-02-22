package ro.fortech.caveatEmptor.business.services;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ro.fortech.caveatEmptor.business.transformers.RegistrationTransformer;
import ro.fortech.caveatEmptor.business.transformers.UserTransformer;
import ro.fortech.caveatEmptor.dto.RegistrationDto;
import ro.fortech.caveatEmptor.dto.UserDto;
import ro.fortech.caveatEmptor.integration.entities.Registration;
import ro.fortech.caveatEmptor.integration.repositories.RegistrationRepository;

@Service
public class RegistrationService {

	@Autowired
	private RegistrationRepository registrationRepository;

	private BCryptPasswordEncoder encoder;

	@PostConstruct
	public void init() {
		encoder = new BCryptPasswordEncoder();
	}

	public RegistrationDto createRegistration(UserDto userDto) throws Exception {

		Registration registration = new Registration();
		registration.setId(encoder.encode(userDto.getUsername()));

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		registration.setCreationDate(new Timestamp(cal.getTimeInMillis()));

		cal.add(Calendar.DAY_OF_YEAR, 1);
		registration.setExpiryDate(new Timestamp(cal.getTimeInMillis()));

		registration.setEnabled(true);
		registration.setUser(new UserTransformer().dtoToEntity(userDto, false, false));

		registrationRepository.saveRegistration(registration);

		return new RegistrationTransformer().entityToDto(registration, true, false);
	}

}
