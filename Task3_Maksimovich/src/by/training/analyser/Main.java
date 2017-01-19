package by.training.analyser;

import java.util.List;

import by.training.analyser.bean.Node;
import by.training.analyser.service.AnalyseService;
import by.training.analyser.service.exception.ServiceException;
import by.training.analyser.service.factory.ServiceFactory;

public class Main {

	private static String fileName = "C:\\Users\\hello\\workspace\\XmlAnalyser\\src\\by\\training\\notes\\notes.xml";

	public static void main(String[] args) {

		ServiceFactory sf = ServiceFactory.getInstance();

		AnalyseService service = sf.getAnalyseService();

		try {

			// по одному узлу
			service.initialize(fileName);
			
			Node s;
			
			while ((s = service.next()) != null) {
				System.out.println(s);
			}
			service.close();

			
			
			// получить сразу все узлы
			service.initialize(fileName);

			List<Node> list = service.getAll();

			for (Node node : list) {
				System.out.println(node);
			}
			service.close();

		} catch (ServiceException e) {
			// TODO:logging
			System.err.println(e);
		}
	}
}
