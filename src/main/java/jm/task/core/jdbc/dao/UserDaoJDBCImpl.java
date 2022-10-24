package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() { //default constructor

    }

    public void createUsersTable() throws SQLException { //create table Users
//        Statement stat = null;
        Connection connection = Util.getConnection(); //connect

        Statement stat = connection.createStatement(); //CONTAINER

        stat.executeUpdate("CREATE TABLE IF NOT EXISTS user(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, " +
                "last_name VARCHAR(255) NOT NULL, age INT NOT NULL )");
        System.err.println("-> TABLE CREATE");
        stat.close();
        connection.close();

    }

    public void dropUsersTable() throws SQLException {

        Connection connection = Util.getConnection(); //connect

        Statement stat = connection.createStatement(); //CONTAINER

        stat.executeUpdate("DROP TABLE IF EXISTS user");
        System.err.println("-> TABLE DROP");
        stat.close();
        connection.close();

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

        Connection connection = Util.getConnection(); //connect
        String sql = "INSERT INTO user (name, last_name, age) VALUES(?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql); //CONTAINER

        preparedStatement.setString(1, name); //подставляем символы
        preparedStatement.setString(2, lastName);   //то же
        preparedStatement.setByte(3, age);

        preparedStatement.executeUpdate();
        System.err.println("User с именем – " + name + " добавлен в базу данных");

        preparedStatement.close();
        connection.close();
    }

    public void removeUserById(long id) throws SQLException {

        Connection connection = Util.getConnection(); //connect
        String sql = "DELETE FROM user WHERE ID=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql); //CONTAINER

        preparedStatement.setLong(1, id);

        preparedStatement.executeUpdate();

        System.err.println("-> User delete");
        preparedStatement.close();
        connection.close();
    }

    public List<User> getAllUsers() throws SQLException {

        List<User> userList = new ArrayList<>();
        Connection connection = Util.getConnection(); //connect

        String sql = "SELECT id, name, last_name, age FROM user";
        Statement stat = connection.createStatement(); //CONTAINER

        ResultSet resultSet = stat.executeQuery(sql);
        System.err.print("-> Start while ");
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setAge(resultSet.getByte("age"));
            userList.add(user);
        }
        System.err.println("-> Good like");
        stat.close();
        connection.close();
        return userList;
    }

    public void cleanUsersTable() throws SQLException {

        Connection connection = Util.getConnection(); //connect

        Statement stat = connection.createStatement(); //CONTAINER

        stat.executeUpdate("DELETE FROM user");

        System.err.println("-> Users table cleared");
        stat.close();
        connection.close();
    }
}
