/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendBeans;

import connectionDB.ChangeGesture;
import connectionDB.SignDAO;
import entities.Gesto;
import entities.Pessoa;
import java.io.Serializable;
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
    //RECEBER A PUTA DO NOME DO GESTO A MUDAR
    
    
    private final static String gesture = "profiles_gesture.xhtml";
    
    @ManagedProperty(value = "#{gesto}")
    private String nome;
    
    private Gesto gesto;
    
    public String change() throws ClassNotFoundException {
        ChangeGesture.GetGestureID(nome);
        System.out.println("------- ");
        return gesture;
    }
    
    //ver esta parte bem
    /*
    @PostConstruct
    private void init() {
        gesto = new Gesto();
    }
    */

    public Gesto getGesto() {
        return gesto;
    }

    public void setGesto(Gesto gesto) {
        this.gesto = gesto;
    }

    public String getNome() {
        return nome;
    }
}
