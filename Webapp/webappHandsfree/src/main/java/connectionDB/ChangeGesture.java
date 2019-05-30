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
public class ChangeGesture {
    
    //fazer aqui a alteracao
    
    //select id_action from action where nome='cima_baixo'
    
    //tenho: cima-baixo--> swipe || quero --> baixo-cima --swipe
    //select action from action where id = 1;
    //select id_action from action_list where id_gesto = id_swipe;
    public static Integer GetGestureID(String gesto) throws ClassNotFoundException {
        Connection con = null;
        System.out.println("GetGestureID + gesto: " + gesto);

        try {
            con = DataConnect.getConnection();
            System.out.println("-----------------------------");
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select id_gesto from gesto where gesto='" + gesto + "';");

            while (rs.next()) {
               return rs.getInt(1);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return null;
    }
    
    public static Integer GetActionID(String action) throws ClassNotFoundException {
        Connection con = null;
        System.out.println("GetActionID + action: " + action);

        try {
            con = DataConnect.getConnection();
            System.out.println("-----------------------------");
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select id_action from action where nome='" + action + "';");

            while (rs.next()) {
               return rs.getInt(1);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return null;
    }

    public static void Update(int gesture_id, int action_id) throws ClassNotFoundException {
        Connection con = null;
        
        try {
            con = DataConnect.getConnection();
            System.out.println("-----------------------------");
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("UPDATE action_list SET id_action=" + action_id + " WHERE gesture_id =" + gesture_id + " ;");

            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
    }
}
