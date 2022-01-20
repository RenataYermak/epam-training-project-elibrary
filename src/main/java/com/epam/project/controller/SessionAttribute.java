package com.epam.project.controller;

public final class SessionAttribute {
    public static final String AUTHORIZED_USER = "authUser";
    public static final String AUTHORIZED_USER_ROLE = "authUserRole";

    private SessionAttribute() {}

    public String getAUTHORIZED_USER() {
        return AUTHORIZED_USER;
    }
}
