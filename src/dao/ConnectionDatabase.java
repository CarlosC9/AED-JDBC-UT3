package dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionDatabase {
    
    private Connection connector;
    
    public ConnectionDatabase() throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        String urlDatabase = "jdbc:mysql://localhost:3306/geografia?useTimezone=true&serverTimezone=CET";
        this.connector = DriverManager.getConnection(urlDatabase, "root", "elrincon1920");
        this.connector.setAutoCommit(false);
    }
    
    public Connection getConexion() {
        return this.connector;
    }
}
