package ro.fortech.caveatEmptor.api;

import java.util.List;

import org.apache.log4j.Logger;
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

import static ro.fortech.caveatEmptor.states.ObjectStates.ITEM_STATE_CLOSED;
import static ro.fortech.caveatEmptor.states.ObjectStates.ITEM_STATE_ABANDONED;

@Controller
@RequestMapping("/ws/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    private Logger logger = Logger.getLogger(ItemController.class);

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody ResponseDto<Long> createItem(@RequestBody ItemDto itemDto) {

	logger.info("<<<START>>> ItemController.createItem");

	ResponseDto<Long> response = new ResponseDto<>();
	try {
	    response.setData(itemService.createItem(itemDto));
	    response.setSuccess(true);
	} catch (Exception e) {
	    logger.error(e);
	    response.setSuccess(false);
	    response.setMessage(ObjectUtils.capitalizeFirstLetter(e.getMessage()));
	}

	logger.info("<<<END>>> ItemController.createItem");

	return response;
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public @ResponseBody ResponseDto<ItemDto> getItemById(@PathVariable("itemId") Long itemId) {

	logger.info("<<<START>>> ItemController.getItemById with parameters: itemdId = " + itemId);

	ResponseDto<ItemDto> response = new ResponseDto<>();
	try {
	    response.setData(itemService.getItemById(itemId));
	    response.setSuccess(true);
	} catch (Exception e) {
	    logger.error(e);
	    response.setSuccess(false);
	    response.setMessage(ObjectUtils.capitalizeFirstLetter(e.getMessage()));
	}

	logger.info("<<<END>>> ItemController.getItemById");

	return response;
    }

    @RequestMapping(value = "/bought/{userId}", method = RequestMethod.GET)
    public @ResponseBody ResponseDto<List<ItemDto>> getAllItemsBought(@PathVariable("userId") long userId) {

	logger.info("<<<START>>> ItemController.getAllItemsBought with parameters: userId = " + userId);

	ResponseDto<List<ItemDto>> response = getAllItemsWithCriteria(userId, false);

	logger.info("<<<END>>> ItemController.getAllItemsBought");

	return response;

    }

    @RequestMapping(value = "/sold/{userId}", method = RequestMethod.GET)
    public @ResponseBody ResponseDto<List<ItemDto>> getAllItemsSold(@PathVariable("userId") long userId) {

	logger.info("<<<START>>> ItemController.getAllItemsSold with parameters: userId = " + userId);

	ResponseDto<List<ItemDto>> response = getAllItemsWithCriteria(userId, true);

	logger.info("<<<END>>> ItemController.getAllItemsSold");

	return response;
    }

    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public @ResponseBody ResponseDto<List<ItemDto>> getAllItemsWithCategory(
	    @PathVariable("categoryId") Long categoryId) {

	logger.info("<<<START>>> ItemController.getAllItemsWithCategory with parameters: categoryId = " + categoryId);

	ResponseDto<List<ItemDto>> response = new ResponseDto<>();

	try {
	    response.setData(itemService.getAllItemsByCategoryId(categoryId));
	    response.setSuccess(true);
	} catch (Exception e) {
	    logger.error(e);
	    response.setSuccess(false);
	    response.setMessage(ObjectUtils.capitalizeFirstLetter(e.getMessage()));
	}

	logger.info("<<<END>>> ItemController.getAllItemsWithCategory");

	return response;
    }

    @RequestMapping(value = "/{itemId}/close", method = RequestMethod.PUT)
    public @ResponseBody ResponseDto<Boolean> closeItem(@PathVariable("itemId") Long itemId) {

	logger.info("<<<START>>> ItemController.closeItem with parameters: itemId = " + itemId);

	ResponseDto<Boolean> response = new ResponseDto<>();

	ItemDto itemDto = new ItemDto();
	itemDto.setId(itemId);
	itemDto.setState(ITEM_STATE_CLOSED);

	response = changeItem(itemDto, true);

	logger.info("<<<END>>> ItemController.closeItem");

	return response;
    }

    @RequestMapping(value = "/{itemId}/abandon", method = RequestMethod.PUT)
    public @ResponseBody ResponseDto<Boolean> abandonItem(@PathVariable("itemId") Long itemId) {

	logger.info("<<<START>>> ItemController.abandonItem with parameters: itemId = " + itemId);

	ResponseDto<Boolean> response = new ResponseDto<>();

	ItemDto itemDto = new ItemDto();
	itemDto.setId(itemId);
	itemDto.setState(ITEM_STATE_ABANDONED);

	response = changeItem(itemDto, true);

	logger.info("<<<END>>> ItemController.abandonItem");

	return response;
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public @ResponseBody ResponseDto<Boolean> changeItem(@RequestBody ItemDto itemDto) {

	logger.info("<<<START>>> ItemController.abandonItem with parameters: itemId = " + itemDto.getId());

	ResponseDto<Boolean> response = changeItem(itemDto, false);

	logger.info("<<<END>>> ItemController.changeItem");

	return response;
    }

    private ResponseDto<Boolean> changeItem(ItemDto itemDto, boolean changeStateOnly) {
	ResponseDto<Boolean> response = new ResponseDto<>();

	try {
	    response.setData(itemService.changeItem(itemDto, changeStateOnly));
	    response.setSuccess(true);
	} catch (Exception e) {
	    logger.error(e);
	    response.setSuccess(false);
	    response.setMessage(ObjectUtils.capitalizeFirstLetter(e.getMessage()));
	}

	return response;
    }

    private ResponseDto<List<ItemDto>> getAllItemsWithCriteria(long userId, boolean option) {
	ResponseDto<List<ItemDto>> response = new ResponseDto<>();

	ItemCriteriaDto itemCriteriaDto = new ItemCriteriaDto();
	itemCriteriaDto.setId(userId);
	itemCriteriaDto.setSold(option);
	try {
	    response.setSuccess(true);
	    response.setData(itemService.getAllItemsWithCriteria(itemCriteriaDto));
	} catch (Exception e) {
	    logger.error(e);
	    response.setSuccess(false);
	    response.setMessage(ObjectUtils.capitalizeFirstLetter(e.getMessage()));
	}
	return response;
    }
}
