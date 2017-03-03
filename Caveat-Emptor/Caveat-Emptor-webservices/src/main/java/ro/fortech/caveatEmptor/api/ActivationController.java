package ro.fortech.caveatEmptor.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.fortech.caveatEmptor.business.services.RegistrationService;
import ro.fortech.caveatEmptor.dto.ResponseDto;
import ro.fortech.caveatEmptor.utils.ObjectUtils;

@Controller
@RequestMapping("/ws/activation")
public class ActivationController {

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(method = RequestMethod.GET, value = "/{registrationId}")
	public @ResponseBody ResponseDto<Boolean> activate(@PathVariable(name = "registrationId") String registrationId) {
		ResponseDto<Boolean> response = new ResponseDto<>();

		try {
			response.setSuccess(true);
			response.setData(registrationService.activateRegistration(registrationId));
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(ObjectUtils.capitalizeFirstLetter(e.getMessage()));
		}

		return response;
	}

}
