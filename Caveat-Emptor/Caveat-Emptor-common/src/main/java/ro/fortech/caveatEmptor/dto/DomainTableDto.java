package ro.fortech.caveatEmptor.dto;

import java.io.Serializable;

public class DomainTableDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1977103114951877849L;
	private String domainCod;
	private String value;
	private boolean isActive;

	public String getDomainCod() {
		return domainCod;
	}

	public void setDomainCod(String domainCod) {
		this.domainCod = domainCod;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
