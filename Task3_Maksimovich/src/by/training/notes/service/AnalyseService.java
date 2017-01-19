package by.training.notes.service;

import java.util.List;

import by.training.notes.bean.Node;
import by.training.notes.service.exception.ServiceException;

public interface AnalyseService {

	public void initialize(String fileName) throws ServiceException;

	public List<Node> getAll() throws ServiceException ;

	public Node next() throws ServiceException ;
	
	public void close()throws ServiceException ;

}
