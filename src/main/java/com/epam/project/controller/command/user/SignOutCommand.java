package com.epam.project.controller.command.user;

import com.epam.project.controller.PagePath;
import com.epam.project.controller.command.Command;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignOutCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request, HttpSession session) {
        LOGGER.log(Level.INFO, "method execute()");
        if (isAuthorized(session)) {
            session.invalidate();
            LOGGER.log(Level.INFO, "session invalidate successfully");
        }
        return PagePath.SIGN_IN;
    }
}
