package ro.fortech.caveatEmptor.dto;

import java.io.Serializable;

public class ItemCriteriaDto implements Serializable {

    private static final long serialVersionUID = 3451450733420250814L;

    private Long id;
    private String option;

    public Long getId() {
	return id;
    }

    public void setId(Long userId) {
	this.id = userId;
    }

    public String getOption() {
	return option;
    }

    public void setOption(String option) {
	this.option = option;
    }

}
