package ro.fortech.caveatEmptor.integration.entities;

import static ro.fortech.caveatEmptor.integration.entities.fields.RegistrationFields.CREATION_DATE;
import static ro.fortech.caveatEmptor.integration.entities.fields.RegistrationFields.ENABLED;
import static ro.fortech.caveatEmptor.integration.entities.fields.RegistrationFields.EXPIRY_DATE;
import static ro.fortech.caveatEmptor.integration.entities.fields.RegistrationFields.REGISTRATIONS;
import static ro.fortech.caveatEmptor.integration.entities.fields.RegistrationFields.REGISTRATION_ID;
import static ro.fortech.caveatEmptor.integration.entities.fields.UserFields.USER_ID;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = REGISTRATIONS)
public class Registration {

	@Id
	@Column(name = REGISTRATION_ID)
	private String id;

	@Column(name = CREATION_DATE)
	private Timestamp creationDate;

	@Column(name = EXPIRY_DATE)
	private Timestamp expiryDate;

	@Column(name = ENABLED)
	private boolean enabled;

	@OneToOne
	@JoinColumn(name = USER_ID)
	private User user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Timestamp getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Timestamp expiryDate) {
		this.expiryDate = expiryDate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
