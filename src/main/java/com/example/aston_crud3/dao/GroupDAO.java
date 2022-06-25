package com.example.aston_crud3.dao;

import com.example.aston_crud3.model.Group;
import com.example.aston_crud3.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO{
    public GroupDAO() {
    }
    public List<Group> selectAllGroupsByUserId(int userId){
        String SELECT_ALL_GROUPS_BY_USER_ID = "SELECT g.id,g.name,g.user_count FROM groups as g INNER JOIN users_groups as ug ON g.id = ug.group_id INNER JOIN users as u ON ug.user_id = u.id WHERE u.id = ?";

        List<Group> groups = new ArrayList<>();

        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_GROUPS_BY_USER_ID)) {
             System.out.println(statement);
             statement.setInt(1,userId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int userCount = rs.getInt("user_count");

                groups.add(new Group(id, name, userCount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }
    public List<Group> selectAllGroups(){
        String SELECT_ALL_GROUPS = "SELECT * FROM groups;";

        List<Group> groups = new ArrayList<>();

        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_GROUPS)) {
            System.out.println(statement);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int userCount = rs.getInt("user_count");

                groups.add(new Group(id, name, userCount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }
    public void deleteGroupById(int id) throws SQLException {
        String DELETE_GROUP_SQL = "delete from groups where id = ?;";

        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_GROUP_SQL);) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }

    }
    public void insertGroup(Group group){
        String INSERT_GROUP_SQL = "INSERT INTO groups" + "  (name,user_count) VALUES " + " (?, ?);";
        System.out.println(INSERT_GROUP_SQL);

        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GROUP_SQL)) {
            preparedStatement.setString(1, group.getName());
            preparedStatement.setInt(2, group.getUserCount());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Group> selectNotSubscribedGroupsForUser(int userId){
        String SELECT_NOT_SUBSCRIBED_GROUPS = "SELECT g.id, g.name, g.user_count" +
                                              "FROM groups AS g" +
                                              "LEFT JOIN users_groups AS ug" +
                                              "ON g.id = ug.group_id" +
                                              "WHERE ug.user_id != ? OR ug.user_id IS NULL;";

        List<Group> groups = new ArrayList<>();

        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_NOT_SUBSCRIBED_GROUPS);) {
                statement.setInt(1,userId);
            System.out.println(statement);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int userCount = rs.getInt("user_count");

                groups.add(new Group(id, name, userCount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }
}
