package com.example.aston_crud3.commandContainer.commands;

import com.example.aston_crud3.commandContainer.Command;
import com.example.aston_crud3.web.UserServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class InsertNewUserCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse resp, UserServlet userServlet) {
        try {
            userServlet.insertUser(request,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
