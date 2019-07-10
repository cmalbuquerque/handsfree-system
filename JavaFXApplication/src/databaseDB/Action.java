/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseDB;

/**
 *
 * @author Andreia Patrocínio
 */
public class Action {
    
    
    private String nome;
    private int id;

    public Action(int id, String nome) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Action{" + "nome=" + nome + ", id=" + id + '}';
    }
    
    
    
}
