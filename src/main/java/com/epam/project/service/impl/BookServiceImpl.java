package com.epam.project.service.impl;

import com.epam.project.dao.BookDao;
import com.epam.project.dao.BookOrderDao;
import com.epam.project.dao.BookStatisticDao;
import com.epam.project.dao.exception.DaoException;
import com.epam.project.dao.impl.BookDaoImpl;
import com.epam.project.dao.impl.BookOrderDaoImpl;
import com.epam.project.dao.impl.BookStatisticDaoImpl;
import com.epam.project.model.book.Book;
import com.epam.project.model.order.Issue;
import com.epam.project.model.order.Status;
import com.epam.project.model.user.User;
import com.epam.project.service.BookService;
import com.epam.project.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BookServiceImpl implements BookService {
    private static final Logger LOGGER = LogManager.getLogger();

    private final BookDao bookDao;
    private final BookOrderDao bookOrderDao;
    private final BookStatisticDao bookStatisticDao;

    public BookServiceImpl() {
        this.bookDao = new BookDaoImpl();
        this.bookOrderDao = new BookOrderDaoImpl();
        this.bookStatisticDao = new BookStatisticDaoImpl();
    }

    @Override
    public Book findBook(Long id) throws ServiceException {
        LOGGER.log(Level.INFO, "method find");
        try {
            return bookDao.find(id);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "exception in method find: ", e);
            throw new ServiceException("Exception when find book: {}", e);
        }
    }

    @Override
    public List<Book> findAllBooks() throws ServiceException {
        LOGGER.log(Level.INFO, "method findAll");
        try {
            return bookDao.findAll();
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "exception in method findAll: ", e);
            throw new ServiceException("Exception when findAll books: {}", e);
        }
    }

    @Override
    public Book create(Book book) throws ServiceException {
        LOGGER.log(Level.INFO, "method create");
        try {
            return bookDao.create(book);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "exception in method create: ", e);
            throw new ServiceException("Exception when create book: {}", e);
        }
    }

    @Override
    public Book update(Book book) throws ServiceException {
        LOGGER.log(Level.INFO, "method update");
        try {
            return bookDao.update(book);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "exception in method update: ", e);
            throw new ServiceException("Exception when update book: {}", e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        LOGGER.log(Level.INFO, "method delete");
        try {
            bookDao.delete(id);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "exception in method delete: ", e);
            throw new ServiceException("Exception when delete book: {}", e);
        }
    }

    @Override
    public List<Book> findBooksByOrderStatus(Status status) throws ServiceException {
        return null;
    }

    @Override
    public void orderBook(User user, Book book, Issue booked) throws ServiceException {

    }
}
