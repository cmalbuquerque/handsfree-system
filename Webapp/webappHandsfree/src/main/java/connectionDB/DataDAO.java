/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionDB;

import entities.Action;
import entities.App;
import entities.Gesto;
import entities.Profile;
import entities.Voice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataDAO {

    public static List<Profile> listProfiles(String email) {

        Connection con = null;
        List<Profile> list = new ArrayList<Profile>();
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT perfil.id_perfil, perfil.nome FROM perfil, pessoa_perfil,pessoa WHERE perfil.id_perfil=pessoa_perfil.id_perfil AND pessoa_perfil.id_pessoa=pessoa.id_pessoa AND pessoa.email='" + email + "';");
            while (rs.next()) {
                Profile p = new Profile(Integer.parseInt(rs.getString(1)), (String) rs.getString(2));
                list.add(p);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Search error -->" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DataConnect.close(con);
        }
        return list;
    }

    public static List<App> listApps(String email) {

        Connection con = null;
        List<App> list = new ArrayList<App>();
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT DISTINCT app.id_app, app.nome FROM app,usa_app,pessoa WHERE app.id_app=usa_app.id_app AND usa_app.id_pessoa=pessoa.id_pessoa AND pessoa.email='" + email + "';");
            while (rs.next()) {
                App app = new App(Integer.parseInt(rs.getString(1)), (String) rs.getString(2));
                list.add(app);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Search error -->" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DataConnect.close(con);
        }
        return list;
    }

    public static List<Profile> listProfilesOfApp(App app, String email) {

        Connection con = null;
        List<Profile> list = new ArrayList<Profile>();
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT perfil.id_perfil, perfil.nome FROM usa_app,perfil,pessoa WHERE perfil.id_perfil=usa_app.id_perfil AND usa_app.id_pessoa=pessoa.id_pessoa AND usa_app.id_app = " + app.getId() + " AND pessoa.email='" + email + "';");

            while (rs.next()) {
                Profile p = new Profile(Integer.parseInt(rs.getString(1)), (String) rs.getString(2));
                list.add(p);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Search error -->" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DataConnect.close(con);
        }
        return list;
    }

    public static List<Voice> voiceCommands(Profile p) {

        Connection con = null;
        List<Voice> lista = new ArrayList<Voice>();
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT voz.id_voz, voz, action.id_action, action.nome from perfil_action_list, action_list, voz, action WHERE action.id_action=action_list.id_action AND voz.id_voz=action_list.id_voz AND action_list.id_action_list=perfil_action_list.id_action_list AND perfil_action_list.id_perfil=" + p.getId() + ";");
            while (rs.next()) {
                Voice v = new Voice(Integer.parseInt(rs.getString(1)), (String) rs.getString(2));
                v.setAction(new Action(Integer.parseInt(rs.getString(3)), (String) rs.getString(4)));
                lista.add(v);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Search error -->" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DataConnect.close(con);
        }
        return lista;
    }

    public static List<Voice> getAllVoices() {

        Connection con = null;
        List<Voice> lista = new ArrayList<Voice>();
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT voz.id_voz, voz.voz, action.id_action, action.nome from voz,action_list,action WHERE action.id_action = action_list.id_action AND action_list.id_voz=voz.id_voz;");
            while (rs.next()) {
                Voice v = new Voice(Integer.parseInt(rs.getString(1)), (String) rs.getString(2));
                v.setAction(new Action(Integer.parseInt(rs.getString(3)), (String) rs.getString(4)));
                lista.add(v);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Search error -->" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DataConnect.close(con);
        }
        return lista;
    }

    public static void updateVoiceCommands(int selectedVoice, int newVoice) {
        Connection con = null;
        
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            
            String querySel = "SELECT * from voz,action_list WHERE action_list.id_voz="+selectedVoice+";";
            ResultSet rs = statement.executeQuery(querySel);
            int id_action_list_old=0;
            while ( rs.next() ) {
                id_action_list_old = rs.getInt("id_action_list");
            }
            
            querySel = "SELECT * from voz,action_list WHERE action_list.id_voz="+newVoice+";";
            rs = statement.executeQuery(querySel);
            int id_action_list_new=0;
            while ( rs.next() ) {
                id_action_list_new = rs.getInt("id_action_list");
            }
            rs.close();
            
            String query = "UPDATE action_list SET id_action=10 WHETE id_voz="+selectedVoice+";";
            statement.executeUpdate(query);
            query="UPDATE action_list SET id_action =" + selectedVoice + " WHERE id_voz = " + newVoice + ";";
            statement.executeUpdate(query);
            
            query="INSERT INTO perfil_action_list(id_perfil,id_action_list) VALUES(1,"+id_action_list_new+");";
            statement.executeUpdate(query);
            
            query="DELETE FROM perfil_action_list WHERE id_perfil=1 AND id_action_list="+id_action_list_old+");";
            statement.executeUpdate(query);
            
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DataConnect.close(con);
        }
    }

}
