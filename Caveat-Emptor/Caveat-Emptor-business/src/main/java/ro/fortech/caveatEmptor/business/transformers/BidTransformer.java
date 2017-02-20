package ro.fortech.caveatEmptor.business.transformers;

import ro.fortech.caveatEmptor.dto.BidDto;
import ro.fortech.caveatEmptor.integration.entities.Bid;

public class BidTransformer extends GeneralBeanTransformer<BidDto, Bid> {

    @Override
    public BidDto entityToDto(Bid origin, boolean deepClone, boolean secondLevelClone) {
	BidDto destination = null;

	if (origin != null && origin.getId() != null) {
	    destination = new BidDto();
	    destination.setId(origin.getId());

	    // if (deepClone) {
	    destination.setBidder(new UserTransformer().entityToDto(origin.getBidder(), secondLevelClone, false));
	    // }
	}

	return destination;
    }

    @Override
    public Bid dtoToEntity(BidDto origin, boolean deepClone, boolean secondLevelClone) {
	Bid destination = null;

	if (origin != null && origin.getId() != null) {
	    destination = new Bid();
	    destination.setId(origin.getId());

	    // if (deepClone) {
	    destination.setBidder(new UserTransformer().dtoToEntity(origin.getBidder(), secondLevelClone, false));
	    // }
	}

	return destination;
    }

}
