package com.epam.project.controller.command.user;

import com.epam.project.controller.RequestAttribute;
import com.epam.project.controller.RequestParam;
import com.epam.project.controller.PagePath;
import com.epam.project.controller.command.Command;
import com.epam.project.controller.SessionAttribute;
import com.epam.project.model.book.Book;
import com.epam.project.model.user.User;
import com.epam.project.service.BookService;
import com.epam.project.service.UserService;
import com.epam.project.service.exception.ServiceException;
import com.epam.project.service.impl.BookServiceImpl;
import com.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SignInCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    private final UserService userService;
    private final BookService bookService;

    public SignInCommand() {
        this.userService = new UserServiceImpl();
        this.bookService = new BookServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpSession session) {
        LOGGER.log(Level.INFO, "method execute()");
        try {
            String login = request.getParameter(RequestParam.USER_LOGIN);
            String pass = request.getParameter(RequestParam.USER_PASSWORD);
            User user = userService.findUser(login, pass);
            if (user != null) {
                LOGGER.log(Level.INFO, "user logged in successfully");
                session.setAttribute(SessionAttribute.AUTHORIZED_USER, user);
                session.setAttribute(SessionAttribute.AUTHORIZED_USER_ROLE, user.getRole());
                List<Book> books = bookService.findAllBooks();
                request.setAttribute(RequestAttribute.BOOKS, books);
                request.setAttribute("userRole", request.isUserInRole(user.getRole().getName()));
                return PagePath.BOOKS;
            }
            LOGGER.log(Level.INFO, "failed to login, bad credentials");
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "error during user log in: ", e);
        }
        return PagePath.SIGN_IN;
    }
}
