package ro.fortech.caveatEmptor.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.fortech.caveatEmptor.business.services.CategoryService;
import ro.fortech.caveatEmptor.dto.CategoryDto;
import ro.fortech.caveatEmptor.dto.ResponseDto;

@Controller
@RequestMapping("/ws/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseDto<List<CategoryDto>> getAll() {
		ResponseDto<List<CategoryDto>> response = new ResponseDto<>();

		try {
			response.setData(categoryService.getAllCategories());
			response.setSuccess(true);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setSuccess(false);

		}
		return response;
	}

}
