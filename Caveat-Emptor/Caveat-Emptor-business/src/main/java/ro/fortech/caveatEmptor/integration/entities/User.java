package ro.fortech.caveatEmptor.integration.entities;

import static ro.fortech.caveatEmptor.integration.entities.fields.UserFields.BILLING_ADDRESS_ID;
import static ro.fortech.caveatEmptor.integration.entities.fields.UserFields.EMAIL;
import static ro.fortech.caveatEmptor.integration.entities.fields.UserFields.FIRST_NAME;
import static ro.fortech.caveatEmptor.integration.entities.fields.UserFields.HOME_ADDRESS_ID;
import static ro.fortech.caveatEmptor.integration.entities.fields.UserFields.IS_ADMIN;
import static ro.fortech.caveatEmptor.integration.entities.fields.UserFields.IS_ENABLED;
import static ro.fortech.caveatEmptor.integration.entities.fields.UserFields.LAST_NAME;
import static ro.fortech.caveatEmptor.integration.entities.fields.UserFields.PASSWORD;
import static ro.fortech.caveatEmptor.integration.entities.fields.UserFields.RANKING;
import static ro.fortech.caveatEmptor.integration.entities.fields.UserFields.SHIPPING_ADDRESS_ID;
import static ro.fortech.caveatEmptor.integration.entities.fields.UserFields.USERS;
import static ro.fortech.caveatEmptor.integration.entities.fields.UserFields.USER_ID;
import static ro.fortech.caveatEmptor.integration.entities.fields.UserFields.USER_NAME;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

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

    @Column(name = IS_ENABLED)
    private boolean enabled;

    @OneToOne
    @JoinColumn(name = HOME_ADDRESS_ID)
    private Address homeAddress;

    @OneToOne
    @JoinColumn(name = BILLING_ADDRESS_ID)
    private Address billingAddress;

    @OneToOne
    @JoinColumn(name = SHIPPING_ADDRESS_ID)
    private Address shippingAddress;

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private List<Item> itemsSold;

    @OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY)
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

    public void setUsername(String username) {
	this.username = username;
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

    public boolean isEnabled() {
	return enabled;
    }

    public void setEnabled(boolean enabled) {
	this.enabled = enabled;
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
