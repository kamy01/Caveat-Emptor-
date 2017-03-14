package ro.fortech.caveatEmptor.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class RegistrationDto implements Serializable {

	private static final long serialVersionUID = -2509222915496323362L;

	private String id;
	private Timestamp creationDate;
	private Timestamp expiryDate;
	private UserDto user;
	private boolean enabled;

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

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
