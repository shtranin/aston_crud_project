package com.example.aston_crud3.commandContainer.commands;

import com.example.aston_crud3.commandContainer.Command;
import com.example.aston_crud3.web.UserServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubscribeUserAtGroupCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse resp, UserServlet userServlet) {
        try {
            userServlet.subscribeUserAtGroup(request,resp);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}
