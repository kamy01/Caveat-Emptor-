package ro.fortech.caveatEmptor.business.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.fortech.caveatEmptor.business.transformers.ItemTransformer;
import ro.fortech.caveatEmptor.dto.ItemCriteriaDto;
import ro.fortech.caveatEmptor.dto.ItemDto;
import ro.fortech.caveatEmptor.exceptions.CaveatException;
import ro.fortech.caveatEmptor.integration.entities.Category;
import ro.fortech.caveatEmptor.integration.entities.Item;
import ro.fortech.caveatEmptor.integration.entities.User;
import ro.fortech.caveatEmptor.integration.repositories.categories.CategoryRepository;
import ro.fortech.caveatEmptor.integration.repositories.items.ItemRepository;
import ro.fortech.caveatEmptor.integration.repositories.users.UserRepository;
import ro.fortech.caveatEmptor.states.ObjectStates;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ItemDto getItemById(Long itemId) {
	Item item = itemRepository.getItemById(itemId);

	ItemDto itemDto = new ItemTransformer().entityToDto(item, true, false);

	return itemDto;
    }

    public List<ItemDto> getAllItemsWithCriteria(ItemCriteriaDto itemCriteriaDto) throws Exception {
	List<Item> allItemsBought = itemRepository.getAllUserItems(itemCriteriaDto);

	if (!itemCriteriaDto.isSold()) {
	    // TODO add items for which the current user has a bid
	}

	List<ItemDto> itemDtos = new ItemTransformer().entityToDtoList(allItemsBought, true, false);

	return itemDtos;
    }

    public List<ItemDto> getAllItemsByCategoryId(Long categoryId) throws Exception {
	if (categoryId == null) {
	    throw new CaveatException("Could not retrieve items. Category not found!");
	}

	List<ItemDto> response = null;

	Category category = categoryRepository.getCategoryById(categoryId);

	List<Long> categoryIds = addChildrenIds(category);
	categoryIds.sort(Comparator.naturalOrder());

	response = new ItemTransformer().entityToDtoList(itemRepository.getItemsByCategoryId(categoryIds), true, false);

	if (response == null) {
	    response = new ArrayList<>();
	}

	return response;
    }

    public Long createItem(ItemDto itemDto) throws Exception {
	validateItem(itemDto, "create");

	itemDto.setState(ObjectStates.ITEM_STATE_OPEN);

	User owner = userRepository.getUserById(itemDto.getSeller().getId());
	Category category = categoryRepository.getCategoryById(itemDto.getCategory().getId());

	Item item = new ItemTransformer().dtoToEntity(itemDto, false, false);
	item.setSeller(owner);
	item.setCategory(category);

	return itemRepository.saveItem(item);
    }

    public boolean changeItem(ItemDto itemDto, boolean changeStateOnly) throws Exception {
	this.validateItem(itemDto, "change");

	boolean response = false;

	Item item = new ItemTransformer().dtoToEntity(itemDto, false, false);

	if (changeStateOnly) {
	    response = itemRepository.changeItemState(item);
	} else {
	    response = itemRepository.changeItem(item);
	}

	return response;
    }

    private void validateItem(ItemDto itemDto, String option) throws CaveatException {
	switch (option) {
	case "create":
	    if (itemDto.getSeller() == null || itemDto.getSeller().getId() == null) {
		throw new CaveatException("Item cannot be created! Seller not found!");
	    }

	    if (itemDto.getCategory() == null || itemDto.getCategory().getId() == null) {
		throw new CaveatException("Item cannot be created! Category not found!");
	    }

	    break;
	case "change":
	    if (itemDto == null || itemDto.getId() == null) {
		throw new CaveatException("Item cannot be changed! Id not found");
	    }
	    break;
	default:
	    break;
	}

    }

    private List<Long> addChildrenIds(Category category) {
	List<Long> childrenIds = new ArrayList<>();

	if (category != null && category.getId() != null) {
	    childrenIds.add(category.getId());
	    if (category.getChildren() != null && !category.getChildren().isEmpty()) {
		category.getChildren().stream().forEach(child -> childrenIds.addAll(addChildrenIds(child)));
	    }
	}

	return childrenIds;

    }

}
