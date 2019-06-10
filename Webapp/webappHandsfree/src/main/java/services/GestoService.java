/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import connectionDB.DataConnect;
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
    static List<Gesto> list = new ArrayList<Gesto>();

    
     //buscar bd todos os gestos
    //select * from gesto;
    //chamar gesto e construir objecto?????
    //recebe nome como parametro.
    //string resultaod query
   
    
    public static List<Gesto> getGestos() throws ClassNotFoundException {
        Connection con = null;

        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from gesto;");
            list.clear();
            
            while(rs.next()) { 
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
   
   public List<Gesto> getList() {
        return list;
   }
   
   public String getGestoNome() {
       String nome = null;
       for(Gesto gesto: list){
           nome = gesto.getNome();
       }
       return nome;
   }
     
}