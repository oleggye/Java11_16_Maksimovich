package by.training.analyser.dao;

import by.training.analyser.dao.exception.DAOException;

public interface XmlDAO {

	public void initialize(String filePath) throws DAOException;

	public String next() throws DAOException;
	
	public void close()throws DAOException;
}
