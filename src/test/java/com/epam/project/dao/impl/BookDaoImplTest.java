package com.epam.project.dao.impl;

import com.epam.project.dao.exception.DaoException;
import com.epam.project.model.book.Book;
import com.epam.project.model.book.Category;
import com.epam.project.dao.BookDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

class BookDaoImplTest {

    private final BookDao bookDao = new BookDaoImpl();

    @Test
    @DisplayName("Should find book by id")
    void shouldFindBookById() throws DaoException {
        Book actualBook = constructMockBook();
        bookDao.create(actualBook);
        Book expectedBook = bookDao.find(actualBook.getId());
        assertThat(actualBook, is(expectedBook));
        bookDao.delete(expectedBook.getId());
    }

    @Test
    @DisplayName("Should find all books")
    void shouldFindAllBooks() throws DaoException {
        int count = 5;
        for (int i = 0; i < count; i++) {
            bookDao.create(constructMockBook());
        }
        List<Book> expectedBook = bookDao.findAll();
        assertThat(expectedBook.size(), is(count));
        for (int i = 0; i < count; i++) {
            bookDao.delete(expectedBook.get(i).getId());
        }
    }

    @Test
    @DisplayName("Should create book")
    void shouldCreateBook() throws DaoException {
        Book actualBook = constructMockBook();
        Book expectedBook = bookDao.create(actualBook);
        assertThat(actualBook, is(expectedBook));
        bookDao.delete(expectedBook.getId());
    }

    @Test
    @DisplayName("Should update book")
    void shouldUpdateBook() throws DaoException {
        Book actualBook = constructMockBook();
        bookDao.create(actualBook);
        actualBook.setDescription("Update Description");
        Book expectedBook = bookDao.update(actualBook);
        assertThat(actualBook, is(expectedBook));
        bookDao.delete(expectedBook.getId());
    }

    @Test
    @DisplayName("Should delete book")
    void shouldDeleteBook() throws DaoException {
        Book actualBook = constructMockBook();
        bookDao.create(actualBook);
        bookDao.delete(actualBook.getId());
        Book expected = bookDao.find(actualBook.getId());
        assertThat(expected, nullValue());
    }

    Book constructMockBook() {
        Book book = new Book();
        book.setTitle("Test Title");
        book.setAuthor("Test Author");
        book.setCategory(Category.SCI_FI);
        book.setDescription("Test Description");
        book.setDate(Date.valueOf("1865-11-26"));
        book.setNumber(1);
        return book;
    }
}
