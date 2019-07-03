/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendBeans;
import connectionDB.DataDAO;
import entities.Action;
import entities.ActionList;
import entities.Voice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
/**
 *
 * @author Carolina
 */
@ManagedBean(name="dtAddRowView")
@ViewScoped
public class AddRowView implements Serializable {
     
    private List<ActionList> actionsList;
    
    private Action selectedAction; 
    private List<Action> actions;
    
    private List<Voice> selectedVoices;
    private List<Voice> allVoices;
 
    /*@ManagedProperty("#{carService}")
    private CarService service;
     */
    
    
    @PostConstruct
    public void init() {
        actionsList = new ArrayList<ActionList>();
        selectedVoices = new ArrayList<Voice>();
        actions = getAllActions();
        allVoices = getVoices();
    }

    public List<ActionList> getActionsList() {
        return actionsList;
    }

    public void setActionsList(List<ActionList> actionsList) {
        this.actionsList = actionsList;
    }
    public Action getSelectedAction() {
        return selectedAction;
    }

    public void setSelectedAction(Action selectedAction) {
        this.selectedAction = selectedAction;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    private List<Action> getAllActions() {
        return DataDAO.getAllActions();
    }
    
    public List<Voice> getSelectedVoices() {
        return selectedVoices;
    }

    public void setSelectedVoices(List<Voice> selectedVoices) {
        this.selectedVoices = selectedVoices;
    }

    public void setAllVoices(List<Voice> allVoices) {
        this.allVoices = allVoices;
    }

    public List<Voice> getAllVoices() {
        return allVoices;
    }
    
    private List<Voice> getVoices() {
        return DataDAO.getAllVoicesWithoutActions();
    }
     
    public void onAddNew() throws ClassNotFoundException {
        System.out.println(selectedVoices);
        for(Voice v : selectedVoices){
            DataDAO.insertVoicesActionList(selectedAction.getId(), v.getId());
        }
        // Add one new car to the table:
        //ActionList actionListAdd = service.createCars(1).get(0);
        //cars1.add(car2Add);
        //FacesMessage msg = new FacesMessage("New Car added", car2Add.getId());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
}