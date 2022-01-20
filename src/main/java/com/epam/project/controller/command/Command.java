package com.epam.project.controller.command;

import com.epam.project.controller.SessionAttribute;
import com.epam.project.model.user.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@FunctionalInterface
public interface Command {
    String execute(HttpServletRequest request, HttpSession session);

    default boolean isAuthorized(HttpSession session) {
        return session.getAttribute(SessionAttribute.AUTHORIZED_USER) != null;
    }

    default boolean isAdmin(HttpSession session) {
        return session.getAttribute(SessionAttribute.AUTHORIZED_USER_ROLE) != null
                && session.getAttribute(SessionAttribute.AUTHORIZED_USER_ROLE).equals(Role.ADMIN);
    }
}
