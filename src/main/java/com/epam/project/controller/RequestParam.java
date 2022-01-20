package com.epam.project.controller;

public final class RequestParam {
    /**
     * Command params
     */
    public static final String COMMAND = "command";
    public static final String ADD_BOOK = "add_book";
    public static final String EDIT_BOOK = "edit_book";
    public static final String FIND_BOOKS = "find_books";
    public static final String FIND_BOOK = "find_book";
    public static final String DELETE_BOOK = "delete_book";

    //    SIGN_IN,
    //    SIGN_OUT,
    //    REGISTRATION,
    //    EDIT_USER,
    //    GET_USER,
    //    GET_USERS,
    //    DELETE_USER,

    /**
     * Book params
     */
    public static final String BOOK_ID = "bookId";
    public static final String BOOK_AUTHOR = "bookAuthor";
    public static final String BOOK_TITLE = "bookTitle";
    public static final String BOOK_CATEGORY = "bookCategory";
    public static final String BOOK_DESCRIPTION = "bookDescription";

    /**
     * User params
     */
    public static final String USER_ID = "userId";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE = "userRole";

    private RequestParam() {}
}
