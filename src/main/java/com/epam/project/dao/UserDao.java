package com.epam.project.dao;

import com.epam.project.dao.exception.DaoException;
import com.epam.project.model.user.User;

import java.util.List;

public interface UserDao extends EntityDao<User, Long> {
    User find(String login, String pass) throws DaoException;
    List<User> findActivatedUsers() throws DaoException;
    List<User> findDeactivatedUsers() throws DaoException;
    void deactivate(Long id) throws DaoException;
}
