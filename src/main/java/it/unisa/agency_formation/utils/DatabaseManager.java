package it.unisa.agency_formation.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseManager {

        private static DatabaseManager instance;
        private Connection conn;
        private String url = "jdbc:mysql://localhost:3306/af_db";
        private String username = "root";
        private String password = "root11";

        private DatabaseManager() throws SQLException{
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.conn = DriverManager.getConnection(url,username,password);
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }

        public Connection getConnection(){
            return this.conn;
        }

        public static DatabaseManager getInstance() throws SQLException{
            if(instance == null){
                instance = new DatabaseManager();
            }else if (instance.getConnection().isClosed()){
                instance = new DatabaseManager();
            }
            return instance;
        }
}
