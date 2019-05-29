/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
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
    private String  id;
    
    public Gesto(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public Gesto() {
    }
   
    public String getNome() {
        return nome;
    }
    public void setId(String nome) {
        this.nome = nome;
    }
    
    public String getId() {
        return id;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Gesto other = (Gesto) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
    
}
