package com.epam.project.controller.command.book;

import com.epam.project.controller.RequestAttribute;
import com.epam.project.controller.RequestParam;
import com.epam.project.controller.PagePath;
import com.epam.project.controller.command.Command;
import com.epam.project.model.book.Book;
import com.epam.project.model.book.Category;
import com.epam.project.service.BookService;
import com.epam.project.service.exception.ServiceException;
import com.epam.project.service.impl.BookServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class EditBookCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    private final BookService bookService;

    public EditBookCommand() {
        this.bookService = new BookServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpSession session) {
        LOGGER.log(Level.INFO, "method execute()");
        if (isAuthorized(session)) {
            try {
                Long id = Long.parseLong(request.getParameter(RequestParam.BOOK_ID));
                String author = request.getParameter(RequestParam.BOOK_AUTHOR);
                String title = request.getParameter(RequestParam.BOOK_TITLE);
                String category = request.getParameter(RequestParam.BOOK_CATEGORY);
                String description = request.getParameter(RequestParam.BOOK_DESCRIPTION);
                Book currentBook = bookService.findBook(id);
                if (currentBook != null) {
                    currentBook.setTitle(title);
                    currentBook.setAuthor(author);
                    currentBook.setCategory(Category.valueOf(category.toUpperCase()));
                    currentBook.setDescription(description);
                    bookService.update(currentBook);
                    List<Book> books = bookService.findAllBooks();
                    request.setAttribute(RequestAttribute.BOOKS, books);
                    return PagePath.BOOKS;
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, "error during updating book: ", e);
            }
        }
        return PagePath.EDIT_BOOK;
    }
}
