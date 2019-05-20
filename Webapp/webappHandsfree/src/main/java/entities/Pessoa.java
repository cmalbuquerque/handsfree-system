/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Andreia Patrocínio, Carolina Albuquerque, Diogo Jorge, Nuno Silva,
 * Pedro Pires
 *
 */

public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private int profile;

    private String email;

    private String password;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.email);
        return hash;
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
        return "Utilizador com email " + email + "}";
    }
}
