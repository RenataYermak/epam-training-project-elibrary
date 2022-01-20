package com.epam.project.controller.command.user;

import com.epam.project.controller.RequestAttribute;
import com.epam.project.controller.RequestParam;
import com.epam.project.controller.PagePath;
import com.epam.project.controller.command.Command;
import com.epam.project.model.user.Role;
import com.epam.project.model.user.User;
import com.epam.project.service.UserService;
import com.epam.project.service.exception.ServiceException;
import com.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RegistrationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    private final UserService userService;

    public RegistrationCommand() {
        this.userService = new UserServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpSession session) {
        LOGGER.log(Level.INFO, "method execute()");
        if (isAdmin(session) && isAuthorized(session)) {
            String login = request.getParameter(RequestParam.USER_LOGIN);
            String pass = request.getParameter(RequestParam.USER_PASSWORD);
            String role = request.getParameter(RequestParam.USER_ROLE);
            try {
                User user = new User();
                user.setLogin(login);
                user.setPassword(pass);
                user.setRole(Role.valueOf(role.toUpperCase()));
                user = userService.create(user);
                if (user.getId() != null) {
                    LOGGER.log(Level.INFO, "user was registered successfully");
                    List<User> allUsers = userService.findAll();
                    request.setAttribute(RequestAttribute.USERS, allUsers);
                }
                return PagePath.USERS;
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, "error during user registration: ", e);
            }
        }
        return PagePath.CREATE_USER;
    }
}
