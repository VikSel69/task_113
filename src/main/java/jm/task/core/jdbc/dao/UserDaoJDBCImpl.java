package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connect = Util.getMySQLConnection()) {
            Statement statement = connect.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, age SMALLINT NOT NULL," +
                    "PRIMARY KEY (id)) ENGINE = InnoDB");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connect = Util.getMySQLConnection()) {
            Statement statement = connect.createStatement();
            statement.execute("DROP TABLE if EXISTS users");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connect = Util.getMySQLConnection()) {
            PreparedStatement ps = connect.prepareStatement("INSERT INTO users (name, lastName, age) " +
                    "VALUE (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connect = Util.getMySQLConnection()) {
            PreparedStatement ps = connect.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connect = Util.getMySQLConnection()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connect = Util.getMySQLConnection()) {
            Statement statement = connect.createStatement();
            statement.execute("TRUNCATE TABLE users;");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
