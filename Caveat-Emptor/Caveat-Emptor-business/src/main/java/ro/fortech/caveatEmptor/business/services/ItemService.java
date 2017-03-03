package ro.fortech.caveatEmptor.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.fortech.caveatEmptor.business.transformers.ItemTransformer;
import ro.fortech.caveatEmptor.dto.ItemCriteriaDto;
import ro.fortech.caveatEmptor.dto.ItemDto;
import ro.fortech.caveatEmptor.exceptions.CaveatException;
import ro.fortech.caveatEmptor.integration.entities.Item;
import ro.fortech.caveatEmptor.integration.repositories.items.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public List<ItemDto> getAllItemsWithCriteria(ItemCriteriaDto itemCriteriaDto) throws Exception {
		switch (itemCriteriaDto.getOption()) {
		case "buy":
			itemCriteriaDto.setOption("buyers");
			break;
		case "sell":
			itemCriteriaDto.setOption("sellers");
			break;
		default:
			throw new CaveatException("Illegal item criteria!");
		}
		List<Item> allItemsBought = itemRepository.getAllUserItems(itemCriteriaDto);

		List<ItemDto> itemDtos = new ItemTransformer().entityToDtoList(allItemsBought, true, false);

		return itemDtos;
	}

	public Long createItem(ItemDto itemDto) {
		// itemRepository.saveItem(item)
		return -1L;
	}

}
