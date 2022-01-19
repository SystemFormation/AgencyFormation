package it.unisa.agency_formation.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class DatabaseManager {
    private String nomeDB = Const.nomeDB;
    private String name = Const.name;
    private String pwd = Const.pwd;
    private String url;

    private static Connection connect;
    private static DatabaseManager instance;

    private DatabaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage() + " problema class not found");
        }
    }

    public static DatabaseManager getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseManager();
        }
            return instance;
    }
/*
    public Connection getConnection() {
        url = "jdbc:mysql://localhost:3306/" + nomeDB + "?characterEncoding=UTF-8&serverTimezone=" + TimeZone.getDefault().getID();
        try {
            if(connect == null || connect.isClosed()) {
                connect = DriverManager.getConnection(url, name, pwd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connect;
    }*/

    public Connection getConnection() throws SQLException {
        url = "jdbc:mysql://localhost:3306/" + nomeDB + "?characterEncoding=UTF-8&serverTimezone=" + TimeZone.getDefault().getID();
        return DriverManager.getConnection(url, name, pwd);

    }

    public static void closeConnessione(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }


}

