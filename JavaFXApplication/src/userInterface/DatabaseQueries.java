/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import databaseDB.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DiogoFerreira
 */
class DatabaseQueries {

    Connection con;

    public DatabaseQueries() {
        con = null;

    }

    public HashMap dbGetUserApps() {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            con = DatabaseConnection.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from usa_app where id_pessoa=1;");
            while (rs.next()) {
                list.add(rs.getInt(3));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return dbGetAppURL(list);
    }

    private HashMap dbGetAppURL(ArrayList<Integer> list) {
        HashMap<String, String> hash = new HashMap<>();
        try {
            con = DatabaseConnection.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            for (Integer i : list) {
                ResultSet rs = statement.executeQuery("SELECT * FROM app where id_app=" + i + ";");
                while (rs.next()) {
                    hash.put(rs.getString(2), rs.getString(3));
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return hash;
    }

    public ArrayList dbGetUserProfile() {
        ArrayList<String[]> list = new ArrayList<>();
        HashMap<String, HashMap> hash = new HashMap<>();
        HashMap<String, String> sub_hash = new HashMap<>();
        try {
            con = DatabaseConnection.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from usa_app where id_pessoa=" + 1 + ";");
            while (rs.next()) {
                String[] array = new String[3];

                //id app
                array[0] = rs.getString(3);
                //id perfil
                array[1] = rs.getString(2);
                //name perfil
                array[2] = dbGetProfileName(array[1]);
                
                
                list.add(array);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return list;

    }

    private String dbGetProfileName(String profileId) {
        String profileName = "";
        try {
            con = DatabaseConnection.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery("select * from perfil where id_perfil=" + profileId + ";");
            while (rs.next()) {
                profileName = rs.getString(2);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return profileName;
    }

}
