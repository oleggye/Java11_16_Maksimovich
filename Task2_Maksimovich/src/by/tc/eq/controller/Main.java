package by.tc.eq.controller;

import by.tc.eq.view.Viewer;

public class Main {

	private static Controller controller = new Controller();

	public static void main(String[] args) {

		/** в самих командах есть описание о принимаемых параметрах */

		// User

		Viewer.showResponse(controller.executeTask("ADD_USER;Вася;Пупкин;superVasya1;qwerty;"));

		Viewer.showResponse(controller.executeTask("SIGN_IN;superVasya;qwerty"));

		Viewer.showResponse(controller.executeTask("SIGN_OUT"));

		Viewer.showResponse(controller.executeTask("UPDATE_USER_DATA;9;Вася;Пупкин;superVasya1;qwerty;0.22;Available"));

		Viewer.showResponse(controller.executeTask("DELETE_USER;9"));

		// Equipment

		Viewer.showResponse(controller.executeTask("ADD_EQUIPMENT;3;Шерстяные носки;3.55f;12;Супер теплые носки"));

		Viewer.showResponse(
				controller.executeTask("UPDATE_EQUIPMENT;10;3;Шерстяные носки;3.55f;10;Ультра теплые носки"));

		Viewer.showResponse(controller.executeTask("DELETE_EQUIPMENT;8"));

		Viewer.showResponse(controller.executeTask("GENERATE_USER_REPORT;"));

		// Rent

		Viewer.showResponse(controller.executeTask("RENT_EQUIPMENT;3;2;1;10"));

		Viewer.showResponse(controller.executeTask("RETURN_EQUIPMENT;3;1;2;10"));

		// Report

		Viewer.showResponse(controller.executeTask("GENERATE_AVAILABLE_EQUIPMENT_REPORT;"));

		Viewer.showResponse(controller.executeTask("GENERATE_RENTED_EQUIPMENT_REPORT;"));

		Viewer.showResponse(controller.executeTask("GENERATE_OWER_REPORT;"));

		Viewer.showResponse(controller.executeTask("GENERATE_USER_REPORT;"));

	}
}
