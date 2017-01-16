package by.tc.eq.bean;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String surname;
	private String login;
	private String password;
	private float discount;
	private Status status;

	public User() {
	}

	public User(int id, String name, String surname, String login, String password, float discount, Status status) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.discount = discount;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

		User other = (User) obj;

		if (id != other.id) {
			return false;
		}

		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else {
			if (!name.equals(other.surname)) {
				return false;
			}
		}

		if (surname == null) {
			if (other.surname != null) {
				return false;
			}
		} else {
			if (!surname.equals(other.surname)) {
				return false;
			}
		}

		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else {
			if (!login.equals(other.login)) {
				return false;
			}
		}

		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else {
			if (!password.equals(other.password)) {
				return false;
			}
		}

		if (discount != other.discount) {
			return false;
		}
		if (status != other.status) {
			return false;
		}
		return true;

	}

	@Override
	public int hashCode() {

		return (int) (23 * id + discount + (name == null ? 0 : name.hashCode())
				+ (surname == null ? 0 : surname.hashCode()) + (login == null ? 0 : login.hashCode())
				+ (password == null ? 0 : password.hashCode()) + (status == null ? 0 : status.hashCode()));
	}

	@Override
	public String toString() {
		return "id: '" + id + "', name: '" + name + "', surname: '" + surname + "', discount: '" + discount
				+ "', status: '" + status + "'";
	}

}
