package by.training.analyser.dao.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import by.training.analyser.dao.XmlDAO;
import by.training.analyser.dao.exception.DAOException;

public class XmlDAOImpl implements XmlDAO {

	private String filePath;

	private BufferedReader buffReader;

	@Override
	public void initialize(String fileName) throws DAOException {

		try {

			buffReader = new BufferedReader(new FileReader(fileName));

		} catch (FileNotFoundException e) {
			throw new DAOException("File: '" + fileName + "' doesn't exist", e);
		}
	}

	@Override
	public String next() throws DAOException {

		if (buffReader == null) {
			throw new DAOException("XmlDAO isn't initialized");
		}

		String nextLine = null;

		try {

			// считываем следующую строку
			nextLine = buffReader.readLine();

		} catch (FileNotFoundException e) {
			throw new DAOException("File: '" + filePath + "' doesn't exist", e);

		} catch (IOException e) {
			throw new DAOException("Can't read from file: '" + filePath + "'", e);
		}

		return nextLine;
	}

	@Override
	public void close() throws DAOException {
		try {
			if (buffReader != null) {
				buffReader.close();
			}
		} catch (IOException e) {
			throw new DAOException("Can't close buffReader", e);
		} finally {
			buffReader = null;
		}
	}
}
