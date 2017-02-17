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
    private List<Long> categories;
    private BidDto successfullBid;
    private List<BidDto> bids;
    private List<UserDto> sellers;
    private List<UserDto> buyers;

    public ItemDto() {
	initMembers();
    }

    private void initMembers() {
	categories = new ArrayList<>();
	bids = new ArrayList<>();
	sellers = new ArrayList<>();
	buyers = new ArrayList<>();
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

    public List<Long> getCategories() {
	return categories;
    }

    public void setCategories(List<Long> categories) {
	this.categories = categories;
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

    public List<UserDto> getSellers() {
	return sellers;
    }

    public void setSellers(List<UserDto> sellers) {
	this.sellers = sellers;
    }

    public List<UserDto> getBuyers() {
	return buyers;
    }

    public void setBuyers(List<UserDto> buyers) {
	this.buyers = buyers;
    }

}
