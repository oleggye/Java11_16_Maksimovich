package by.tc.eq.bean;

import java.io.Serializable;
import java.util.List;

public class Ower implements Serializable {

	private static final long serialVersionUID = 1L;

	User user;
	List<Equipment> equipments;

	public Ower() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 22 * this.getUser().hashCode() + this.getEquipments().hashCode();
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
		Ower ower = (Ower) obj;

		if (!this.user.equals(ower.getUser())) {
			return false;
		}
		if (!this.getEquipments().equals(ower.getEquipments())) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {

		return "user: " + this.getUser() + "\n" + this.getEquipments();
	}

}
