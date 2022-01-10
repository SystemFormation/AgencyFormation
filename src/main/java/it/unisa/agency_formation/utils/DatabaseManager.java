package it.unisa.agency_formation.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    String nomeDB = Const.nomeDB;
    String name = Const.name;
    String pwd = Const.pwd;
    String url;

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
        } else if (connect != null && connect.isClosed()) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() {
        url = "jdbc:mysql://localhost:3306/" + nomeDB + "?serverTimezone=UTC";
        try {
            return this.connect = DriverManager.getConnection(url, name, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}

