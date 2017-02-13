package ro.fortech.caveatEmptor.integration.entities;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ro.fortech.caveatEmptor.integration.entities.fields.CategoryFields;
import ro.fortech.caveatEmptor.integration.entities.fields.ItemFields;
import ro.fortech.caveatEmptor.integration.entities.fields.UserFields;

@Entity
@Table(name = ItemFields.ITEMS)
public class Item {

    @Id
    @GeneratedValue
    @Column(name = ItemFields.ITEM_ID, unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = ItemFields.NAME)
    private String name;

    @Column(name = ItemFields.DESCRIPTION)
    private String description;

    @Column(name = ItemFields.INITIAL_PRICE)
    private BigDecimal initialPrice;

    @Column(name = ItemFields.RESERVE_PRICE)
    private BigDecimal reservePrice;

    @Column(name = ItemFields.START_DATE)
    private Date startDate;

    @Column(name = ItemFields.END_DATE)
    private Date endDate;

    @Column(name = ItemFields.STATE)
    private String state;

    @Column(name = ItemFields.APPROVAL_DATE_TIME)
    private Timestamp approvalDateTime;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ItemFields.ITEM_CATEGORY, joinColumns = {
	    @JoinColumn(name = ItemFields.ITEM_ID, nullable = false, updatable = false) }, inverseJoinColumns = {
		    @JoinColumn(name = CategoryFields.CATEGORY_ID, nullable = false, updatable = false) })
    private List<Category> categories;

    @OneToOne
    @JoinColumn(name = ItemFields.SUCCESSFUL_BID_ID)
    private Bid successfullBid;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Bid> bids;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ItemFields.ITEM_SELLER, joinColumns = {
	    @JoinColumn(name = ItemFields.ITEM_ID, nullable = false, updatable = false) }, inverseJoinColumns = {
		    @JoinColumn(name = UserFields.USER_ID, nullable = false, updatable = false) })
    private List<User> sellers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ItemFields.ITEM_BUYER, joinColumns = {
	    @JoinColumn(name = ItemFields.ITEM_ID, nullable = false, updatable = false) }, inverseJoinColumns = {
		    @JoinColumn(name = UserFields.USER_ID, nullable = false, updatable = false) })
    private List<User> buyers;

    public Item() {
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

    public List<Category> getCategories() {
	return categories;
    }

    public void setCategories(List<Category> categories) {
	this.categories = categories;
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

    public List<User> getSellers() {
	return sellers;
    }

    public void setSellers(List<User> sellers) {
	this.sellers = sellers;
    }

    public List<User> getBuyers() {
	return buyers;
    }

    public void setBuyers(List<User> buyers) {
	this.buyers = buyers;
    }

}
