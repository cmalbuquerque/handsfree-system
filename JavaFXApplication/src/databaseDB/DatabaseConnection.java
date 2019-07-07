/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DiogoFerreira
 */
public class DatabaseConnection {
    
    Connection con = null;

    public static Connection getConnection() throws ClassNotFoundException {
        String url = "jdbc:postgresql://localhost:5432/handsfree";
        String user = "postgres";
        String password = "postgres";
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return conn;
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
    

    
}
