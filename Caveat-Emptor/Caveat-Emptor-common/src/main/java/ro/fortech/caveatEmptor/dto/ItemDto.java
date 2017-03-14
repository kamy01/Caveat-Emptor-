package ro.fortech.caveatEmptor.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ItemDto implements Serializable {

	private static final long serialVersionUID = 3872505178938025498L;

	private Long id;
	private String name;
	private String description;
	private BigDecimal initialPrice;
	private BigDecimal reservePrice;
	private Date startDate;
	private Date endDate;
	private String state;
	private Timestamp approvalDateTime;
	private CategoryDto category;
	private BidDto successfullBid;
	private List<BidDto> bids;
	private UserDto seller;
	private UserDto buyer;

	public ItemDto() {
		initMembers();
	}

	private void initMembers() {
		bids = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(BigDecimal initialPrice) {
		this.initialPrice = initialPrice;
	}

	public BigDecimal getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(BigDecimal reservePrice) {
		this.reservePrice = reservePrice;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Timestamp getApprovalDateTime() {
		return approvalDateTime;
	}

	public void setApprovalDateTime(Timestamp approvalDateTime) {
		this.approvalDateTime = approvalDateTime;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public BidDto getSuccessfullBid() {
		return successfullBid;
	}

	public void setSuccessfullBid(BidDto successfullBid) {
		this.successfullBid = successfullBid;
	}

	public List<BidDto> getBids() {
		return bids;
	}

	public void setBids(List<BidDto> bids) {
		this.bids = bids;
	}

	public UserDto getSeller() {
		return seller;
	}

	public void setSeller(UserDto seller) {
		this.seller = seller;
	}

	public UserDto getBuyer() {
		return buyer;
	}

	public void setBuyer(UserDto buyer) {
		this.buyer = buyer;
	}

}
