package ro.fortech.caveatEmptor.integration.entities;

import static ro.fortech.caveatEmptor.integration.entities.fields.CategoryFields.CATEGORIES;
import static ro.fortech.caveatEmptor.integration.entities.fields.CategoryFields.CATEGORY_ID;
import static ro.fortech.caveatEmptor.integration.entities.fields.CategoryFields.CATEGORY_NAME;
import static ro.fortech.caveatEmptor.integration.entities.fields.CategoryFields.PARENT_ID;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = CATEGORIES)
public class Category {

    @Id
    @Column(name = CATEGORY_ID, unique = true, nullable = false, updatable = false)
    @GeneratedValue
    private Long id;

    @Column(name = CATEGORY_NAME)
    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> children;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = PARENT_ID)
    private Category parent;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Item> items;

    public Category() {
	initMembers();
    }

    private void initMembers() {
	children = new ArrayList<>();
	items = new ArrayList<>();
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
