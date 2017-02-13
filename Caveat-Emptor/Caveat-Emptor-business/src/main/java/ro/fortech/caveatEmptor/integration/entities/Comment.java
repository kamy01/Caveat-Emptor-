package ro.fortech.caveatEmptor.integration.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ro.fortech.caveatEmptor.integration.entities.fields.CommentFields;
import ro.fortech.caveatEmptor.integration.entities.fields.ItemFields;
import ro.fortech.caveatEmptor.integration.entities.fields.UserFields;

@Entity
@Table(name = CommentFields.COMMENTS)
public class Comment {

	@Id
	@GeneratedValue
	@Column(name = CommentFields.COMMENT_ID, unique = true, nullable = false, updatable = false)
	private Long id;

	@Column(name = CommentFields.RATING)
	private String rating;

	@Column(name = CommentFields.TEXT)
	private String text;

	@Column(name = CommentFields.CREATION_DATE)
	private Date creationDate;

	@OneToOne
	@JoinColumn(name = ItemFields.ITEM_ID)
	private Item item;

	@OneToOne
	@JoinColumn(name = UserFields.USER_ID)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
