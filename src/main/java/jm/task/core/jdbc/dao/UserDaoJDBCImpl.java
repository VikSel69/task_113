package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        Connection connect = Util.getMySQLConnection();
        Statement statement = connect.createStatement();
        statement.execute("CREATE TABLE if NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, age SMALLINT NOT NULL," +
                "PRIMARY KEY (id)) ENGINE = InnoDB");
        statement.close();
        connect.close();
    }

    public void dropUsersTable() throws SQLException {
        Connection connect = Util.getMySQLConnection();
        Statement statement = connect.createStatement();
        statement.execute("DROP TABLE if EXISTS users");
        statement.close();
        connect.close();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connect = Util.getMySQLConnection();
        PreparedStatement ps = connect.prepareStatement("INSERT INTO users (name, lastName, age) " +
                "VALUE (?, ?, ?)");
        ps.setString(1, name);
        ps.setString(2, lastName);
        ps.setByte(3, age);
        ps.executeUpdate();
        ps.close();
        connect.close();
    }

    public void removeUserById(long id) throws SQLException {
        Connection connect = Util.getMySQLConnection();
        PreparedStatement ps = connect.prepareStatement("DELETE FROM users WHERE id = ?");
        ps.setLong(1, id);
        ps.executeUpdate();
        ps.close();
        connect.close();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection connect = Util.getMySQLConnection();
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users;");
        while (resultSet.next()) {
            User us = new User();
            us.setId(resultSet.getLong(1));
            us.setName(resultSet.getString(2));
            us.setLastName(resultSet.getString(3));
            us.setAge(resultSet.getByte(4));
            users.add(us);
        }
        statement.close();
        connect.close();
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        Connection connect = Util.getMySQLConnection();
        Statement statement = connect.createStatement();
        statement.execute("TRUNCATE TABLE users;");
        statement.close();
        connect.close();
    }
}
