package by.tc.eq.service.util;

import by.tc.eq.bean.Equipment;
import by.tc.eq.bean.User;

public class ServiceInspector {

	public static boolean isLoginAndPasswordIncorrect(String login, String password) {

		if (isDataStringIncorrect(login) || isDataStringIncorrect(password)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean isUserObjectIncorrect(User user) {

		if (user == null) {
			return true;
		}

		if (isDataStringIncorrect(user.getName()) || isDataStringIncorrect(user.getSurname())) {
			return true;
		}

		/**
		 * скидка задается не в % (процент), а вещественным числом, в промежутке
		 * [0;1]
		 */

		if (user.getDiscount() < 0 || user.getDiscount() > 1) {
			return true;
		}

		/**
		 * user.getStatus() - возвращает перечисление Status, если не null, то
		 * можно утверждать, что ссылка указывает на объект
		 */
		if (user.getStatus() == null)
			return true;

		return false;
	}

	/*
	 * public static boolean isGoodObjectCorrect(Good good) {
	 * 
	 * if (good == null) { return false; }
	 * 
	 * if (good.getId() < 0 || !isDataStringCorrect(good.getTitle())) return
	 * false; else { return true; } }
	 */

	public static boolean isEquipmentObjectIncorrect(Equipment equipment) {

		if (equipment == null) {
			return true;
		}

		if (isDataStringIncorrect(equipment.getTitle())) {
			return true;
		}

		if (isDataStringIncorrect(equipment.getDescription())) {
			return true;
		}

		// предполагается, что номера категорий будут начинаться с 1
		if (equipment.getCategory_id() < 1) {
			return true;
		}

		if (equipment.getPrice() < 0) {
			return true;
		}

		if (equipment.getQuantity() < 0) {
			return true;
		}

		return false;
	}

	public static boolean isIdArrayIncorrect(int[] arrayOfId) {

		if (arrayOfId == null) {
			return true;
		}

		if (arrayOfId.length == 0) {
			return true;
		}

		for (int elem : arrayOfId) {

			if (elem <= 0) {
				return true;
			}
		}
		return false;
	}

	private static boolean isDataStringIncorrect(String data) {

		if (data == null) {
			return true;
		}

		if (data.isEmpty()) {
			return true;
		}
		/**
		 * возможна такая ситуация, что строка состоит или содержит символы
		 * пробела, что недопустимо
		 */
		int dataLength = data.length();

		if (data.trim().length() != dataLength) {
			return true;
		}

		return false;
	}

}
