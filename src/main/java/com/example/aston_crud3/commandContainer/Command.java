package com.example.aston_crud3.commandContainer;


import com.example.aston_crud3.web.UserServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    void execute(HttpServletRequest request, HttpServletResponse resp, UserServlet userServlet);
}