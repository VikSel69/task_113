package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String hostName = "localhost";
    private static final String dbName = "bdusers";
    private static final String userName = "root";
    private static final String password = "root";

    public static Connection MySQLConnection() throws SQLException {
        String connectUrl = "jdbc:mysql://"
                + hostName + ":3306/"
                + dbName + "?verifyServerCertificate=false&useSSL=false&requireSSL=false&serverTimezone=UTC";
        return DriverManager.getConnection(connectUrl, userName, password);
    }

    public static Connection getMySQLConnection() throws SQLException {
        return MySQLConnection();
    }

}
