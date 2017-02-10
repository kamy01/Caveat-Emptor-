package ro.fortech.caveatEmptor.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryDto implements Serializable {

	private static final long serialVersionUID = -2214525257891163483L;

	private Integer id;
	private String name;
	private Integer parentId;
	private List<CategoryDto> children = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parent) {
		this.parentId = parent;
	}

	public List<CategoryDto> getChildren() {
		return children;
	}

	public void setChildren(List<CategoryDto> children) {
		this.children = children;
	}

}
