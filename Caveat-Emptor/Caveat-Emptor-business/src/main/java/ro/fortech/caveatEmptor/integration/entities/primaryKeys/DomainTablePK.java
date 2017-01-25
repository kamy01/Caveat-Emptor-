package ro.fortech.caveatEmptor.integration.entities.primaryKeys;

import java.io.Serializable;

public class DomainTablePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1216295674286453201L;
	private String domainCod;
	private String value;

	public DomainTablePK() {
	}

	public DomainTablePK(String domainCod, String value) {
		this.domainCod = domainCod;
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

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

}
