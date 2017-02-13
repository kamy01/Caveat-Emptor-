package ro.fortech.caveatEmptor.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class BidDto implements Serializable {

	private static final long serialVersionUID = 1646430613271566662L;

	private Long id;
	private BigDecimal amount;
	private Date creationDate;
	private UserDto bidder;
	private ItemDto item;

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

	public UserDto getBidder() {
		return bidder;
	}

	public void setBidder(UserDto bidder) {
		this.bidder = bidder;
	}

	public ItemDto getItem() {
		return item;
	}

	public void setItem(ItemDto item) {
		this.item = item;
	}

}
