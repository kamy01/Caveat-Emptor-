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

import ro.fortech.caveatEmptor.integration.entities.fields.BidFields;
import ro.fortech.caveatEmptor.integration.entities.fields.ItemFields;
import ro.fortech.caveatEmptor.integration.entities.fields.UserFields;

@Entity
@Table(name = BidFields.BIDS)
public class Bid {

	@Id
	@Column(name = BidFields.BID_ID, unique = true, nullable = false, updatable = false)
	@GeneratedValue
	private Long id;

	@Column(name = BidFields.AMOUNT)
	private BigDecimal amount;

	@Column(name = BidFields.CREATION_DATE)
	private Date creationDate;

	@OneToOne
	@JoinColumn(name = UserFields.USER_ID)
	private User bidder;

	@ManyToOne
	@JoinColumn(name = ItemFields.ITEM_ID)
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
