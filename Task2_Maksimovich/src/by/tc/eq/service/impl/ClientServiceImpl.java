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

		if (ServiceInspector.isUserObjectIncorrect(user)) {

			throw new ServiceException("Введены некорректные данные пользователя: " + user);

		} else {

			DAOFactory df = DAOFactory.getInstance();

			try {

				df.getUserDAO().addUser(user);

			} catch (DAOException e) {
				throw new ServiceException("Ошибка при добавлении нового пользователя: " + user, e);
			}
		}
	}

	@Override
	public int singIn(String login, String password) throws ServiceException {

		if (ServiceInspector.isLoginAndPasswordIncorrect(login, password)) {

			throw new ServiceException("Введены некорректные данные авторизации: login: " + "'" + login
					+ "', password: '" + password + "'");
		} else {

			DAOFactory df = DAOFactory.getInstance();

			try {
				int user_id = df.getUserDAO().signInUser(login, password);
				return user_id;

			} catch (DAOException e) {
				throw new ServiceException("No such user", e);
			}
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

		if (ServiceInspector.isUserObjectIncorrect(user) && user.getId() < 0) {
			throw new ServiceException("Введены некорректные данные пользователя: " + user);

		} else {

			DAOFactory df = DAOFactory.getInstance();

			try {

				df.getUserDAO().updateUser(user);

			} catch (DAOException e) {
				throw new ServiceException("Ошибка при обновлении данных пользователя: " + user, e);
			}
		}
	}

	@Override
	public void deleteUser(int userId) throws ServiceException {

		if (userId < 0) {
			throw new ServiceException("Введены некорректные данные пользователя: user_id: '" + userId + "'");
		} else {
			DAOFactory df = DAOFactory.getInstance();

			try {
				df.getUserDAO().deleteUser(userId);
			} catch (DAOException e) {
				throw new ServiceException("Ошибка при удалении пользователя: user_id: '" + userId + "'", e);
			}
		}
	}

	@Override
	public User getUserInfo(int userId) throws ServiceException {

		if (userId < 0) {
			throw new ServiceException("Введены некорректные данные пользователя: user_id: '" + userId + "'");
		} else {
			DAOFactory df = DAOFactory.getInstance();

			try {
				return df.getUserDAO().getUser(userId);
			} catch (DAOException e) {
				throw new ServiceException(
						"Ошибка при попытке получить информацию о пользователе: user_id: '" + userId + "'", e);
			}
		}
	}
}
