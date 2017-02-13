package ro.fortech.caveatEmptor.integration.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static ro.fortech.caveatEmptor.integration.entities.fields.AddressFields.*;

@Entity
@Table(name = ADDRESSES)
public class Address {

    @Id
    @GeneratedValue
    @Column(name = ADDRESS_ID)
    private Long id;

    @Column(name = STREET)
    private String street;

    @Column(name = ZIPCODE)
    private String zipCode;

    @Column(name = CITY)
    private String city;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getStreet() {
	return street;
    }

    public void setStreet(String street) {
	this.street = street;
    }

    public String getZipCode() {
	return zipCode;
    }

    public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

}
