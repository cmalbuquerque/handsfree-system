/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendBeans;

import entities.Action;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import services.ActionService;

/**
 *
 * @author nunos
 */
@ManagedBean(name = "actionview")
@ViewScoped
public class ActionView implements Serializable {

    private List<Action> listAction;

    private List<Action> listed = new ArrayList<Action>();

    private Action action;

    @ManagedProperty("#{actionService}")
    private ActionService service;

    @PostConstruct
    public void init() {
        try {
            listAction = service.getAction();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GesturesView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Action> getListAction() {
        listAction = service.getList();
        return listAction;
    }

    public Integer getNumberAction() {
        listAction = service.getList();
        return listAction.size();
    }

    public List<Action> getListedAction() {
        return listed;
    }

    /*
    public String getNome() {
        return service.getGestoNome();
    }   
     */
    //se for diferente no if, adicionar depois do if. os que vem a seguir sao diferentes
    //se for igual, eliminar no fim.
    public String getNome() {
        String nome = null;
        for (Action action : getListAction()) {
            listed.add(action);
            for (Action done : listed) {
                if (action.getNome() == done.getNome()) {
                    nome = action.getNome();
                    
                }
            }
        }
        return nome;
    }

    public void setService(ActionService service) {
        this.service = service;
    }

    public Action getAction() {
        return action;
    }

    public void setSelectedAction(Action action) {
        this.action = action;
    }
    
    
/*
    public String display() {
        String nome = "";

        for (Action action : listAction) {
            System.out.println("name: " + action.getNome());
            System.out.println("" + action.getId() + ". " + action.getNome());
            nome += "\r\n ID:" + action.getId() + ". " + "Nome: " + action.getNome();
        }

        return nome;
    }
*/

    //string = ""
      //      string += cena de fazer paragrao mais valor

    /*
    String text = new String("<li>"+ name +"</li>");
    String htmlText = new String("<html><font color='red'>" + text + "</font></html>");
    JTextPane jTextPane =new JTextPane ();
    jTextPane.setContentType("text/html");
    jTextPane.setText(htmlText);
     */
    
    /*
    public String outro() {
        int size = getNumberGestures();
        System.out.println("SIZE: " + size);
        String nome = null;

        for (int i = 0; i < size; i++) {
            for (Gesto gesto : listGesto) {
                System.out.println("name: " + gesto.getNome());
                System.out.println("" + i + ". " + gesto.getNome());
                nome = "" + i + ". " + gesto.getNome();
                return nome;
            }
        }
        return nome;
    }
     */
}
