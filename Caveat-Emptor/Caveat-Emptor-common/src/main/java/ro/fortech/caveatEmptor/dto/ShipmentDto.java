package ro.fortech.caveatEmptor.dto;

import java.io.Serializable;
import java.sql.Date;

public class ShipmentDto implements Serializable {

	private static final long serialVersionUID = 1556648722990094125L;

	private Integer id;
	private String state;
	private Date creationDate;
	private UserDto buyer;
	private UserDto seller;
	private ItemDto item;
	private AddressDto deliveryAddress;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public UserDto getBuyer() {
		return buyer;
	}

	public void setBuyer(UserDto buyer) {
		this.buyer = buyer;
	}

	public UserDto getSeller() {
		return seller;
	}

	public void setSeller(UserDto seller) {
		this.seller = seller;
	}

	public ItemDto getItem() {
		return item;
	}

	public void setItem(ItemDto item) {
		this.item = item;
	}

	public AddressDto getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(AddressDto deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

}
