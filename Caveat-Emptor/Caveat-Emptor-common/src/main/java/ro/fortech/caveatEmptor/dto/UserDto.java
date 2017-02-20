package ro.fortech.caveatEmptor.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDto implements Serializable {

    private static final long serialVersionUID = 6241298386176174535L;

    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private Long ranking;
    private boolean admin;
    private boolean enabled;
    private AddressDto homeAddress;
    private AddressDto billingAddress;
    private AddressDto shippingAddress;
    private List<ItemDto> itemsSold;
    private List<ItemDto> itemsBought;

    public UserDto() {
	initMembers();
    }

    private void initMembers() {
	itemsSold = new ArrayList<>();
	itemsBought = new ArrayList<>();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
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

    public String getFullName() {
	return String.join(" ", this.firstName, this.lastName);
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

    public Long getRanking() {
	return ranking;
    }

    public void setRanking(Long ranking) {
	this.ranking = ranking;
    }

    public boolean isAdmin() {
	return admin;
    }

    public void setAdmin(boolean isAdmin) {
	this.admin = isAdmin;
    }

    public boolean isEnabled() {
	return enabled;
    }

    public void setEnabled(boolean isEnabled) {
	this.enabled = isEnabled;
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
