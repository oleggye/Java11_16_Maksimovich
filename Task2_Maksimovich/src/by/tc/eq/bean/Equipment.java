package by.tc.eq.bean;

import java.io.Serializable;

public class Equipment implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int category_id;
	private String title;
	private float price;
	private int quantity;
	private String description;

	public Equipment() {
	}
	
	public Equipment(int id, int category_id, String title, float price, int quantity, String description) {
		super();
		this.id = id;
		this.category_id = category_id;
		this.title = title;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Equipment other = (Equipment) obj;
		if (id != other.id) {
			return false;
		}
		if (category_id != other.category_id) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		if (price != other.price) {
			return false;
		}
		if (quantity != other.quantity) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else {
			if (!description.equals(other.description)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public int hashCode() {

		return (int) (23 * id + price + quantity + category_id + (title == null ? 0 : title.hashCode())
				+ (description == null ? 0 : description.hashCode()));
	}

	@Override
	public String toString() {

		return "id: '" + id + "', category_id: '" + category_id + "', title: '" + title + "', price: '" + price
				+ "', quantity: '" + quantity + "', description: '" + description + "'";
	}
}
