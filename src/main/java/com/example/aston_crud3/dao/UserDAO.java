package com.example.aston_crud3.dao;

import com.example.aston_crud3.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO{

    public UserDAO() {
    }

    public void insertUser(User user) throws SQLException {
        String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email) VALUES " + " (?, ?);";
        System.out.println(INSERT_USERS_SQL);

        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User selectUser(int id) {
        String SELECT_USER_BY_ID = "select id,name,email from users where id =?";
        User user = null;

        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {

            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                user = new User(id, name, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> selectAllUsers() {
        String SELECT_ALL_USERS = "select * from users";

        List<User> users = new ArrayList<>();

        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                users.add(new User(id, name, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void deleteUser(int id) throws SQLException {
        String DECREMENT_USER_COUNTER_IN_GROUP = "UPDATE groups SET user_count = ? WHERE id = ?;";
        String DELETE_USERS_SQL = "delete from users where id = ?;";
        String FIND_SUBSCRIBED_GROUPS_SQL = "SELECT ug.group_id, g.user_count\n" +
                                            " FROM users_groups AS ug\n" +
                                            " INNER JOIN groups AS g\n" +
                                            " ON ug.group_id = g.id\n" +
                                            "WHERE ug.user_id = ?;";

        try (Connection connection = DataSource.getConnection();
            PreparedStatement selectGroupsToDecrementCounterStatement = connection.prepareStatement(FIND_SUBSCRIBED_GROUPS_SQL)){

            selectGroupsToDecrementCounterStatement.setInt(1,id);

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            ResultSet resultSet = selectGroupsToDecrementCounterStatement.executeQuery();

            while(resultSet.next()){
                PreparedStatement decrementUserCountStatement = connection.prepareStatement(DECREMENT_USER_COUNTER_IN_GROUP);
                int userCount = resultSet.getInt("user_count") - 1;
                decrementUserCountStatement.setInt(1,userCount);
                int groupId = resultSet.getInt("group_id");
                decrementUserCountStatement.setInt(2,groupId);
                decrementUserCountStatement.executeUpdate();
            }

             PreparedStatement deleteStatement = connection.prepareStatement(DELETE_USERS_SQL);
             deleteStatement.setInt(1, id);
             deleteStatement.executeUpdate();

             connection.commit();

        }

    }

    public void updateUser(User user) throws SQLException {
        String UPDATE_USERS_SQL = "update users set name = ?,email= ? where id = ?;";

        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getId());

            statement.executeUpdate();
        }
    }
    public void subscribeUserAtGroup(int userId, int groupId){
        String INCREMENT_GROUP_COUNTER = "UPDATE groups SET user_count = user_count + 1 WHERE id = ?;";
        String SUBSCRIBE_USER_AT_GROUP = "INSERT INTO users_groups(user_id, group_id) VALUES (?, ?);";

        try(Connection connection = DataSource.getConnection();
            PreparedStatement incrementingStatement = connection.prepareStatement(INCREMENT_GROUP_COUNTER)){

            incrementingStatement.setInt(1,groupId);
            PreparedStatement subscribingStatement = connection.prepareStatement(SUBSCRIBE_USER_AT_GROUP);
            subscribingStatement.setInt(1,userId);
            subscribingStatement.setInt(2,groupId);

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            incrementingStatement.executeUpdate();
            subscribingStatement.executeUpdate();
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}
