package org.example.dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "admin";
    private final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private final String DB_BASE_QUERY = "?autoReconnect=true&userSSL=false";
    private final String DB_HOST = "localhost";
    private final int DB_PORT = 3306;
    private final String USER_DB_NAME = "user";
    private final String WORLD_DB_NAME = "world";
    private enum DB_NAME {USER_DB_NAME,WORLD_DB_NAME};
    private String DB_URL = "";

    public Connection getConnection(DB_NAME dbName) throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(getConnectionUrl(dbName), DB_USERNAME, DB_PASSWORD);
        return connection;
    }

    private String getConnectionUrl(DB_NAME dbName) {
        switch (dbName) {
            case USER_DB_NAME:
                DB_URL = "jdbc:mysql://" + DB_HOST.trim() + ":" + DB_PORT + "/" + USER_DB_NAME + DB_BASE_QUERY;
                break;
            case WORLD_DB_NAME:
                DB_URL = "jdbc:mysql://" + DB_HOST.trim() + ":" + DB_PORT + "/" + WORLD_DB_NAME + DB_BASE_QUERY;
                break;
        }
        return DB_URL;
    }

    public void closeDBConnection(Connection connection) {
        if(connection == null)
            return;
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
