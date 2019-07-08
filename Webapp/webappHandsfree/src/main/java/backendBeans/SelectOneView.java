/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendBeans;

import connectionDB.DataDAO;
import entities.Action;
import entities.Row;
import entities.Voice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
    private List<Row> listRow;
    private HashMap<Integer, Integer> map;

    @PostConstruct
    public void init() {
        actions = getListActions();
        voices = getListVoices();
        map = new HashMap<Integer,Integer>();
        listRow = new ArrayList<Row>();
    }


    public List<Row> getListRow() {
        return listRow;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public void setListRow(List<Row> listRow) {
        this.listRow = listRow;
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
        System.out.println("UNICO" + selectedAction);
        System.out.println("OLA" + selectedAction);
        System.out.println("XAU" + selectedVoice);

        Row row = new Row();
        row.setSelectAction(selectedAction);
        row.setSelectedVoice(selectedVoice);
        System.out.println(row.toString());

        System.out.println("ADEUS");
        listRow.add(row);
        System.out.println("SIZE: " + listRow.size());

        for (Row r : listRow) {
            System.out.println("ENTROU NO LOOP");
            System.out.println("R - ACTION : " + r.getSelectAction());
            System.out.println("R - Voice : " + r.getSelectedVoice());
            
            
        }
    }
//input type="button"

}
//onclick="document.location.reload(true)
//<h:outputText value="#{car.id}" />