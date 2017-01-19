package by.training.notes.dao;

import by.training.notes.dao.exception.DAOException;

public interface XmlDAO {

	public void initialize(String fileName) throws DAOException;

	public String next() throws DAOException;
	
	public void close()throws DAOException;
}
