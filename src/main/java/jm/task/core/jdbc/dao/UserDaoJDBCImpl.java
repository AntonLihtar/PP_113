package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() { //default constructor
    }
    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement stat = connection.createStatement()) {
            stat.execute("CREATE TABLE IF NOT EXISTS user(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, " +
                    "last_name VARCHAR(255) NOT NULL, age INT NOT NULL )");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement stat = connection.createStatement()) {
            stat.execute("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             var ppStmt = connection.prepareStatement("INSERT INTO user (name, last_name, age) VALUES(?, ?, ?)")) {
            ppStmt.setString(1, name); //подставляем символы
            ppStmt.setString(2, lastName);   //то же
            ppStmt.setByte(3, age);
            ppStmt.executeUpdate();
            System.err.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement ppStmt = connection.prepareStatement("DELETE FROM user WHERE ID=?")) {
            ppStmt.setLong(1, id);
            ppStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement stat = connection.createStatement()) {

            ResultSet resultSet = stat.executeQuery("SELECT id, name, last_name, age FROM user");
            System.err.print("-> Start while ");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }
    public void cleanUsersTable() {

        try(Connection connection = Util.getConnection();
        Statement stat = connection.createStatement()) {
            stat.execute("TRUNCATE user");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
