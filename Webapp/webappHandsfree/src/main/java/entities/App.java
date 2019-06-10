/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Carolina
 */
public class App {
    
    private int id;
    
    private String nome;
    
    private List<Profile> profiles;

    public App(){}
    
    public App(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public App(int id, String nome, List<Profile> profiles) {
        this.id = id;
        this.nome = nome;
        this.profiles = profiles;
    }
    
    
    

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public String toString() {
        return "App{" + "id=" + id + ", nome=" + nome + ", profiles=" + profiles + '}';
    }
    
    
}
