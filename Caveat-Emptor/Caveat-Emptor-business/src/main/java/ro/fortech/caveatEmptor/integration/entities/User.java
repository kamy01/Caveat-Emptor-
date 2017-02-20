package ro.fortech.caveatEmptor.integration.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import static ro.fortech.caveatEmptor.integration.entities.fields.UserFields.*;

@Entity
@Table(name = USERS)
public class User {

    @Id
    @GeneratedValue
    @Column(name = USER_ID, unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = FIRST_NAME, nullable = false)
    private String firstName;

    @Column(name = LAST_NAME, nullable = false)
    private String lastName;

    @Size(min = 3, message = "Username must be at least 3 character!")
    @Column(name = USER_NAME, unique = true, nullable = false, updatable = false)
    private String username;

    @Size(min = 5, message = "Password must be at least 3 character!")
    @Column(name = PASSWORD, nullable = false)
    private String password;

    @Email
    @Column(name = EMAIL, unique = true, nullable = false)
    private String email;

    @Column(name = RANKING)
    private Long ranking;

    @Column(name = IS_ADMIN)
    private boolean isAdmin;

    @OneToOne
    @JoinColumn(name = HOME_ADDRESS_ID)
    private Address homeAddress;

    @OneToOne
    @JoinColumn(name = BILLING_ADDRESS_ID)
    private Address billingAddress;

    @OneToOne
    @JoinColumn(name = SHIPPING_ADDRESS_ID)
    private Address shippingAddress;

    @ManyToMany(mappedBy = "sellers", fetch = FetchType.LAZY)
    private List<Item> itemsSold;

    @ManyToMany(mappedBy = "buyers", fetch = FetchType.LAZY)
    private List<Item> itemsBought;

    public User() {
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
	return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
	this.isAdmin = isAdmin;
    }

    public Address getHomeAddress() {
	return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
	this.homeAddress = homeAddress;
    }

    public Address getBillingAddress() {
	return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
	this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
	return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
	this.shippingAddress = shippingAddress;
    }

    public List<Item> getItemsSold() {
	return itemsSold;
    }

    public void setItemsSold(List<Item> itemsSold) {
	this.itemsSold = itemsSold;
    }

    public List<Item> getItemsBought() {
	return itemsBought;
    }

    public void setItemsBought(List<Item> itemsBought) {
	this.itemsBought = itemsBought;
    }

}
