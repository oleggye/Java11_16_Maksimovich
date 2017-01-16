package by.tc.eq.service.impl;

import by.tc.eq.bean.User;
import by.tc.eq.dao.exeption.DAOException;
import by.tc.eq.dao.factory.DAOFactory;
import by.tc.eq.service.ClientService;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.util.ServiceInspector;

public class ClientServiceImpl implements ClientService {

	@Override
	public void registeration(User user) throws ServiceException {

		if (ServiceInspector.isUserObjectCorrect(user)) {

			DAOFactory df = DAOFactory.getInstance();

			try {

				df.getUserDAO().addUser(user);

			} catch (DAOException e) {
				throw new ServiceException("Ошибка при добавлении нового пользователя: " + user, e);
			}

		} else {
			throw new ServiceException("Введены некорректные данные пользователя: " + user);
		}
	}

	@Override
	public int singIn(String login, String password) throws ServiceException {

		if (ServiceInspector.isLoginAndPasswordCorrect(login, password)) {

			DAOFactory df = DAOFactory.getInstance();

			try {
				int user_id = df.getUserDAO().signInUser(login, password);
				return user_id;

			} catch (DAOException e) {
				throw new ServiceException("No such user", e);
			}

		} else {
			throw new ServiceException("Введены некорректные данные авторизации: login: " + "'" + login
					+ "', password: '" + password + "'");
		}
	}

	public void signOut() throws ServiceException {
		// TODO: user's logOut
	}

	@Override
	public void updateUserData(User user) throws ServiceException {

		/**
		 * добавлена проверка (user.getId() > 0), т.к. в данном методе мы
		 * пытаемся обновить данные существующего пользователя, то нам
		 * необходимо проверить на предварительную корректность поле id объекта
		 * user.
		 */

		if (ServiceInspector.isUserObjectCorrect(user) && user.getId() > 0) {

			DAOFactory df = DAOFactory.getInstance();

			try {

				df.getUserDAO().updateUser(user);

			} catch (DAOException e) {
				throw new ServiceException("Ошибка при обновлении данных пользователя: " + user, e);

			}
		} else {
			throw new ServiceException("Введены некорректные данные пользователя: " + user);
		}

	}

	@Override
	public void deleteUser(int user_id) throws ServiceException {

		if (user_id > 0) {

			DAOFactory df = DAOFactory.getInstance();

			try {
				df.getUserDAO().deleteUser(user_id);
			} catch (DAOException e) {
				throw new ServiceException("Ошибка при удалении пользователя: user_id: '" + user_id + "'", e);
			}
		} else {
			// TODO: возможно, нужно выбрать другое сообщение
			throw new ServiceException("Введены некорректные данные пользователя: user_id: '" + user_id + "'");
		}

	}

	@Override
	public User getUserInfo(int user_id) throws ServiceException {

		if (user_id > 0) {

			DAOFactory df = DAOFactory.getInstance();

			try {
				return df.getUserDAO().getUser(user_id);
			} catch (DAOException e) {
				throw new ServiceException(
						"Ошибка при попытке получить информацию о пользователе: user_id: '" + user_id + "'", e);
			}
		} else {
			// TODO: возможно, нужно выбрать другое сообщение
			throw new ServiceException("Введены некорректные данные пользователя: user_id: '" + user_id + "'");
		}

	}
}
