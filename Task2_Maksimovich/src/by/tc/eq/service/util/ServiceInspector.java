package by.tc.eq.service.util;

import by.tc.eq.bean.Equipment;
import by.tc.eq.bean.User;

public class ServiceInspector {

	public static boolean isLoginAndPasswordCorrect(String login, String password) {

		if (!isDataStringCorrect(login) || !isDataStringCorrect(password)) {
			return false;
		} else {
			return true;
		}

	}

	public static boolean isUserObjectCorrect(User user) {

		if (user == null) {
			return false;
		}

		if (!isDataStringCorrect(user.getName()) || !isDataStringCorrect(user.getSurname())) {
			return false;
		}

		/**
		 * скидка задается не в % (процент), а вещественным числом, в промежутке
		 * [0;1]
		 */

		if (user.getDiscount() < 0 || user.getDiscount() > 1) {
			return false;
		}

		/**
		 * user.getStatus() - возвращает перечисление Status, если не null, то
		 * можно утверждать, что ссылка указывает на объект
		 */
		if (user.getStatus() == null)
			return false;

		return true;
	}

	/*public static boolean isGoodObjectCorrect(Good good) {

		if (good == null) {
			return false;
		}

		if (good.getId() < 0 || !isDataStringCorrect(good.getTitle()))
			return false;
		else {
			return true;
		}
	}*/

	public static boolean isEquipmentObjectCorrect(Equipment equipment) {

		if (equipment == null) {
			return false;
		}

		if (!isDataStringCorrect(equipment.getTitle())) {
			return false;
		}

		if (!isDataStringCorrect(equipment.getDescription())) {
			return false;
		}

		// предполагается, что номера категорий будут начинаться с 1
		if (equipment.getCategory_id() < 1) {
			return false;
		}

		if (equipment.getPrice() < 0) {
			return false;
		}

		if (equipment.getQuantity() < 0) {
			return false;
		}

		return true;
	}

	public static boolean isIdArrayCorrect(int[] id_array) {

		if (id_array == null) {
			return false;
		}

		if (id_array.length == 0) {
			return false;
		}

		for (int elem : id_array) {

			if (elem <= 0) {
				return false;
			}
		}
		return true;
	}

	private static boolean isDataStringCorrect(String data) {

		if (data == null) {
			return false;
		}

		if (data.isEmpty()) {
			return false;
		}
		/**
		 * возможна такая ситуация, что строка состоит или содержит символы
		 * пробела, что недопустимо
		 */
		int dataLength = data.length();

		if (data.trim().length() != dataLength) {
			return false;
		}

		return true;
	}

}
