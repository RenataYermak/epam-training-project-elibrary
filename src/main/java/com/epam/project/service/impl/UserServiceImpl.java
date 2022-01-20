package com.epam.project.service.impl;

import com.epam.project.dao.exception.DaoException;
import com.epam.project.dao.impl.UserDaoImpl;
import com.epam.project.model.user.User;
import com.epam.project.dao.UserDao;
import com.epam.project.service.UserService;
import com.epam.project.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger();

    private final UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public User findUser(Long id) throws ServiceException {
        LOGGER.log(Level.INFO, "method find user by id");
        try {
            return userDao.find(id);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "exception in method find user by id: ", e);
            throw new ServiceException("Exception when find user by id: {}", e);
        }
    }

    @Override
    public User findUser(String login, String pass) throws ServiceException {
        LOGGER.log(Level.INFO, "method find user by login and pass");
        try {
            return userDao.find(login, pass);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "exception in method find user by login and pass: ", e);
            throw new ServiceException("Exception when find user by login and pass: {}", e);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        LOGGER.log(Level.INFO, "method findAll");
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "exception in method findAll: ", e);
            throw new ServiceException("Exception when find all users: {}", e);
        }
    }

    @Override
    public User create(User user) throws ServiceException {
        LOGGER.log(Level.INFO, "method create");
        try {
            return userDao.create(user);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "exception in method create: ", e);
            throw new ServiceException("Exception when create user: {}", e);
        }
    }

    @Override
    public User update(User user) throws ServiceException {
        LOGGER.log(Level.INFO, "method update");
        try {
            return userDao.update(user);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "exception in method update: ", e);
            throw new ServiceException("Exception when update user: {}", e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        LOGGER.log(Level.INFO, "method delete");
        try {
            userDao.delete(id);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "exception in method delete: ", e);
            throw new ServiceException("Exception when delete user: {}", e);
        }
    }

    @Override
    public List<User> findActivatedUsers() throws ServiceException {
        LOGGER.log(Level.INFO, "method findActivatedUsers");
        try {
            return userDao.findActivatedUsers();
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "exception in method findActivatedUsers: ", e);
            throw new ServiceException("Exception when find activated users: {}", e);
        }
    }

    @Override
    public List<User> findDeactivatedUsers() throws ServiceException {
        LOGGER.log(Level.INFO, "method findDeactivatedUsers");
        try {
            return userDao.findActivatedUsers();
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "exception in method findDeactivatedUsers: ", e);
            throw new ServiceException("Exception when find deactivated users: {}", e);
        }
    }

    @Override
    public void deactivate(Long id) throws ServiceException {
        LOGGER.log(Level.INFO, "method deactivate");
        try {
            userDao.deactivate(id);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "exception in method deactivate: ", e);
            throw new ServiceException("Exception when deactivate user: {}", e);
        }
    }
}
