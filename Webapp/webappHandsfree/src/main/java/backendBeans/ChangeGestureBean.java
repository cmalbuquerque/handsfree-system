/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendBeans;

import connectionDB.ChangeGesture;
import connectionDB.DataConnect;
import connectionDB.SignDAO;
import entities.Gesto;
import entities.Pessoa;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author nunos
 */
@ManagedBean(name = "changegesturebean")
@SessionScoped
public class ChangeGestureBean implements Serializable{    
    
    private final static String gesture = "profiles_gesture.xhtml";
    
    @ManagedProperty(value = "#{gesto}")
    private String nome;
    
    //FALTA ESTE
    @ManagedProperty(value = "#{action}")
    private String action;
    
    private Gesto gesto;
    
    private static int gesture_id;
    private static int action_id;
    
    //OU METER TUDO AQUI DENTO, TIPO ESTAS FUNCOES E CHAMO UMA A UMA OU TUDO DIRETO MAS NAO SEI SE DA
    
    //String nome, String action
    public String change() throws ClassNotFoundException {
        /*
        int gesture_id = ChangeGesture.GetGestureID(nome); //primeiro id do gesto a mudar
        int action_id = ChangeGesture.GetActionID(action);
        ChangeGesture.Update(gesture_id, action_id);
        System.out.println("------- ");
        return gesture;
        */
        
        ChangeGesture.Update(gesture_id, action_id);
        System.out.println("------- ");
        return gesture;
    }

    public Gesto getGesto() {
        return gesto;
    }

    public void setGesto(Gesto gesto) {
        this.gesto = gesto;
    }

    public String getNome() {
        return nome;
    }
      
    //ver esta parte bem
    /*
    @PostConstruct
    private void init() {
        gesto = new Gesto();
    }
    */
    
    public Integer GetGestureID(String gesto) throws ClassNotFoundException {
        System.out.println("GESTURE_ID");
        Connection con = null;
        System.out.println("GetGestureID + gesto: " + gesto);

        try {
            con = DataConnect.getConnection();
            System.out.println("-----------------------------");
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select id_gesto from gesto where id_gesto='" + gesto + "';");

            while (rs.next()) {
               gesture_id = rs.getInt(1);
               return gesture_id;
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return gesture_id;
    }
    
    //PELA ACTION QUE VAMOS MUDAR
    public Integer GetActionID(String action) throws ClassNotFoundException {
        System.out.println("ACTION_ID");
        Connection con = null;
        System.out.println("GetActionID + action: " + action);

        try {
            con = DataConnect.getConnection();
            System.out.println("-----------------------------");
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select id_action from action where nome='" + action + "';");

            while (rs.next()) {
               action_id = rs.getInt(1);
               Update();
               return action_id;
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return action_id;
    }

    public void Update() throws ClassNotFoundException {
        Connection con = null;
        System.out.println("UPDATE");
        
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
