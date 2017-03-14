package ro.fortech.caveatEmptor.integration.entities;

import static ro.fortech.caveatEmptor.integration.entities.fields.ItemFields.APPROVAL_DATE_TIME;
import static ro.fortech.caveatEmptor.integration.entities.fields.ItemFields.DESCRIPTION;
import static ro.fortech.caveatEmptor.integration.entities.fields.ItemFields.END_DATE;
import static ro.fortech.caveatEmptor.integration.entities.fields.ItemFields.INITIAL_PRICE;
import static ro.fortech.caveatEmptor.integration.entities.fields.ItemFields.ITEMS;
import static ro.fortech.caveatEmptor.integration.entities.fields.ItemFields.ITEM_BUYER_ID;
import static ro.fortech.caveatEmptor.integration.entities.fields.ItemFields.ITEM_CATEGORY_ID;
import static ro.fortech.caveatEmptor.integration.entities.fields.ItemFields.ITEM_ID;
import static ro.fortech.caveatEmptor.integration.entities.fields.ItemFields.ITEM_NAME;
import static ro.fortech.caveatEmptor.integration.entities.fields.ItemFields.ITEM_SELLER_ID;
import static ro.fortech.caveatEmptor.integration.entities.fields.ItemFields.RESERVE_PRICE;
import static ro.fortech.caveatEmptor.integration.entities.fields.ItemFields.START_DATE;
import static ro.fortech.caveatEmptor.integration.entities.fields.ItemFields.STATE;
import static ro.fortech.caveatEmptor.integration.entities.fields.ItemFields.SUCCESSFUL_BID_ID;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = ITEMS)
public class Item {

    @Id
    @GeneratedValue
    @Column(name = ITEM_ID, unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = ITEM_NAME)
    private String name;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = INITIAL_PRICE)
    private BigDecimal initialPrice;

    @Column(name = RESERVE_PRICE)
    private BigDecimal reservePrice;

    @Column(name = START_DATE)
    private Date startDate;

    @Column(name = END_DATE)
    private Date endDate;

    @Column(name = STATE)
    private String state;

    @Column(name = APPROVAL_DATE_TIME)
    private Timestamp approvalDateTime;

    @ManyToOne
    @JoinColumn(name = ITEM_CATEGORY_ID)
    private Category category;

    @OneToOne
    @JoinColumn(name = SUCCESSFUL_BID_ID)
    private Bid successfullBid;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Bid> bids;

    @ManyToOne
    @JoinColumn(name = ITEM_SELLER_ID)
    private User seller;

    @ManyToOne
    @JoinColumn(name = ITEM_BUYER_ID)
    private User buyer;

    public Item() {
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

    public Category getCategory() {
	return category;
    }

    public void setCategory(Category category) {
	this.category = category;
    }

    public Bid getSuccessfullBid() {
	return successfullBid;
    }

    public void setSuccessfullBid(Bid successfullBid) {
	this.successfullBid = successfullBid;
    }

    public List<Bid> getBids() {
	return bids;
    }

    public void setBids(List<Bid> bids) {
	this.bids = bids;
    }

    public User getSeller() {
	return seller;
    }

    public void setSeller(User seller) {
	this.seller = seller;
    }

    public User getBuyer() {
	return buyer;
    }

    public void setBuyer(User buyer) {
	this.buyer = buyer;
    }

}
