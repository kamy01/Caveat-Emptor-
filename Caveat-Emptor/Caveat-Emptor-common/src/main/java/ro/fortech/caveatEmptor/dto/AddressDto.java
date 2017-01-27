package ro.fortech.caveatEmptor.dto;

import java.io.Serializable;

public class AddressDto implements Serializable {

	private static final long serialVersionUID = -4123384527862950446L;

	private Integer id;
	private String street;
	private String zipCode;
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
