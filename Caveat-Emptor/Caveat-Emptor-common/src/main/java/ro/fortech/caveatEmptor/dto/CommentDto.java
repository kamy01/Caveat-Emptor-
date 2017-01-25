package ro.fortech.caveatEmptor.dto;

import java.io.Serializable;
import java.sql.Date;

public class CommentDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3352649760868920077L;
	private Integer id;
	private String rating;
	private String text;
	private Date creationDate;
	private ItemDto item;
	private UserDto user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public ItemDto getItem() {
		return item;
	}

	public void setItem(ItemDto item) {
		this.item = item;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

}
