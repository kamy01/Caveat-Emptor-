package ro.fortech.caveatEmptor.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryDto implements Serializable {

    private static final long serialVersionUID = -2214525257891163483L;

    private Long id;
    private String name;
    private Long parentId;
    private List<CategoryDto> children;

    public CategoryDto() {
	initMembers();
    }

    private void initMembers() {
	children = new ArrayList<>();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Long getParentId() {
	return parentId;
    }

    public void setParentId(Long parent) {
	this.parentId = parent;
    }

    public List<CategoryDto> getChildren() {
	return children;
    }

    public void setChildren(List<CategoryDto> children) {
	this.children = children;
    }

}
