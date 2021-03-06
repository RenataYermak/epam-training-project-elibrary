package com.epam.project.controller.command;

import com.epam.project.controller.command.book.*;
import com.epam.project.controller.RequestParam;
import com.epam.project.controller.command.user.*;

import javax.servlet.http.HttpServletRequest;
import java.util.EnumMap;

public final class CommandHelper {
    private static CommandHelper INSTANCE = new CommandHelper();
    private final EnumMap<CommandName, Command> commands;

    private CommandHelper() {
        this.commands = new EnumMap<>(CommandName.class);
        this.commands.put(CommandName.ADD_BOOK, new AddBookCommand());
        this.commands.put(CommandName.EDIT_BOOK, new EditBookCommand());
        this.commands.put(CommandName.FIND_BOOK, new FindBookCommand());
        this.commands.put(CommandName.FIND_BOOKS, new FindAllBooksCommand());
        this.commands.put(CommandName.DELETE_BOOK, new DeleteBookCommand());

        this.commands.put(CommandName.SIGN_IN, new SignInCommand());
        this.commands.put(CommandName.SIGN_OUT, new SignOutCommand());
        this.commands.put(CommandName.REGISTRATION, new RegistrationCommand());
        this.commands.put(CommandName.EDIT_USER, new EditUserCommand());
        this.commands.put(CommandName.FIND_USER, new FindUserCommand());
        this.commands.put(CommandName.FIND_USERS, new FindAllUsersCommand());
        this.commands.put(CommandName.DELETE_USER, new DeleteUserCommand());
    }

    private static CommandHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CommandHelper();
        }
        return INSTANCE;
    }

    public static Command getCommand(HttpServletRequest request) {
        String commandName = request.getParameter(RequestParam.COMMAND);
        return getInstance().commands.get(CommandName.valueOf(commandName.toUpperCase()));
    }
}
