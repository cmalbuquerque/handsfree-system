/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendBeans;

import connectionDB.DataDAO;
import entities.Action;
import entities.Voice;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Carolina
 */
@ManagedBean(name = "selectOneView")
@ViewScoped
public class SelectOneView implements Serializable {

    int selectedAction, selectedVoice;
    private List<Action> actions;
    private List<Voice> voices;
    
    private HashMap<Integer,Integer> map;

    @PostConstruct
    public void init() {
        actions = getListActions();
        voices = getListVoices();
        map = new HashMap<Integer,Integer>();
    }

    public int getSelectedAction() {
        return selectedAction;
    }

    public void setSelectedAction(int selectedAction) {
        this.selectedAction = selectedAction;
    }

    public int getSelectedVoice() {
        return selectedVoice;
    }

    public void setSelectedVoice(int selectedVoice) {
        this.selectedVoice = selectedVoice;
    }

    public List<Action> getActions() {
        return actions;
    }

    public List<Voice> getVoices() {
        return voices;
    }

    public void setVoices(List<Voice> voices) {
        this.voices = voices;
    }

    public List<Action> getListActions() {
        return DataDAO.getAllActions();
    }

    public List<Voice> getListVoices() {
        return DataDAO.getAllVoicesWithoutActions();
    }

    public HashMap<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, Integer> map) {
        this.map = map;
    }

    public void onAddNew() throws ClassNotFoundException {
        map.put(selectedAction, selectedVoice);
    }


}
