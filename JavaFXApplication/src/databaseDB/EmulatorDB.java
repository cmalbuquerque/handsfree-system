/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andreia Patrocínio
 */


public class EmulatorDB {
    
    static int perfil=1; 
    
    public static List<Action> getActions() throws ClassNotFoundException {
        Connection con = null;
        List<Action> list = new ArrayList<>();
        try {
            con = DatabaseConnection.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select action.id_action, action.nome from action;");
            
            while(rs.next()) {
                Action a = new Action(rs.getInt(1), (String) rs.getString(2));
                list.add(a);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Search error -->" + ex.getMessage());
            
        } finally {
            DatabaseConnection.close(con);
        }
        return list;
    }
    
    
    public static HashMap<String, String> getHashMapActionGesture() {
        Connection con = null;
        //Gesture, Action
        HashMap<String, String> hashmapActionGesture = new HashMap<>();
        try {
            con = DatabaseConnection.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select action.nome, gesto.gesto  from  perfil_action_list, action_list, gesto, action WHERE perfil_action_list.id_action_list=action_list.id_action_list AND id_perfil="+ perfil +" AND action_list.id_action=action.id_action AND action_list.id_gesto=gesto.id_gesto;");
            
            while(rs.next()) {
                hashmapActionGesture.put(rs.getString(2), rs.getString(1));
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Search error -->" + ex.getMessage());
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmulatorDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatabaseConnection.close(con);
        }
        return hashmapActionGesture;
    }
    
    
    public static HashMap<String, String> getHashMapActionVoice() {
        Connection con = null;
        //Gesture, Action
        HashMap<String, String> hashmapActionVoice = new HashMap<>();
        try {
            con = DatabaseConnection.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select action.nome, voz.voz  from  perfil_action_list, action_list, voz, action WHERE perfil_action_list.id_action_list=action_list.id_action_list AND id_perfil="+ perfil +" AND action_list.id_action=action.id_action AND action_list.id_voz=voz.id_voz;");
            
            while(rs.next()) {
                hashmapActionVoice.put(rs.getString(2), rs.getString(1));
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Search error -->" + ex.getMessage());
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmulatorDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatabaseConnection.close(con);
        }
        return hashmapActionVoice;
    }
    
    
    public void setProfile(Integer profileID){
        perfil = profileID;
    }
}
