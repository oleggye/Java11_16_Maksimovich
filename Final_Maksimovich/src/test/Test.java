package test;

import java.util.List;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class Test {
	public static void main(String[] args) throws ServiceException {

		ServiceFactory factory = ServiceFactory.getInstance();
		List<Competition> competitions = factory.getCompetitionService().obtainAvailableCompetitionsList(1, 8);
		for (Competition elem : competitions) {
			System.out.println(elem);
		}

	}

}
