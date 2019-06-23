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
  
    private Gesto gesto;
    
    private static int gesture_id;
    private static int action_id;
   
    public String change() throws ClassNotFoundException {
     
        ChangeGesture.Update(gesture_id, action_id);
        return gesture;
    }

    public Gesto getGesto() {
        return gesto;
    }

    public void setGesto(Gesto gesto) {
        this.gesto = gesto;
    }

    public Integer getgestureid() throws ClassNotFoundException {
        Connection con = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        String gestoNome = getGestoParam(fc);

        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select id_gesto from gesto where gesto='" + gestoNome + "';");

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

    public Integer getactionid() throws ClassNotFoundException {
        Connection con = null;

        FacesContext fc = FacesContext.getCurrentInstance();
        String actionNome = getActionParam(fc);

        try {
            con = DataConnect.getConnection();
            System.out.println("-----------------------------");
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select id_action from action where nome='" + actionNome + "';");

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
        int rs = 0;
         
        try {
            con = DataConnect.getConnection();
            PreparedStatement statement = con.prepareStatement("update action_list set id_action = " + ChangeGestureBean.action_id + " where id_gesto = " + ChangeGestureBean.gesture_id + ";");
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
