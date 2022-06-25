package com.example.aston_crud3.MultiThreading;

import com.example.aston_crud3.commandContainer.Command;
import com.example.aston_crud3.commandContainer.CommandContainer;
import com.example.aston_crud3.web.UserServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Task implements Runnable {
    private HttpServletResponse response;
    private HttpServletRequest request;
    private UserServlet userServlet;

    public Task(HttpServletResponse response, HttpServletRequest request,UserServlet userServlet) {
        this.response = response;
        this.request = request;
        this.userServlet = userServlet;
        this.run();
    }

    @Override
    public void run() {
        String action = request.getServletPath();
        CommandContainer container = CommandContainer.getInstance();
        Command command = container.receiveCommand(action);
        command.execute(request,response,userServlet);

    }
}
