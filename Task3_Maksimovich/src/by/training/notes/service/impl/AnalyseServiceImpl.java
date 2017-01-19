package by.training.notes.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.training.notes.bean.Node;
import by.training.notes.bean.NodeType;
import by.training.notes.dao.XmlDAO;
import by.training.notes.dao.exception.DAOException;
import by.training.notes.dao.factory.DAOFactory;
import by.training.notes.service.AnalyseService;
import by.training.notes.service.exception.ServiceException;
import by.training.notes.service.util.Constants;

public class AnalyseServiceImpl implements AnalyseService {

	private XmlDAO xmlDAO;

	private String dataString = new String();

	@Override
	public void initialize(String fileName) throws ServiceException {

		DAOFactory df = DAOFactory.getInstance();
		xmlDAO = df.getXmlDAO();

		try {
			xmlDAO.initialize(fileName);
		} catch (DAOException e) {
			throw new ServiceException("Error during initialization ", e);
		}

	}

	@Override
	public Node next() throws ServiceException {

		if (xmlDAO == null) {
			throw new ServiceException("AnaliseService isn't initialized");
		}

		String nodeData = find();

		Node node = null;

		if (nodeData != null) {
			node = Analyser.analyseNodeData(nodeData);

		} else {
			try {
				xmlDAO.close();
			} catch (DAOException e) {
				throw new ServiceException("A problem occurred during resource releasing", e);
			}
		}

		return node;
	}

	@Override
	public List<Node> getAll() throws ServiceException {

		if (xmlDAO == null) {
			throw new ServiceException("AnaliseService isn't initialized");
		}

		List<Node> nodeList = new ArrayList<>();

		Node node = null;

		while ((node = next()) != null) {
			nodeList.add(node);
		}

		// т.к. мы прочитали все теги, то закроем
		close();

		return nodeList;
	}

	@Override
	public void close() throws ServiceException {

		try {
			if (xmlDAO != null)
				xmlDAO.close();

		} catch (DAOException e) {
			throw new ServiceException("A problem occurred while service closing", e);

		} finally {
			xmlDAO = null;
		}

	}
	
	private String getData() throws ServiceException {

		String line;

		try {
			line = xmlDAO.next();

		} catch (DAOException e) {
			throw new ServiceException("Can't get next node", e);
		}

		return line;
	}

	private String find() throws ServiceException {

		StringBuilder result = new StringBuilder();

		if (dataString.length() < 1) {
			dataString = getData();
		}

		while (dataString != null) {

			char[] buffer = dataString.toCharArray();

			for (int position = 0; position < buffer.length; position++) {

				if (buffer[position] == Constants.OPEN_BRACKET) {

					// если следующий символ за < это ?, то это служебная
					// инф-ция и мы её пропускаем
					if (buffer[position + 1] != Constants.QUESTION_MARK) {

						result.append(buffer[position]);

						position = position + 1;

						while (dataString != null) {
							if (position < buffer.length) {

								result.append(buffer[position]);

								if (buffer[position] == Constants.CLOSE_BRACKET) {
									int finishPos = position + 1;
									// вырезаем из строки найденный тег
									dataString = dataString.substring(finishPos, dataString.length());
									return result.toString();
								}
								position = position + 1;
							} else {
								// если строка данных закончилась и все
								// содержимое мы не нашли, то бирём новую порцию
								// данных
								dataString = getData();
								buffer = dataString.toCharArray();
								position = 0;
							}
						}
					} else {
						// пропускаем служебную информацию
						dataString = getData();
						break;
					}
				} else {

					if (Character.isAlphabetic(buffer[position])) {

						result.append(buffer[position]);

						position = position + 1;

						while (dataString != null) {
							if (position < buffer.length) {

								if (buffer[position] == Constants.OPEN_BRACKET) {
									int finishPos = position;
									// вырезаем из строки с данными найденный
									// текст
									dataString = dataString.substring(finishPos, dataString.length());
									return result.toString();
								}
								result.append(buffer[position]);
								position = position + 1;
							} else {
								// если строка данных закончилась и все
								// содержимое мы не нашли, то бирём новую порцию
								// данных
								dataString = getData();
								buffer = dataString.toCharArray();
							}
						}
					}
				}
			}
			// если строка с данными не содержала узлов, то бирём новую порцию
			// данных
			dataString = getData();
		}

		return dataString;
	}

	private static class Analyser {

		private static Node analyseNodeData(String data) {

			Node node = null;

			char[] charArr = data.toCharArray();

			if (charArr[0] == Constants.OPEN_BRACKET) {

				if (charArr[1] == Constants.SLASH) {
					node = constractNode(data, NodeType.CLOSE_TAG);
				} else {

					if (charArr[charArr.length - 2] == Constants.SLASH) {
						node = constractNode(data, NodeType.EMPTY_ELEMENT_TAG);
					} else
						node = constractNode(data, NodeType.OPEN_TAG);
				}

			} else {
				node = constractNode(data, NodeType.TEXT);
			}

			return node;
		}

		private static Node constractNode(String data, NodeType type) {

			Node node = new Node();
			node.setType(type);

			if (type == NodeType.OPEN_TAG) {

				Pattern pattern = Pattern.compile("<[a-zA-z]+\\s?");
				Matcher mather = pattern.matcher(data);

				mather.find();
				String openTagname = mather.group();
				openTagname = openTagname.trim();

				node.setName(openTagname + Constants.CLOSE_BRACKET);

				String content = "";
				pattern = Pattern.compile("\\b([a-zA-Z]+=[\"|']((\\w)+|\\s{1})[\"|'])");
				mather = pattern.matcher(data);

				while (mather.find()) {
					content = content + mather.group() + " ";
				}
				if (content.length() > 0) {
					content = content.trim();
					node.setContent(content);
				}
			} else {
				node.setName(data);
			}

			return node;
		}
	}

}
