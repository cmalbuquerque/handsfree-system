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
        System.out.println("OLA");
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
        
        System.out.println("GESTO NOME: "  + gestoNome);
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select id_gesto from gesto where gesto='" + gestoNome + "';");

            while (rs.next()) {
                ChangeGestureBean.gesture_id = rs.getInt(1);
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
                ChangeGestureBean.action_id = rs.getInt(1);
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
        System.out.println("here");
        Connection con = null;

        try {
            con = DataConnect.getConnection();
            
            //o antigo
            String id_action_list = "select id_action_list from action_list where id_gesto=" + gesto.getId() + " AND id_action=" + ChangeGestureBean.action_id + " ;";
            PreparedStatement pstmt2 = con.prepareStatement(id_action_list);
            int old_id = pstmt2.executeUpdate();
            System.out.println("PRIMEIRO ID: " + old_id);

            String insert = "insert into action_list(id_gesto,id_action) values(?,?)";
            PreparedStatement pstmt = con.prepareStatement(insert);
            pstmt.setInt(1, ChangeGestureBean.gesture_id);
            pstmt.setInt(2, ChangeGestureBean.action_id);
            pstmt.executeUpdate();

            String select = "select id_action_list from action_list where id_gesto = " + ChangeGestureBean.action_id + " AND id_action = " + ChangeGestureBean.gesture_id + ";";
            PreparedStatement pstmt1 = con.prepareStatement(select);
            pstmt.setInt(1, ChangeGestureBean.gesture_id);
            pstmt.setInt(2, ChangeGestureBean.action_id);
            int new_id = pstmt1.executeUpdate();

            String update = "update perfil_action_list set id_gesto = " + ChangeGestureBean.gesture_id + "where id_action_list = " + new_id + ";";
            PreparedStatement statement = con.prepareStatement(update);
            statement.executeUpdate();

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
