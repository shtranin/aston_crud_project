package com.example.aston_crud3.web;


import com.example.aston_crud3.commandContainer.Command;
import com.example.aston_crud3.commandContainer.CommandContainer;
import com.example.aston_crud3.dao.GroupDAO;
import com.example.aston_crud3.dao.UserDAO;
import com.example.aston_crud3.model.Group;
import com.example.aston_crud3.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;
    private GroupDAO groupDAO;
    private ExecutorService service;

    public void init() {
        userDAO = new UserDAO();
        groupDAO = new GroupDAO();
        service = Executors.newFixedThreadPool(3);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

            String action = request.getServletPath();
            CommandContainer container = CommandContainer.getInstance();
            Command command = container.receiveCommand(action);
            command.execute(request,response,this);

    }

    public void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user-list.jsp");
        dispatcher.forward(request, response);
    }

    public void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    public void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        User newUser = new User(name, email);
        userDAO.insertUser(newUser);
        response.sendRedirect("list");
    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        User user = new User(id, name, email);
        userDAO.updateUser(user);
        response.sendRedirect("list");
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("list");

    }
    public void userGroups(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Group> userGroups = groupDAO.selectAllGroupsByUserId(id);
        request.setAttribute("userGroups", userGroups);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user's-groups.jsp");
        dispatcher.forward(request, response);
    }
    public void listOfAllGroups(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Group> listGroup = groupDAO.selectAllGroups();
        request.setAttribute("listGroup", listGroup);
        RequestDispatcher dispatcher = request.getRequestDispatcher("group-list.jsp");
            dispatcher.forward(request, response);
    }
    public void deleteGroup(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        groupDAO.deleteGroupById(id);
        response.sendRedirect("/aston_crud3_war_exploded/groups");
    }
    public void createGroup(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("group-form.jsp");
            dispatcher.forward(request, response);

    }
    public void insertGroup(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        Group newGroup = new Group(name,0);
        groupDAO.insertGroup(newGroup);
        response.sendRedirect("/aston_crud3_war_exploded/groups");
    }
    public void showSubscribeMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Group> listOfNotSubscribedGroups = groupDAO.selectNotSubscribedGroupsForUser(id);
        request.setAttribute("listGroup", listOfNotSubscribedGroups);
        request.setAttribute("userId",id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("menu-for-subscribe.jsp");
        dispatcher.forward(request, response);
    }

    public void subscribeUserAtGroup(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int groupId = Integer.parseInt(request.getParameter("group_id"));
        userDAO.subscribeUserAtGroup(userId,groupId);

        List<Group> listOfNotSubscribedGroups = groupDAO.selectNotSubscribedGroupsForUser(userId);
        request.setAttribute("listGroup", listOfNotSubscribedGroups);
        request.setAttribute("userId",userId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("menu-for-subscribe.jsp");
        dispatcher.forward(request, response);

    }


}
