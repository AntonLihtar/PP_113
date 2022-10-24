package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDaoJDBCImpl usdao = new UserDaoJDBCImpl();
    public void createUsersTable() throws SQLException {
        usdao.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        usdao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        usdao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        usdao.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {

        return usdao.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        usdao.cleanUsersTable();
    }
}
