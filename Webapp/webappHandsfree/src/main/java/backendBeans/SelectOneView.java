/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendBeans;

import connectionDB.DataDAO;
import connectionDB.SessionUtils;
import entities.Action;
import entities.Gesto;
import entities.Profile;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carolina
 */
@ManagedBean(name = "selectOneView")
@ViewScoped
public class SelectOneView implements Serializable {

    int selectedAction, selectedVoice, selectedGesture;
    private Profile selectedProfile;

    private HttpSession session;

    private List<Action> actions;
    private List<Voice> voices;
    private List<Voice> voicesUnsed;
    private List<Row> listRow;
    private HashMap<Integer, Integer> map;

    private List<Gesto> allGestures;

    @PostConstruct
    public void init() {
        session = SessionUtils.getSession();
        allGestures = addAllGestos();
        actions = getListActions();
        voices = getListVoices();
        map = new HashMap<Integer, Integer>();
        listRow = new ArrayList<Row>();
        voicesUnsed = new ArrayList<Voice>();
    }

    public int getSelectedGesture() {
        return selectedGesture;
    }

    public void setSelectedGesture(int selectedGesture) {
        this.selectedGesture = selectedGesture;
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

    public List<Voice> getVoicesUnsed() {
        return voicesUnsed;
    }

    public void setVoicesUnsed(List<Voice> voicesUnsed) {
        this.voicesUnsed = voicesUnsed;
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

    public Profile getSelectedProfile() {
        return selectedProfile;
    }

    public void setSelectedProfile(Profile selectedProfile) {
        this.selectedProfile = selectedProfile;
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

    public List<Gesto> getAllGestures() {
        return allGestures;
    }

    public void setAllGestures(List<Gesto> allGestures) {
        this.allGestures = allGestures;
    }

    public String addVoiceAction() {
        int actionListID = DataDAO.insertVoiceAction(selectedAction, selectedVoice);
        DataDAO.inserPerfilActionList(actionListID, selectedProfile);
        return "home.xhtml";
    }

    public String addGestureAction() {
        int actionListID = DataDAO.insertGestureAction(selectedAction, selectedGesture);
        DataDAO.inserPerfilActionList(actionListID, selectedProfile);
        return "home.xhtml";
    }

    public List<Gesto> addAllGestos() {
        return DataDAO.getAllGestos();
    }

    /*public List<Voice> addVoicesUnsed() {
        List<Voice> voicesUsed = addVoices();
        System.out.println(voices);
        System.out.println(voicesUsed);
        for (Voice v : voices) {
            if (!voicesUsed.contains(v)) {
                voicesUnsed.add(v);
            }
        }
        return voicesUnsed;
    }

    public List<Voice> addVoices() {
        Profile selectedProfile = (Profile) session.getAttribute("profile");
        return DataDAO.voiceCommands(selectedProfile);
    }
     */
}

//onclick="document.location.reload(true)
