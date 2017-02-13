package ro.fortech.caveatEmptor.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.fortech.caveatEmptor.business.services.UserService;
import ro.fortech.caveatEmptor.dto.ResponseDto;
import ro.fortech.caveatEmptor.dto.UserDto;
import ro.fortech.caveatEmptor.utils.ObjectUtils;

@Controller
@RequestMapping("/ws/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ResponseDto<List<UserDto>> getAll() {
	ResponseDto<List<UserDto>> response = new ResponseDto<>();
	try {
	    response.setData(userService.getAll());
	    response.setSuccess(true);
	} catch (Exception e) {
	    response.setSuccess(false);
	    response.setMessage(ObjectUtils.capitalizeFirstLetter(e.getMessage()));
	}
	return response;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public @ResponseBody ResponseDto<UserDto> getUserById(@PathVariable("userId") Long userId) {
	ResponseDto<UserDto> response = new ResponseDto<>();

	return response;

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{userId}")
    public @ResponseBody ResponseDto<UserDto> updateUserById(@PathVariable("userId") Long userId,
	    @RequestBody UserDto userDto) {
	ResponseDto<UserDto> response = new ResponseDto<>();

	try {
	    response.setData(null);
	    response.setSuccess(true);
	} catch (Exception e) {
	    response.setSuccess(false);
	    response.setMessage(ObjectUtils.capitalizeFirstLetter(e.getMessage()));
	}
	return response;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = { "application/json" })
    public @ResponseBody ResponseDto<Long> createUser(@RequestBody UserDto userDto) {
	ResponseDto<Long> response = new ResponseDto<>();
	try {
	    response.setData(userService.createUser(userDto));
	    response.setSuccess(true);
	} catch (Exception e) {
	    response.setSuccess(false);
	    response.setMessage(ObjectUtils.capitalizeFirstLetter(e.getMessage()));
	}
	return response;
    }

}
