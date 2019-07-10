/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import connectionDB.DataConnect;
import entities.Action;
import entities.Gesto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author nunos
 */
//serve para ir buscar todos os gestos a BD para dar display no changeGestures
@ManagedBean(name = "gestoService")
@ApplicationScoped
public class GestoService {

     

    //buscar bd todos os gestos
    //select * from gesto;
    //chamar gesto e construir objecto?????
    //recebe nome como parametro.
    //string resultaod query
    public static List<Gesto> getGestos() throws ClassNotFoundException {
        Connection con = null;
        List<Gesto> list = new ArrayList<Gesto>();
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select gesto.id_gesto, gesto.gesto, action_list.id_action, action.nome from action_list,gesto,action WHERE action_list.id_gesto=gesto.id_gesto AND action_list.id_action=action.id_action;");
            list.clear();

            while (rs.next()) {
                Gesto gesto = new Gesto(Integer.parseInt(rs.getString(1)), rs.getString(2));
                Action a = new Action(Integer.parseInt(rs.getString(3)), rs.getString(4));
                gesto.setAction(a);
                list.add(gesto);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return list;
    }

        public static List<Gesto> getGestosPerfil(int selectedProfile) throws ClassNotFoundException {
        Connection con = null;
        List<Gesto> list = new ArrayList<Gesto>();
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select gesto.id_gesto, gesto.gesto, action.id_action, action.nome from perfil_action_list, action_list, action, gesto WHERE perfil_action_list.id_perfil="+selectedProfile + " AND perfil_action_list.id_action_list=action_list.id_action_list AND action_list.id_action=action.id_action AND action_list.id_gesto=gesto.id_gesto;");
            list.clear();

            while (rs.next()) {
                Gesto gesto = new Gesto(Integer.parseInt(rs.getString(1)), rs.getString(2));
                Action a = new Action(Integer.parseInt(rs.getString(3)), rs.getString(4));
                gesto.setAction(a);
                list.add(gesto);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return list;
    }
    
    public static List<Gesto> getAllGestos() throws ClassNotFoundException {
        Connection con = null;
        List<Gesto> list = new ArrayList<Gesto>();
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select gesto.id_gesto, gesto.gesto from gesto;");
            list.clear();

            while (rs.next()) {
                Gesto gesto = new Gesto(Integer.parseInt(rs.getString(1)), rs.getString(2));
                list.add(gesto);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return list;
    }

}
