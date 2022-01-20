/*
 * eLibrary ©
 * Renata Yermak
 */

package com.epam.project.dao.impl;

import com.epam.project.dao.BookDao;
import com.epam.project.dao.exception.DaoException;
import com.epam.project.model.book.Book;
import com.epam.project.model.book.Category;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private static final Logger LOGGER = LogManager.getLogger();

    private static class Query {
        public static final String SELECT_ALL_BOOKS = "select * from book";
        public static final String SELECT_BOOK_BY_ID = "select * from book where book_id=?";
        public static final String INSERT_BOOK =
                "insert into book (title, author, category, `date`, description, overall_rating, number) " +
                        "values(?, ?, ?, ?, ?, ?, ?);";
        public static final String UPDATE_BOOK =
                "update book set title=?, author=?, category=?, `date`=?, description=?, overall_rating=?, number=? " +
                        "where book_id=?";
        public static final String DELETE_BOOK = "delete from book where book_id=?";
    }

    private static class ColumnName {
        public static final String ID = "book_id";
        public static final String TITLE = "title";
        public static final String AUTHOR = "author";
        public static final String CATEGORY = "category";
        public static final String DATE = "date";
        public static final String DESCRIPTION = "description";
        public static final String OVERALL_RATING = "overall_rating";
        public static final String NUMBER = "number";
    }

    @Override
    public Book find(Long id) throws DaoException {
        LOGGER.log(Level.INFO, "method find");
        Book book = null;
        PreparedStatement ps = getPrepareStatement(Query.SELECT_BOOK_BY_ID);
        try {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book = new Book();
                constructBook(book, rs);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "exception in method find: ", e);
            throw new DaoException("Exception when find book: {}", e);
        } finally {
            closePrepareStatement(ps);
        }
        return book;
    }

    @Override
    public List<Book> findAll() throws DaoException {
        LOGGER.log(Level.INFO, "method findAll");
        List<Book> books = new ArrayList<>();
        PreparedStatement ps = getPrepareStatement(Query.SELECT_ALL_BOOKS);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                constructBook(book, rs);
                books.add(book);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "exception in method findAll: ", e);
            throw new DaoException("Exception when findAll books: {}", e);
        } finally {
            closePrepareStatement(ps);
        }
        return books;
    }

    @Override
    public Book create(Book entity) throws DaoException {
        LOGGER.log(Level.INFO, "method create");
        PreparedStatement ps = getPrepareStatement(Query.INSERT_BOOK);
        try {
            constructPrepareStatement(ps, entity);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                entity.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "exception in method create: ", e);
            throw new DaoException("Exception when create book: {}", e);
        } finally {
            closePrepareStatement(ps);
        }
        return entity;
    }

    @Override
    public Book update(Book entity) throws DaoException {
        LOGGER.log(Level.INFO, "method update");
        PreparedStatement ps = getPrepareStatement(Query.UPDATE_BOOK);
        try {
            constructPrepareStatement(ps, entity);
            ps.setLong(8, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "exception in method update: ", e);
            throw new DaoException("Exception when update book: {}", e);
        } finally {
            closePrepareStatement(ps);
        }
        return entity;
    }

    @Override
    public void delete(Long id) throws DaoException {
        LOGGER.log(Level.INFO, "method delete");
        PreparedStatement ps = getPrepareStatement(Query.DELETE_BOOK);
        try {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "exception in method delete: ", e);
            throw new DaoException("Exception when delete book: {}", e);
        } finally {
            closePrepareStatement(ps);
        }
    }

    private void constructBook(Book book, ResultSet rs) throws SQLException {
        book.setId(rs.getLong(ColumnName.ID));
        book.setTitle(rs.getString(ColumnName.TITLE));
        book.setAuthor(rs.getString(ColumnName.AUTHOR));
        book.setDate(rs.getDate(ColumnName.DATE));
        book.setDescription(rs.getString(ColumnName.DESCRIPTION));
        book.setOverallRating(returnResultSetDoubleValue(rs, ColumnName.OVERALL_RATING));
        book.setNumber(returnResultSetIntValue(rs, ColumnName.NUMBER));
        book.setCategory(Category.valueOf(
                rs.getString(ColumnName.CATEGORY)
                        .toUpperCase()));
    }

    private void constructPrepareStatement(PreparedStatement ps, Book entity) throws SQLException {
        ps.setString(1, entity.getTitle());
        ps.setString(2, entity.getAuthor());
        ps.setString(3, entity.getCategory().toString());
        ps.setDate(4, entity.getDate());
        ps.setString(5, entity.getDescription());
        setDoubleOrNull(ps, 6, entity.getOverallRating());
        setIntOrNull(ps, 7, entity.getNumber());
    }
}
