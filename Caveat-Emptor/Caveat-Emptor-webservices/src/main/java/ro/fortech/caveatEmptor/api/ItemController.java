package ro.fortech.caveatEmptor.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(method = RequestMethod.POST, consumes = { "application/json" })
    public @ResponseBody ResponseDto<List<ItemDto>> getAllWithCriteria(@RequestBody ItemCriteriaDto itemCriteriaDto) {
	ResponseDto<List<ItemDto>> response = new ResponseDto<>();
	try {
	    response.setData(itemService.getAllItemsWithCriteria(itemCriteriaDto));
	    response.setSuccess(true);
	} catch (Exception e) {
	    response.setSuccess(false);
	    response.setMessage(ObjectUtils.capitalizeFirstLetter(e.getMessage()));
	}
	return response;

    }
}
