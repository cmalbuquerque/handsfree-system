/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionDB;

import entities.Pessoa;
import entities.Profile;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataDAO {
    
    public static List<String> listProfileNames(String email){

        Connection con = null;
        List<String> list = new ArrayList<String>();
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT perfil.nome FROM perfil, pessoa_perfil,pessoa WHERE perfil.id_perfil=pessoa_perfil.id_perfil AND pessoa_perfil.id_pessoa=pessoa.id_pessoa AND pessoa.email='"+email+"';");
            while (rs.next()) {
                System.out.println(rs.getString(1));
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

}
