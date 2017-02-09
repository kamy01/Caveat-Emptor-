package ro.fortech.caveatEmptor.integration.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ro.fortech.caveatEmptor.integration.entities.fields.CategoryFields;

@Entity
@Table(name = CategoryFields.CATEGORIES)
public class Category {

	@Id
	@Column(name = CategoryFields.CATEGORY_ID, unique = true, nullable = false, updatable = false)
	@GeneratedValue
	private Integer id;

	@Column(name = CategoryFields.NAME)
	private String name;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Category> children = new ArrayList<>();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = CategoryFields.PARENT_ID)
	private Category parent;

	@ManyToMany(mappedBy = "categories")
	private List<Item> items = new ArrayList<>();

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

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public List<Category> getChildren() {
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
	}

}
