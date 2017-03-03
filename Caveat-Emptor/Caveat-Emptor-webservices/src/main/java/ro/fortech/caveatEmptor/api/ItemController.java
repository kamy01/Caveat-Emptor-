package ro.fortech.caveatEmptor.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.fortech.caveatEmptor.business.services.ItemService;
import ro.fortech.caveatEmptor.dto.ItemCriteriaDto;
import ro.fortech.caveatEmptor.dto.ItemDto;
import ro.fortech.caveatEmptor.dto.ResponseDto;
import ro.fortech.caveatEmptor.utils.ObjectUtils;

@Controller
@RequestMapping("/ws/items")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "/bought/{userId}", method = RequestMethod.GET)
	public @ResponseBody ResponseDto<List<ItemDto>> getAllItemsBought(@PathVariable("userId") long userId) {
		ResponseDto<List<ItemDto>> response = getAllItemsWithCriteria(userId, "buy");
		return response;

	}

	@RequestMapping(value = "/sold/{userId}", method = RequestMethod.GET)
	public @ResponseBody ResponseDto<List<ItemDto>> getAllItemsSold(@PathVariable("userId") long userId) {
		ResponseDto<List<ItemDto>> response = getAllItemsWithCriteria(userId, "sell");
		return response;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody ResponseDto<Long> createItem(@RequestBody ItemDto itemDto) {
		ResponseDto<Long> response = new ResponseDto<>();
		try {
			response.setData(itemService.createItem(itemDto));
			response.setSuccess(true);
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(ObjectUtils.capitalizeFirstLetter(e.getMessage()));
		}
		return response;
	}

	private ResponseDto<List<ItemDto>> getAllItemsWithCriteria(long userId, String option) {
		ResponseDto<List<ItemDto>> response = new ResponseDto<>();

		ItemCriteriaDto itemCriteriaDto = new ItemCriteriaDto();
		itemCriteriaDto.setId(userId);
		itemCriteriaDto.setOption(option);
		try {
			response.setSuccess(true);
			response.setData(itemService.getAllItemsWithCriteria(itemCriteriaDto));
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(ObjectUtils.capitalizeFirstLetter(e.getMessage()));
		}
		return response;
	}
}
