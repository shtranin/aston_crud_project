package com.example.aston_crud3.web;


import com.example.aston_crud3.commandContainer.Command;
import com.example.aston_crud3.commandContainer.CommandContainer;
import com.example.aston_crud3.dao.groupDAO.GroupDAOImpl;
import com.example.aston_crud3.dao.old_dao_classes.ChannelGroupDAOImpl;
import com.example.aston_crud3.dao.old_dao_classes.PrivateGroupDAOImpl;
import com.example.aston_crud3.dao.old_dao_classes.PublicGroupDAOImpl;
import com.example.aston_crud3.dao.userDAO.UserDAOImpl;
import com.example.aston_crud3.dao.old_dao_classes.GroupDAO;
import com.example.aston_crud3.dao.old_dao_classes.UserDAO;
import com.example.aston_crud3.model.group.ChannelGroup;
import com.example.aston_crud3.model.group.Group;
import com.example.aston_crud3.model.User;
import com.example.aston_crud3.model.group.PrivateGroup;
import com.example.aston_crud3.model.group.PublicGroup;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;
    private GroupDAO groupDAO;
    private UserDAOImpl userDAOImpl;
    private GroupDAOImpl groupDAOImpl;



    public void init() {
        userDAO = new UserDAO();
        groupDAO = new GroupDAO();
        userDAOImpl = new UserDAOImpl();
        groupDAOImpl = new GroupDAOImpl();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        String action = request.getServletPath();
        CommandContainer container = CommandContainer.getInstance();
        Command command = container.receiveCommand(action);
        command.execute(request, response, this);

    }

    public void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAOImpl.getAll();
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
        long id = Long.parseLong(request.getParameter("id"));
        User existingUser = userDAOImpl.getById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    public void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        User newUser = new User(name, email);


        userDAOImpl.save(newUser);
        response.sendRedirect("list");
    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        User user = new User(id, name, email);


        userDAOImpl.update(user);
        response.sendRedirect("list");
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));

        userDAOImpl.deleteById(id);
        response.sendRedirect("list");

    }

    public void userGroups(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        List<Group> userGroups = groupDAOImpl.getAllByUserId(id);
        request.setAttribute("userGroups", userGroups);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user's-groups.jsp");
        dispatcher.forward(request, response);
    }

    public void listOfAllGroups(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Group> listGroup = groupDAOImpl.getAll();
        request.setAttribute("listGroup", listGroup);
        RequestDispatcher dispatcher = request.getRequestDispatcher("group-list.jsp");
        dispatcher.forward(request, response);
    }

    public void deleteGroup(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        groupDAOImpl.deleteById(id);
        response.sendRedirect("/aston_crud3_war_exploded/groups");
    }

    public void createGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("group-form.jsp");
        dispatcher.forward(request, response);

    }

    public void insertGroup(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

         Group newGroup = getGroupFromRequest(request);
         groupDAOImpl.save(newGroup);
        response.sendRedirect("/aston_crud3_war_exploded/groups");
    }

    public void showSubscribeMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
       // List<Group> listOfNotSubscribedGroups = groupDAO.selectNotSubscribedGroupsForUser(id);
        List<Group> listOfNotSubscribedGroups = groupDAOImpl.getAll();
        request.setAttribute("listGroup", listOfNotSubscribedGroups);
        request.setAttribute("userId", id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("menu-for-subscribe.jsp");
        dispatcher.forward(request, response);
    }

    public void subscribeUserAtGroup(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int groupId = Integer.parseInt(request.getParameter("group_id"));
        userDAOImpl.subscribeAtGroup(userId, groupId);

       // List<Group> listOfNotSubscribedGroups = groupDAO.selectNotSubscribedGroupsForUser(userId);
        List<Group> listOfNotSubscribedGroups = groupDAOImpl.getAll();
        request.setAttribute("listGroup", listOfNotSubscribedGroups);
        request.setAttribute("userId", userId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("menu-for-subscribe.jsp");
        dispatcher.forward(request, response);

    }

    private Group getGroupFromRequest(HttpServletRequest request) {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String param = request.getParameter("param");
        Group group = null;
        switch (type) {
            case "Channel":
                return new ChannelGroup(name, param);
            case "Private":
                return new PrivateGroup(name, Integer.parseInt(param));
            case "Public":
                return new PublicGroup(name, param);

        }
        return null;

    }
}
