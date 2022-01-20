package com.epam.project.dao.impl;

import com.epam.project.dao.UserDao;
import com.epam.project.dao.exception.DaoException;
import com.epam.project.model.user.Role;
import com.epam.project.model.user.Status;
import com.epam.project.model.user.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger();

    private static class Query {
        public static final String SELECT_USER_BY_ID = "select * from user where user_id=?";
        public static final String SELECT_USER_BY_LOGIN_AND_PASS =
                "select * from user where login=? and password=? and `status`='ACTIVATED'";
        public static final String SELECT_ALL_USERS = "select * from user";
        public static final String SELECT_ALL_ACTIVATED_USERS =
                "select * from user where `status`='ACTIVATED'";
        public static final String SELECT_ALL_DEACTIVATED_USERS =
                "select * from user where `status`='DEACTIVATED'";
        public static final String INSERT_USER =
                "insert into user(login, `password`, role) values(?, ?, ?);";
        public static final String UPDATE_USER = "update user set login=?, password=?, role=? where user_id=?";
        public static final String DEACTIVATE_USER =
                "update user set `status`='DEACTIVATED', deactivation_date=? where user_id=?";
        public static final String DELETE_USER = "delete from user where user_id=?";
    }

    private static class ColumnName {
        public static final String ID = "user_id";
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String ROLE = "role";
        public static final String STATUS = "status";
        public static final String ACTIVATION_DATE = "activation_date";
        public static final String DEACTIVATION_DATE = "deactivation_date";
    }

    @Override
    public User find(Long id) throws DaoException {
        LOGGER.log(Level.INFO, "method find by id");
        User user = null;
        PreparedStatement ps = getPrepareStatement(Query.SELECT_USER_BY_ID);
        try {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                constructUser(user, rs);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "exception in method find by id: ", e);
            throw new DaoException("Exception when find user by id: {}", e);
        } finally {
            closePrepareStatement(ps);
        }
        return user;
    }

    @Override
    public User find(String login, String pass) throws DaoException {
        LOGGER.log(Level.INFO, "method find by login and pass");
        User user = null;
        PreparedStatement ps = getPrepareStatement(Query.SELECT_USER_BY_LOGIN_AND_PASS);
        try {
            ps.setString(1, login);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                constructUser(user, rs);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "exception in method find by login and pass: ", e);
            throw new DaoException("Exception when find user by login and pass: {}", e);
        } finally {
            closePrepareStatement(ps);
        }
        return user;
    }

    @Override
    public List<User> findAll() throws DaoException {
        LOGGER.log(Level.INFO, "method findAll");
        List<User> users = new ArrayList<>();
        PreparedStatement ps = getPrepareStatement(Query.SELECT_ALL_USERS);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                constructUser(user, rs);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "exception in method findAll: ", e);
            throw new DaoException("Exception when findAll users: {}", e);
        } finally {
            closePrepareStatement(ps);
        }
        return users;
    }

    @Override
    public List<User> findActivatedUsers() throws DaoException {
        LOGGER.log(Level.INFO, "method findActivatedUsers");
        List<User> users = new ArrayList<>();
        PreparedStatement ps = getPrepareStatement(Query.SELECT_ALL_ACTIVATED_USERS);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                constructUser(user, rs);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "exception in method findActivatedUsers: ", e);
            throw new DaoException("Exception when findActivatedUsers users: {}", e);
        } finally {
            closePrepareStatement(ps);
        }
        return users;
    }

    @Override
    public List<User> findDeactivatedUsers() throws DaoException {
        LOGGER.log(Level.INFO, "method findDeactivatedUsers");
        List<User> users = new ArrayList<>();
        PreparedStatement ps = getPrepareStatement(Query.SELECT_ALL_DEACTIVATED_USERS);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                constructUser(user, rs);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "exception in method findDeactivatedUsers: ", e);
            throw new DaoException("Exception when findDeactivatedUsers users: {}", e);
        } finally {
            closePrepareStatement(ps);
        }
        return users;
    }

    @Override
    public User create(User user) throws DaoException {
        LOGGER.log(Level.INFO, "method create");
        PreparedStatement ps = getPrepareStatement(Query.INSERT_USER);
        try {
            constructPrepareStatement(ps, user);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getLong(1));
                User createdUser = find(user.getId());
                user.setStatus(createdUser.getStatus());
                user.setActivationDate(createdUser.getActivationDate());
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "exception in method create: ", e);
            throw new DaoException("Exception when create user: {}", e);
        } finally {
            closePrepareStatement(ps);
        }
        return user;
    }

    @Override
    public User update(User user) throws DaoException {
        LOGGER.log(Level.INFO, "method update");
        PreparedStatement ps = getPrepareStatement(Query.UPDATE_USER);
        try {
            constructPrepareStatement(ps, user);
            ps.setLong(4, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "exception in method update: ", e);
            throw new DaoException("Exception when update user: {}", e);
        } finally {
            closePrepareStatement(ps);
        }
        return user;
    }

    @Override
    public void deactivate(Long id) throws DaoException {
        LOGGER.log(Level.INFO, "method deactivate");
        PreparedStatement ps = getPrepareStatement(Query.DEACTIVATE_USER);
        try {
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "exception in method deactivate: ", e);
            throw new DaoException("Exception when deactivate user: {}", e);
        } finally {
            closePrepareStatement(ps);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        LOGGER.log(Level.INFO, "method delete");
        PreparedStatement ps = getPrepareStatement(Query.DELETE_USER);
        try {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "exception in method delete: ", e);
            throw new DaoException("Exception when delete user: {}", e);
        } finally {
            closePrepareStatement(ps);
        }
    }

    private void constructUser(User user, ResultSet rs) throws SQLException {
        user.setId(rs.getLong(ColumnName.ID));
        user.setLogin(rs.getString(ColumnName.LOGIN));
        user.setPassword(rs.getString(ColumnName.PASSWORD));
        user.setActivationDate(rs.getTimestamp(ColumnName.ACTIVATION_DATE));
        user.setDeactivationDate(rs.getTimestamp(ColumnName.DEACTIVATION_DATE));
        user.setRole(Role.valueOf(rs.getString(ColumnName.ROLE).toUpperCase()));
        user.setStatus(Status.valueOf(rs.getString(ColumnName.STATUS).toUpperCase()));
    }

    private void constructPrepareStatement(PreparedStatement ps, User entity) throws SQLException {
        ps.setString(1, entity.getLogin());
        ps.setString(2, entity.getPassword());
        ps.setString(3, entity.getRole().toString());
    }
}
