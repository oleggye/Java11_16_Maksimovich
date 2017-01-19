package by.training.notes;

import java.util.List;

import by.training.notes.bean.Node;
import by.training.notes.service.AnalyseService;
import by.training.notes.service.exception.ServiceException;
import by.training.notes.service.factory.ServiceFactory;

public class Main {

	private static String fileName = "C:\\Users\\hello\\workspace\\XmlAnalyser\\src\\by\\training\\notes\\notes.xml";

	public static void main(String[] args) {

		ServiceFactory sf = ServiceFactory.getInstance();

		AnalyseService service = sf.getAnalyseService();

		try {
			service.initialize(fileName);

			// Node s;
			// while ((s = service.next()) != null) {
			// System.out.println(s);
			// }
			// service.close();

			List<Node> list = service.getAll();

			for (Node node : list) {
				System.out.println(node);
			}
		} catch (ServiceException e) {
			// TODO:logging
			System.err.println(e);
		}
	}
}
