package com.epam.project.controller.command.user;

import com.epam.project.controller.PagePath;
import com.epam.project.controller.RequestAttribute;
import com.epam.project.controller.RequestParam;
import com.epam.project.controller.SessionAttribute;
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

public class EditUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    private final UserService userService;

    public EditUserCommand() {
        this.userService = new UserServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpSession session) {
        LOGGER.log(Level.INFO, "method execute()");
        if (isAuthorized(session)) {
            try {
                Long id = Long.parseLong(request.getParameter(RequestParam.USER_ID));
                String login = request.getParameter(RequestParam.USER_LOGIN);
                String pass = request.getParameter(RequestParam.USER_PASSWORD);
                Role role = Role.valueOf(request.getParameter(RequestParam.USER_ROLE).toUpperCase());
                User user = userService.findUser(id);
                if (user != null) {
                    user.setLogin(login);
                    user.setPassword(pass);
                    if (!user.getLogin().equalsIgnoreCase("admin")) {
                        user.setRole(role);
                    }
                    userService.update(user);
                    session.setAttribute(SessionAttribute.AUTHORIZED_USER, user);
                    List<User> users = userService.findActivatedUsers();
                    request.setAttribute(RequestAttribute.USERS, users);
                    return PagePath.USERS;
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, "error during updating user: ", e);
            }
        }
        return PagePath.EDIT_USER;
    }
}
