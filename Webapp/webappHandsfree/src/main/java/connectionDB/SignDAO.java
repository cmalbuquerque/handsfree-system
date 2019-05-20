/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nunos
 */
public class SignDAO {

    public static boolean Sign(String nome, String email, String password) throws ClassNotFoundException {
        Connection con = null;

        try {
            con = DataConnect.getConnection();
            System.out.println("-----------------------------");
            //Statement statement = con.createStatement();
            //ResultSet rs = statement.executeQuery("CREATE EXTENSION pgcrypto;");
            
            String insert = "INSERT INTO pessoa(nome,email,password) VALUES(?,?,CRYPT(?,GEN_SALT('bf',8)))";
            PreparedStatement pstmt = con.prepareStatement(insert);
            
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, password);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("Sign error -->" + ex.getMessage());
            return false;
        } finally {
            
            DataConnect.close(con);
        }
    }
    
}
