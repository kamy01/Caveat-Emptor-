package ro.fortech.caveatEmptor.integration.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import ro.fortech.caveatEmptor.integration.entities.fields.AddressFields;

@Entity
@Table(name = AddressFields.ADDRESSES)
public class Address {

	@Id
	@GeneratedValue
	@Column(name = AddressFields.ADDRESS_ID)
	private Integer id;

	@Column(name = AddressFields.STREET)
	private String street;

	@Column(name = AddressFields.ZIPCODE)
	private String zipCode;

	@Column(name = AddressFields.CITY)
	private String city;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
