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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
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
    
    //@ManagedProperty(value = "#{gesto}")
    //private String nome;
    
    //FALTA ESTE
    //@ManagedProperty(value = "#{action}")
    //private String action;
    
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

    /*
    public String getNome() {
        return nome;
    }
     */
    //ver esta parte bem
    /*
    @PostConstruct
    private void init() {
        gesto = new Gesto();
    }
     */

    public Integer getgestureid() throws ClassNotFoundException {
        System.out.println("GESTURE_ID");
        Connection con = null;
        //System.out.println("GetGestureID + gesto: " + gesto);
        FacesContext fc = FacesContext.getCurrentInstance();
        String gestoNome = getGestoParam(fc);
        //Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        //String gesture = params.get("gestureNome");
        System.out.println("GetGestureID + gesto: " + gestoNome);

        try {
            con = DataConnect.getConnection();
            System.out.println("-----------------------------");
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select id_gesto from gesto where gesto='" + gestoNome + "';");

            while (rs.next()) {
                gesture_id = rs.getInt(1);
                System.out.println("GESTURE ID: " + gesture_id);
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
    public Integer getactionid() throws ClassNotFoundException {
        System.out.println("ACTION_ID");
        Connection con = null;

        FacesContext fc = FacesContext.getCurrentInstance();
        String actionNome = getActionParam(fc);
        System.out.println("GetActionID + action: " + actionNome);

        try {
            con = DataConnect.getConnection();
            System.out.println("-----------------------------");
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select id_action from action where nome='" + actionNome + "';");

            while (rs.next()) {
                action_id = rs.getInt(1);
                System.out.println("ACTION ID: " + action_id);
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
        int rs = 0;
         
        try {
            con = DataConnect.getConnection();
            System.out.println("-----------------------------");
            PreparedStatement statement = con.prepareStatement("update action_list set id_action = " + ChangeGestureBean.action_id + " where id_gesto = " + ChangeGestureBean.gesture_id + ";");
            System.out.println(ChangeGestureBean.action_id);
            System.out.println(ChangeGestureBean.gesture_id);
            System.out.println("update action_list set id_action = " + ChangeGestureBean.action_id + " where id_gesto = " + ChangeGestureBean.gesture_id + ";");
            rs = statement.executeUpdate();

            statement.close();
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
    }

    private String getGestoParam(FacesContext fc) {
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("gestureNome");
    }

    private String getActionParam(FacesContext fc) {
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("actionNome");
    }

}
