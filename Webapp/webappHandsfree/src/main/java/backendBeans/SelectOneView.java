/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendBeans;

import connectionDB.DataDAO;
import entities.Action;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Carolina
 */
@ManagedBean(name="selectOneView")
@ViewScoped
public class SelectOneView implements Serializable {
     
    private Action action; 
    private List<Action> actions;

     
    @PostConstruct
    public void init() {
        actions = getListActions();
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<Action> getActions() {
        return actions;
    }

    public List<Action> getListActions() {
        return DataDAO.getAllActions();
    }
    
    public void print(){
        System.out.println("PRINT");
        System.out.println(action);
    }
    
 
   
}