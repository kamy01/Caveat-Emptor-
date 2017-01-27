package ro.fortech.caveatEmptor.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 6241298386176174535L;

	private Integer id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private Integer ranking;
	private boolean isAdmin;
	private AddressDto homeAddress;
	private AddressDto billingAddress;
	private AddressDto shippingAddress;
	private List<ItemDto> itemsSold = new ArrayList<>();
	private List<ItemDto> itemsBought = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public AddressDto getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(AddressDto homeAddress) {
		this.homeAddress = homeAddress;
	}

	public AddressDto getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(AddressDto billingAddress) {
		this.billingAddress = billingAddress;
	}

	public AddressDto getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(AddressDto shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<ItemDto> getItemsSold() {
		return itemsSold;
	}

	public void setItemsSold(List<ItemDto> itemsSold) {
		this.itemsSold = itemsSold;
	}

	public List<ItemDto> getItemsBought() {
		return itemsBought;
	}

	public void setItemsBought(List<ItemDto> itemsBought) {
		this.itemsBought = itemsBought;
	}

}
