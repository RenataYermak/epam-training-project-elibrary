package com.epam.project.controller.command.book;

import com.epam.project.controller.command.Command;
import com.epam.project.controller.RequestAttribute;
import com.epam.project.controller.RequestParam;
import com.epam.project.controller.PagePath;
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


public class AddBookCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    private final BookService bookService;

    public AddBookCommand() {
        this.bookService = new BookServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpSession session) {
        if (isAdmin(session) && isAuthorized(session)) {
            try {
                String author = request.getParameter(RequestParam.BOOK_AUTHOR);
                String title = request.getParameter(RequestParam.BOOK_TITLE);
                String category = request.getParameter(RequestParam.BOOK_CATEGORY);
                String description = request.getParameter(RequestParam.BOOK_DESCRIPTION);
                Book book = new Book();
                book.setTitle(title);
                book.setAuthor(author);
                book.setCategory(Category.valueOf(category.toUpperCase()));
                book.setDescription(description);
                book = bookService.create(book);
                if (book.getId() != null) {
                    LOGGER.log(Level.INFO, "book was created successfully");
                    request.setAttribute(RequestAttribute.BOOK, book);
                }
                return PagePath.BOOK;
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, "error during book creation: ", e);
            }
        }
        return PagePath.CREATE_BOOK;
    }
}
