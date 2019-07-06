/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionDB;

import entities.Action;
import entities.ActionList;
import entities.App;
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

/**
 * Buscar dados à base de dados
 * @author Andreia, Carolina, Diogo, Nuno, Pedro
 */
public class DataDAO {

    
    /**
     * Listar todos os perfis de um utilizador
     * @param email
     * @return 
     */
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

    
    /**
     * Listagem de aplicações de um utilizador
     * @param email
     * @return 
     */
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

    
    /**
     * Listagem de Perfis de cada Aplicação de um utilizador
     * @param app
     * @param email
     * @return 
     */
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

    
    /**
     * Listagem de comandos de voz de um perfil
     * @param p
     * @return 
     */
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

    
    /**
     * Listagem de todos os comandos de voz com ação atribuída
     * @return 
     */
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

    
    /**
     * Listagem de todos os comandos de voz
     * @return 
     */
    public static List<Voice> getAllVoicesWithoutActions() {

        Connection con = null;
        List<Voice> lista = new ArrayList<Voice>();
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT voz.id_voz, voz.voz from voz;");
            while (rs.next()) {
                Voice v = new Voice(Integer.parseInt(rs.getString(1)), (String) rs.getString(2));
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

    /**
     * Update de comandos de voz
     * @param selectedVoice
     * @param newVoice 
     */
    public static void updateVoiceCommands(int selectedVoice, int newVoice) {
        Connection con = null;

        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();

            String querySel = "SELECT * from voz,action_list WHERE action_list.id_voz=" + selectedVoice + ";";
            ResultSet rs = statement.executeQuery(querySel);
            int id_action_list_old = 0;
            while (rs.next()) {
                id_action_list_old = rs.getInt("id_action_list");
            }

            querySel = "SELECT * from voz,action_list WHERE action_list.id_voz=" + newVoice + ";";
            rs = statement.executeQuery(querySel);
            int id_action_list_new = 0;
            while (rs.next()) {
                id_action_list_new = rs.getInt("id_action_list");
            }
            rs.close();

            String query = "UPDATE action_list SET id_action=10 WHETE id_voz=" + selectedVoice + ";";
            statement.executeUpdate(query);
            query = "UPDATE action_list SET id_action =" + selectedVoice + " WHERE id_voz = " + newVoice + ";";
            statement.executeUpdate(query);

            query = "INSERT INTO perfil_action_list(id_perfil,id_action_list) VALUES(1," + id_action_list_new + ");";
            statement.executeUpdate(query);

            query = "DELETE FROM perfil_action_list WHERE id_perfil=1 AND id_action_list=" + id_action_list_old + ");";
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

    
    /**
     * Listagem das actions list de cada perfil
     * @param p
     * @return 
     */
    public static List<ActionList> getActionListofProfile(Profile p) {
        Connection con = null;
        List<ActionList> lista = new ArrayList<ActionList>();
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT action_list.id_action_list, action.id_action, action.nome, action_list.id_gesto, action_list.id_voz, voz.voz from perfil_action_list, action_list, action, voz WHERE perfil_action_list.id_perfil=" + p.getId() + " AND perfil_action_list.id_action_list=action_list.id_action_list AND action_list.id_action=action.id_action AND action_list.id_voz=voz.id_voz;");
            while (rs.next()) {
                Action a = new Action(Integer.parseInt(rs.getString(2)), (String) rs.getString(3));
                Voice v = new Voice(Integer.parseInt(rs.getString(5)), (String) rs.getString(6));
                ActionList action_list = new ActionList(Integer.parseInt(rs.getString(1)), a, v);
                lista.add(action_list);
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

    
    /**
     * Listagem de todas as ações
     * @return 
     */
    public static List<Action> getAllActions() {
        Connection con = null;
        List<Action> lista = new ArrayList<Action>();
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT action.id_action, action.nome from action;");
            while (rs.next()) {
                Action a = new Action(Integer.parseInt(rs.getString(1)), (String) rs.getString(2));
                lista.add(a);
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

    
    /**
     * Atribuir ações a comandos de voz (inserir dados na ActionList)
     * @param selectedAction
     * @param selectedVoice
     * @throws ClassNotFoundException 
     */
    public static void insertVoicesActionList(int selectedAction, int selectedVoice) throws ClassNotFoundException {
        Connection con = null;
        System.out.println(selectedAction + ", " + selectedVoice);
        try {
            con = DataConnect.getConnection();
            //Statement statement = con.createStatement();
            //ResultSet rs = statement.executeQuery("CREATE EXTENSION pgcrypto;");

            String insert = "INSERT INTO action_list(id_action,id_voz) VALUES(?,?);";
            PreparedStatement pstmt = con.prepareStatement(insert);

            pstmt.setInt(1, selectedAction);
            pstmt.setInt(2, selectedVoice);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Sign error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

    }

    /**
     * Listagem dos perfis não usados numa aplicação do utilizador
     * @param appID
     * @param email
     * @return 
     */
    public static List<Profile> getUnsedProfilesOfApp(int appID, String email) {

        Connection con = null;
        List<Profile> list = new ArrayList<Profile>();
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT;");

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

    
    /**
     * Listagem de todos os perfis do Utilizador
     * @param email
     * @return 
     */
    public static List<Profile> getAllProfilesUser(String email) {
        Connection con = null;
        List<Profile> list = new ArrayList<Profile>();
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT perfil.id_perfil, perfil.nome from pessoa_perfil, pessoa, perfil WHERE pessoa_perfil.id_pessoa=pessoa.id_pessoa AND pessoa_perfil.id_perfil=perfil.id_perfil AND pessoa.email='" + email + "';");

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

    
    /**
     * Adicionar perfis existentes a uma aplicação do utilizador
     * @param selectedApp
     * @param profileToAdd
     * @param idUser
     * @throws ClassNotFoundException 
     */
    public static void addProfiletoApp(App selectedApp, Profile profileToAdd, int idUser) throws ClassNotFoundException {
        Connection con = null;

        try {
            con = DataConnect.getConnection();

            String insert = "INSERT INTO usa_app(id_pessoa,id_perfil,id_app) VALUES(?,?,?);";
            PreparedStatement pstmt = con.prepareStatement(insert);

            pstmt.setInt(1, idUser);
            pstmt.setInt(2, profileToAdd.getId());
            pstmt.setInt(3, selectedApp.getId());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Insert error -->" + ex.getMessage());
        } finally {

            DataConnect.close(con);
        }
    }

    
    /**
     * Get pessoa
     * @param email
     * @return 
     */
    public static int getUserId(String email) {

        Connection con = null;
        int id = 0;
        try {
            con = DataConnect.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id_pessoa from pessoa WHERE pessoa.email='"+email+"';");

            while (rs.next()) {
                id = Integer.parseInt(rs.getString(1));
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
        return id;
    }
    
    
    public static void updateGesture(int oldGestureID, int oldActionID, int newGestureID, int idPerfil, int userID) throws ClassNotFoundException {
        System.out.println("here");
        Connection con = null;

        try {
            con = DataConnect.getConnection();
            
            //Get action_listID do gesto antigo
            String id_action_list = "select id_action_list from action_list where id_gesto=" + oldGestureID + " AND id_action=" + oldActionID + " ;";
            PreparedStatement pstmt2 = con.prepareStatement(id_action_list);
            int actionlistOldID = pstmt2.executeUpdate();
            System.out.println("ActionListID: " + actionlistOldID);

            // Atribuir uma nova ação ao gesto novo (insert na actionlist)
            String insert = "insert into action_list(id_gesto,id_action) values(?,?)";
            PreparedStatement pstmt = con.prepareStatement(insert);
            pstmt.setInt(1, newGestureID);
            pstmt.setInt(2, oldActionID);
            pstmt.executeUpdate();

            // Ir buscar o id da nova actionlist criada
            String select = "select id_action_list from action_list where id_gesto = " + newGestureID + " AND id_action = " + oldActionID + ";";
            PreparedStatement pstmt1 = con.prepareStatement(select);
            pstmt.setInt(1, newGestureID);
            pstmt.setInt(2, oldActionID);
            int actionlistNewID = pstmt1.executeUpdate();

            // Update da action_list que o perfil usa
            String update = "UPDATE perfil_action_list SET id_action_list = " + actionlistNewID + "WHERE id_action_list = " + actionlistOldID + " AND id_perfil="+ idPerfil +";";
            PreparedStatement statement = con.prepareStatement(update);
            statement.executeUpdate();

            statement.close();
        } catch (SQLException ex) {
            System.out.println("Update error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
    }

}
