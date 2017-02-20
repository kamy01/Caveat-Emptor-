package ro.fortech.caveatEmptor.integration.entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import static ro.fortech.caveatEmptor.integration.entities.fields.CategoryFields.*;
import static ro.fortech.caveatEmptor.integration.entities.fields.ItemFields.*;
import static ro.fortech.caveatEmptor.integration.entities.fields.UserFields.*;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = ITEM_CATEGORY, joinColumns = {
	    @JoinColumn(name = ITEM_ID, nullable = false, updatable = false) }, inverseJoinColumns = {
		    @JoinColumn(name = CATEGORY_ID, nullable = false, updatable = false) })
    private List<Category> categories;

    @OneToOne
    @JoinColumn(name = SUCCESSFUL_BID_ID)
    private Bid successfullBid;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Bid> bids;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = ITEM_SELLER, joinColumns = {
	    @JoinColumn(name = ITEM_ID, nullable = false, updatable = false) }, inverseJoinColumns = {
		    @JoinColumn(name = USER_ID, nullable = false, updatable = false) })
    private List<User> sellers;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = ITEM_BUYER, joinColumns = {
	    @JoinColumn(name = ITEM_ID, nullable = false, updatable = false) }, inverseJoinColumns = {
		    @JoinColumn(name = USER_ID, nullable = false, updatable = false) })
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
