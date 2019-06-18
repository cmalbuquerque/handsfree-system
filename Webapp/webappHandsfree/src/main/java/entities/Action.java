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
@ManagedBean(name = "action")
@SessionScoped
public class Action implements Serializable {

    private String nome;
    private int id;

    public Action(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Action() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Action{" + "id=" + id + ", nome=" + nome + '}';
    }

}
