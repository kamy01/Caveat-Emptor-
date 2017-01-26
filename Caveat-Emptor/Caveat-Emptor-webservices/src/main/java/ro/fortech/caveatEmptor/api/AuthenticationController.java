package ro.fortech.caveatEmptor.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.fortech.caveatEmptor.UserService;
import ro.fortech.caveatEmptor.dto.Response;
import ro.fortech.caveatEmptor.dto.UserDto;

@Controller
@RequestMapping("/ws")
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = { "application/json" })
	public @ResponseBody Response<UserDto> authenticate(@RequestBody UserDto user) {
		System.out.println(user.getUsername());
		Response<UserDto> response = new Response<>();
		try {
			response.setSuccess(userService.isUser(user));
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
}
