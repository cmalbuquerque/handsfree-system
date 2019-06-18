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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataDAO {

    public static List<String> listProfileNames(String email) {

        Connection con = null;
        List<String> list = new ArrayList<String>();
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT perfil.nome FROM perfil, pessoa_perfil,pessoa WHERE perfil.id_perfil=pessoa_perfil.id_perfil AND pessoa_perfil.id_pessoa=pessoa.id_pessoa AND pessoa.email='" + email + "';");
            while (rs.next()) {
                list.add((String) rs.getString(1));
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
            ResultSet rs = statement.executeQuery("SELECT app.id_app, app.nome FROM app,usa_app,pessoa WHERE app.id_app=usa_app.id_app AND usa_app.id_pessoa=pessoa.id_pessoa AND pessoa.email='" + email + "';");
            while (rs.next()) {
                App app = new App(Integer.parseInt(rs.getString(1)),(String) rs.getString(2));
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
                Profile p = new Profile(Integer.parseInt(rs.getString(1)), (String)rs.getString(2));
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
            ResultSet rs = statement.executeQuery("SELECT voz.id_voz, voz, action.id_action, action.nome FROM action_list,voz,action WHERE action_list.id_action=action.id_action AND action_list.id_voz=voz.id_voz;");
            //System.out.println(p.getId());            
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

}
