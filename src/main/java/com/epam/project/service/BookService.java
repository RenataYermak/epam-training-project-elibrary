package com.epam.project.service;

import com.epam.project.model.book.Book;
import com.epam.project.model.order.Issue;
import com.epam.project.model.order.Status;
import com.epam.project.model.user.User;
import com.epam.project.service.exception.ServiceException;

import java.util.List;

public interface BookService {
    Book findBook(Long id) throws ServiceException;
    List<Book> findAllBooks() throws ServiceException;
    Book create(Book book) throws ServiceException;
    Book update(Book book) throws ServiceException;
    void delete(Long id) throws ServiceException;
    List<Book> findBooksByOrderStatus(Status status) throws ServiceException;
    void orderBook(User user, Book book, Issue booked) throws ServiceException;


}
