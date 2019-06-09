/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import connectionDB.DataConnect;
import entities.Action;
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

//serve para ir buscar todos os action a BD para dar display no changeGestures
@ManagedBean(name = "actionService")
@ApplicationScoped
public class ActionService {
    static List<Action> list = new ArrayList<Action>();

    public static List<Action> getAction() throws ClassNotFoundException {
        Connection con = null;

        try {
            con = DataConnect.getConnection();
            System.out.println("-----------------------------");
            System.out.println("ACTION");
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from action;");
            System.out.println("OLA");
            list.clear();

            while (rs.next()) {

                System.out.println("id: " + rs.getString(1) + " nome: " + rs.getString(2));
                Action action = new Action(rs.getString(1), rs.getString(2));
                System.out.println(action.getNome());
                list.add(action);
                System.out.println("list_size: " + list.size());
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

   public List<Action> getList() {
        return list;
   }
   
   public static String getActionNome() {
       String nome = null;
       for(Action action: list){
           nome = action.getNome();
       }
       return nome;
   }
     
}