package by.training.analyser.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.training.analyser.bean.Node;
import by.training.analyser.bean.NodeType;
import by.training.analyser.dao.XmlDAO;
import by.training.analyser.dao.exception.DAOException;
import by.training.analyser.dao.factory.DAOFactory;
import by.training.analyser.service.AnalyseService;
import by.training.analyser.service.exception.ServiceException;
import by.training.analyser.service.util.CharConstants;

public class AnalyseServiceImpl implements AnalyseService {

	private static final String UNINIT_SERVICE_ER_MESSAGE = "AnaliseService is uninitialized";
	private static final String CLOSE_RESOURCE_ER_MESSAGE = "A problem occurred during resource releasing";

	private XmlDAO xmlDAO;

	private String dataString;

	@Override
	public void initialize(String fileName) throws ServiceException {

		DAOFactory df = DAOFactory.getInstance();
		xmlDAO = df.getXmlDAO();

		try {
			xmlDAO.initialize(fileName);
			dataString = new String();
		} catch (DAOException e) {
			throw new ServiceException("Error during initialization ", e);
		}

	}

	@Override
	public Node next() throws ServiceException {

		if (xmlDAO == null) {
			throw new ServiceException(UNINIT_SERVICE_ER_MESSAGE);
		}

		String nodeData = find();

		Node node = null;

		if (nodeData != null) {
			node = Analyser.analyseNodeData(nodeData);

			// т.е. данных нет, то на всякий случай закрываем
		} else {
			try {
				xmlDAO.close();
			} catch (DAOException e) {
				throw new ServiceException(CLOSE_RESOURCE_ER_MESSAGE, e);
			}
		}

		return node;
	}

	@Override
	public List<Node> getAll() throws ServiceException {

		if (xmlDAO == null) {
			throw new ServiceException(UNINIT_SERVICE_ER_MESSAGE);
		}

		List<Node> nodeList = new ArrayList<>();

		Node node = null;

		while ((node = next()) != null) {
			nodeList.add(node);
		}

		return nodeList;
	}

	@Override
	public void close() throws ServiceException {

		try {
			if (xmlDAO != null)
				xmlDAO.close();

		} catch (DAOException e) {
			throw new ServiceException(CLOSE_RESOURCE_ER_MESSAGE, e);

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

		if (dataString.isEmpty()) {
			dataString = getData();
		}

		while (dataString != null) {

			char[] buffer = dataString.toCharArray();

			for (int position = 0; position < buffer.length; position++) {

				if (buffer[position] == CharConstants.OPEN_BRACKET) {

					// если следующий символ за < это ?, то это служебная
					// инф-ция и мы её пропускаем
					if (buffer[position + 1] != CharConstants.QUESTION_MARK) {

						result.append(buffer[position]);

						position = position + 1;

						while (dataString != null) {
							if (position < buffer.length) {

								result.append(buffer[position]);

								if (buffer[position] == CharConstants.CLOSE_BRACKET) {
									int endPos = position + 1;
									// вырезаем из строки найденный тег
									dataString = dataString.substring(endPos, dataString.length());
									return result.toString();
								}
								position = position + 1;
							} else {
								// если данные закончились и все
								// содержимое мы не нашли, то бирём новую порцию
								// данных
								dataString = getData();
								buffer = dataString.toCharArray();
								position = 0;
							}
						}
					} else {
						// пропускаем служебную информацию
						break;
					}
				} else {

					if (Character.isAlphabetic(buffer[position])) {

						result.append(buffer[position]);

						position = position + 1;

						while (dataString != null) {
							if (position < buffer.length) {

								if (buffer[position] == CharConstants.OPEN_BRACKET) {
									int endPosition = position;
									// вырезаем из строки с данными найденный
									// текст
									dataString = dataString.substring(endPosition, dataString.length());
									return result.toString();
								}
								result.append(buffer[position]);
								position = position + 1;
							} else {
								// если данные закончились и все
								// содержимое мы не нашли, то бирём новую порцию
								// данных
								dataString = getData();
								buffer = dataString.toCharArray();
								position = 0;
							}
						}
					}
				}
			}
			// если строка с данными не содержала узлов, то бирём новую порцию
			// данных
			dataString = getData();
		}

		return null;
	}

	private static class Analyser {

		private static final String REG_EXP_FOR_OPEN_TAG = "<[a-zA-Z]+\\s?";
		private static final String REG_EXP_FOR_ATTRIBUTES = "\\b([a-zA-Z]+=[\"|']((\\w)+|\\s{1})[\"|'])";

		private static Node analyseNodeData(String data) {

			Node node = null;

			char[] charArr = data.toCharArray();

			if (charArr[0] == CharConstants.OPEN_BRACKET) {

				if (charArr[1] == CharConstants.SLASH) {
					node = constractNode(data, NodeType.CLOSE_TAG);
				} else {

					if (charArr[charArr.length - 2] == CharConstants.SLASH) {
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

				Pattern pattern = Pattern.compile(REG_EXP_FOR_OPEN_TAG);
				Matcher mather = pattern.matcher(data);

				mather.find();
				String openTagname = mather.group();
				openTagname = openTagname.trim();

				node.setName(openTagname + CharConstants.CLOSE_BRACKET);

				String content = "";
				pattern = Pattern.compile(REG_EXP_FOR_ATTRIBUTES);
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
