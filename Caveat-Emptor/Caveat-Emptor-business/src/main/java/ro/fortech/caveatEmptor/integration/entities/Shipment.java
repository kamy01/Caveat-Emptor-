package ro.fortech.caveatEmptor.integration.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ro.fortech.caveatEmptor.integration.entities.fields.AddressFields;
import ro.fortech.caveatEmptor.integration.entities.fields.ItemFields;
import ro.fortech.caveatEmptor.integration.entities.fields.ShipmentFields;

@Entity
@Table(name = ShipmentFields.SHIPMENTS)
public class Shipment {

	@Id
	@GeneratedValue
	@Column(name = ShipmentFields.SHIPMENT_ID, unique = true, nullable = false, updatable = false)
	private Integer id;

	@Column(name = ShipmentFields.STATE)
	private String state;

	@Column(name = ShipmentFields.CREATION_DATE)
	private Date creationDate;

	@OneToOne
	@JoinColumn(name = ShipmentFields.BUYER_ID)
	private User buyer;

	@OneToOne
	@JoinColumn(name = ShipmentFields.SELLER_ID)
	private User seller;

	@OneToOne
	@JoinColumn(name = ItemFields.ITEM_ID)
	private Item item;

	@OneToOne
	@JoinColumn(name = AddressFields.ADDRESS_ID)
	private Address deliveryAddress;

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

}
