package ro.fortech.caveatEmptor.business.services;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.fortech.caveatEmptor.business.transformers.RegistrationTransformer;
import ro.fortech.caveatEmptor.business.transformers.UserTransformer;
import ro.fortech.caveatEmptor.dto.RegistrationDto;
import ro.fortech.caveatEmptor.dto.UserDto;
import ro.fortech.caveatEmptor.exceptions.CaveatException;
import ro.fortech.caveatEmptor.integration.entities.Registration;
import ro.fortech.caveatEmptor.integration.repositories.registrations.RegistrationRepository;
import ro.fortech.caveatEmptor.integration.repositories.users.UserRepository;
import ro.fortech.caveatEmptor.utils.ObjectUtils;

@Service
public class RegistrationService {

	@Autowired
	private RegistrationRepository registrationRepository;

	@Autowired
	private UserRepository userRepository;

	public RegistrationDto createRegistration(UserDto userDto) throws Exception {

		Registration registration = new Registration();
		registration.setId(UUID.randomUUID().toString());

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

	public boolean activateRegistration(String registrationId) throws Exception {

		Registration registration = registrationRepository.getRegistrationById(registrationId);

		if (registration == null || ObjectUtils.isNullOrEmpty(registration.getId())) {
			throw new CaveatException("Registration not found!!");
		}

		RegistrationDto registrationDto = new RegistrationTransformer().entityToDto(registration, false, false);
		registrationDto.setEnabled(false);
		registrationRepository.enableRegistration(registrationDto);

		UserDto userDto = new UserTransformer().entityToDto(registration.getUser(), false, false);
		userDto.setEnabled(true);
		userRepository.enableUser(userDto);

		return true;
	}

}
