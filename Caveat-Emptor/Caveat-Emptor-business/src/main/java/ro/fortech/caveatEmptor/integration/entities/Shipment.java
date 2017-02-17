package ro.fortech.caveatEmptor.integration.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import static ro.fortech.caveatEmptor.integration.entities.fields.AddressFields.*;
import static ro.fortech.caveatEmptor.integration.entities.fields.ItemFields.*;
import static ro.fortech.caveatEmptor.integration.entities.fields.ShipmentFields.*;

@Entity
@Table(name = SHIPMENTS)
public class Shipment {

    @Id
    @GeneratedValue
    @Column(name = SHIPMENT_ID, unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = SHIPMENT_STATE)
    private String state;

    @Column(name = CREATION_DATE)
    private Date creationDate;

    @OneToOne
    @JoinColumn(name = BUYER_ID)
    private User buyer;

    @OneToOne
    @JoinColumn(name = SELLER_ID)
    private User seller;

    @OneToOne
    @JoinColumn(name = ITEM_ID)
    private Item item;

    @OneToOne
    @JoinColumn(name = ADDRESS_ID)
    private Address deliveryAddress;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
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
