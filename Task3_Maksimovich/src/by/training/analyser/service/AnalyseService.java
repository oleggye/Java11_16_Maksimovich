package by.training.analyser.service;

import java.util.List;

import by.training.analyser.bean.Node;
import by.training.analyser.service.exception.ServiceException;

public interface AnalyseService {

	public void initialize(String fileName) throws ServiceException;

	public List<Node> getAll() throws ServiceException ;

	public Node next() throws ServiceException ;
	
	public void close()throws ServiceException ;

}
