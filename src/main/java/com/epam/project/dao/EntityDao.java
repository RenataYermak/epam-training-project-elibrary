package com.epam.project.dao;

import com.epam.project.dao.config.ConnectionPool;
import com.epam.project.dao.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public interface EntityDao<E, K> {
    Logger LOGGER = LogManager.getLogger();
    ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    E find(K id) throws DaoException;
    List<E> findAll() throws DaoException;
    E create(E entity) throws DaoException;
    E update(E entity) throws DaoException;
    void delete(K id) throws DaoException;

    default Connection getConnection() {
        return connectionPool.getConnection();
    }

    default void returnConnection(Connection connection) {
        connectionPool.returnConnection(connection);
    }

    default PreparedStatement getPrepareStatement(String sql) {
        LOGGER.log(Level.INFO, "get prepare statement");
        PreparedStatement ps = null;
        try {
            ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "exception in method getPrepareStatement: ", e);
        }
        return ps;
    }

    default void closePrepareStatement(PreparedStatement ps) {
        LOGGER.log(Level.INFO, "close prepare statement");
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "exception in method closePrepareStatement: ", e);
        }
    }

    default void setIntOrNull(PreparedStatement ps, int parameterIndex, Integer value) throws SQLException {
        if (value == null) {
            ps.setNull(parameterIndex, Types.INTEGER);
        } else {
            ps.setInt(parameterIndex, value);
        }
    }

    default void setDoubleOrNull(PreparedStatement ps, int parameterIndex, Double value) throws SQLException {
        if (value == null) {
            ps.setNull(parameterIndex, Types.DOUBLE);
        } else {
            ps.setDouble(parameterIndex, value);
        }
    }

    default Double returnResultSetDoubleValue(ResultSet rs, String columnName) throws SQLException {
        return rs.getObject(columnName) != null ? rs.getDouble(columnName) : null;
    }

    default Integer returnResultSetIntValue(ResultSet rs, String columnName) throws SQLException {
        return rs.getObject(columnName) != null ? rs.getInt(columnName) : null;
    }
}
