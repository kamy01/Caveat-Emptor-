package ro.fortech.caveatEmptor.business.transformers;

import ro.fortech.caveatEmptor.dto.ItemDto;
import ro.fortech.caveatEmptor.integration.entities.Item;

public class ItemTransformer extends GeneralBeanTransformer<ItemDto, Item> {

	@Override
	public ItemDto entityToDto(Item origin, boolean deepClone, boolean secondLevelClone) {
		ItemDto destination = null;

		if (origin != null && origin.getId() != null) {
			destination = new ItemDto();
			destination.setId(origin.getId());
			destination.setName(origin.getName());
			destination.setDescription(origin.getDescription());
			destination.setInitialPrice(origin.getInitialPrice());
			destination.setReservePrice(origin.getReservePrice());
			destination.setStartDate(origin.getStartDate());
			destination.setEndDate(origin.getEndDate());
			destination.setState(origin.getState());
			destination.setApprovalDateTime(origin.getApprovalDateTime());

			if (deepClone) {
				destination.setCategory(
						new CategoryTransformer().entityToDto(origin.getCategory(), secondLevelClone, false));
				destination.setSuccessfullBid(
						new BidTransformer().entityToDto(origin.getSuccessfullBid(), secondLevelClone, false));
				destination.setBids(new BidTransformer().entityToDtoList(origin.getBids(), secondLevelClone, false));
				destination.setBuyer(new UserTransformer().entityToDto(origin.getBuyer(), secondLevelClone, false));
				destination.setSeller(new UserTransformer().entityToDto(origin.getSeller(), secondLevelClone, false));
			}
		}

		return destination;
	}

	@Override
	public Item dtoToEntity(ItemDto origin, boolean deepClone, boolean secondLevelClone) {
		Item destination = null;

		if (origin != null) {
			destination = new Item();
			destination.setId(origin.getId());
			destination.setName(origin.getName());
			destination.setDescription(origin.getDescription());
			destination.setInitialPrice(origin.getInitialPrice());
			destination.setReservePrice(origin.getReservePrice());
			destination.setStartDate(origin.getStartDate());
			destination.setEndDate(origin.getEndDate());
			destination.setState(origin.getState());
			destination.setApprovalDateTime(origin.getApprovalDateTime());

			if (deepClone) {
				destination.setCategory(
						new CategoryTransformer().dtoToEntity(origin.getCategory(), secondLevelClone, false));
				destination.setSuccessfullBid(
						new BidTransformer().dtoToEntity(origin.getSuccessfullBid(), secondLevelClone, false));
				destination.setBids(new BidTransformer().dtoToEntityList(origin.getBids(), secondLevelClone, false));
				destination.setBuyer(new UserTransformer().dtoToEntity(origin.getBuyer(), secondLevelClone, false));
				destination.setSeller(new UserTransformer().dtoToEntity(origin.getSeller(), secondLevelClone, false));
			}
		}

		return destination;
	}

}
