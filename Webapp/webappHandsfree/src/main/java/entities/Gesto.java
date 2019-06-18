/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author nunos
 */

/*
<h:panelGrid columns="1" style="width:100%">
                        <!--<p:graphicImage name="demo/images/car/#{car.brand}.gif"/> -->

                        <!--<h:outputText value="#{car.year}" /> -->
*/
@ManagedBean(name = "gesto")
@SessionScoped
public class Gesto implements Serializable {
    
    
    private String nome;
    private int  id;
    
    private Action action;
    
    public Gesto(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public Gesto() {
    }
   
    public String getNome() {
        return nome;
    }
    
    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Gesto other = (Gesto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Gesto{" + "nome=" + nome + ", id=" + id + ", action=" + action + '}';
    }
    
    
    
   
    
}
