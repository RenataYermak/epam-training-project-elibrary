package com.epam.project.dao.impl;

import com.epam.project.dao.BookOrderDao;
import com.epam.project.dao.exception.DaoException;

import java.util.List;

public class BookOrderDaoImpl implements BookOrderDao {

    private static class Query {
        public static final String SELECT_BOOK_BY_STATUS =
                "select * from book_order where status=?";
        public static final String ORDER_BOOK =
                "insert into book_order (book_id, user_id, status, issue) values (?, ?, 'ORDERED', ?);";
    }

    private static class ColumnName {
        public static final String ID = "order_id";
        public static final String BOOK_ID = "book_id";
        public static final String USER_ID = "user_id";
        public static final String STATUS = "status";
        public static final String ISSUE = "issue";
        public static final String ORDERED_DATE = "ordered_date";
        public static final String BOOKED_DATE = "booked_date";
        public static final String RETURNED_DATE = "returned_date";
    }


    @Override
    public BookOrderDao find(Long id) throws DaoException {
        return null;
    }

    @Override
    public List<BookOrderDao> findAll() throws DaoException {
        return null;
    }

    @Override
    public BookOrderDao create(BookOrderDao entity) throws DaoException {
        return null;
    }

    @Override
    public BookOrderDao update(BookOrderDao entity) throws DaoException {
        return null;
    }

    @Override
    public void delete(Long id) throws DaoException {

    }
}
