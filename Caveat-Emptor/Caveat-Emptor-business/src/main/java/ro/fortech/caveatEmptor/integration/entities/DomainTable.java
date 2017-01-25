package ro.fortech.caveatEmptor.integration.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import ro.fortech.caveatEmptor.integration.entities.fields.DomainTableFields;
import ro.fortech.caveatEmptor.integration.entities.primaryKeys.DomainTablePK;

@Entity
@Table(name = DomainTableFields.DOMAIN_TABLE)
@IdClass(DomainTablePK.class)
public class DomainTable {

	@Id
	@Column(name = DomainTableFields.DOMAIN_COD)
	private String domainCod;

	@Id
	@Column(name = DomainTableFields.DOMAIN_VALUE)
	private String value;

	@Column(name = DomainTableFields.IS_ACTIVE)
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
