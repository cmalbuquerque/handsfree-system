/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Andreia Patrocínio, Carolina Albuquerque, Diogo Jorge, Nuno Silva,
 * Pedro Pires
 *
 */

public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int id;
    private String email;
    private String password;
    
    private List<App> apps;
    private List<Profile> profiles;

    public Pessoa(){}
    
    public Pessoa(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Pessoa(int id, String email, String password, List<App> apps, List<Profile> profiles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.apps = apps;
        this.profiles = profiles;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        this.apps = apps;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getID() {
        return id;
    }
    
    public void setID(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.email);
        return hash;
    }
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            System.out.println("segundo é null");
            return false;
        }
        if (getClass() != obj.getClass()) {
            System.out.println("classes diferentes");
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.id, other.id)) {
            System.out.println("ids diferentes: " + this.id + "  " + other.id);
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            System.out.println("emails diferentes: " + this.email + "  " + other.email);
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {

            System.out.println("passwords diferentes\n" + this.password + " != " + other.password);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", email=" + email + ", password=" + password + ", apps=" + apps + ", profiles=" + profiles + '}';
    }

}
