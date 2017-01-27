package ro.fortech.caveatEmptor.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.fortech.caveatEmptor.business.services.UserService;
import ro.fortech.caveatEmptor.dto.ResponseDto;
import ro.fortech.caveatEmptor.dto.UserDto;

@Controller
@RequestMapping("/ws/users")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseDto<List<UserDto>> getAll() {
		ResponseDto<List<UserDto>> response = new ResponseDto<>();
		response.setData(new ArrayList<>());
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { "application/json" })
	public @ResponseBody ResponseDto<Integer> createUser(@RequestBody UserDto userDto) {
		ResponseDto<Integer> response = new ResponseDto<>();
		try {
			response.setData(userService.createUser(userDto));
			response.setSuccess(true);
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}

}
