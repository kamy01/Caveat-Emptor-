package ro.fortech.caveatEmptor.integration.entities;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import static ro.fortech.caveatEmptor.integration.entities.fields.BidFields.*;
import static ro.fortech.caveatEmptor.integration.entities.fields.ItemFields.*;
import static ro.fortech.caveatEmptor.integration.entities.fields.UserFields.*;

@Entity
@Table(name = BIDS)
public class Bid {

    @Id
    @Column(name = BID_ID, unique = true, nullable = false, updatable = false)
    @GeneratedValue
    private Long id;

    @Column(name = AMOUNT)
    private BigDecimal amount;

    @Column(name = CREATION_DATE)
    private Date creationDate;

    @OneToOne
    @JoinColumn(name = USER_ID)
    private User bidder;

    @ManyToOne
    @JoinColumn(name = ITEM_ID)
    private Item item;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public BigDecimal getAmount() {
	return amount;
    }

    public void setAmount(BigDecimal amount) {
	this.amount = amount;
    }

    public Date getCreationDate() {
	return creationDate;
    }

    public void setCreationDate(Date creationDate) {
	this.creationDate = creationDate;
    }

    public User getBidder() {
	return bidder;
    }

    public void setBidder(User bidder) {
	this.bidder = bidder;
    }

    public Item getItem() {
	return item;
    }

    public void setItem(Item item) {
	this.item = item;
    }

}
