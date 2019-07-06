package backendBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import connectionDB.DataDAO;
import connectionDB.SessionUtils;
import entities.Gesto;
import entities.Profile;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nunos
 */
@ManagedBean(name = "changegesturebean")
@SessionScoped
public class ChangeGestureBean implements Serializable{    
    
    private final static String gesture = "profiles_gesture.xhtml";
    
    private HttpSession session;
    
    private Gesto gesto;
    private Profile selectedProfile;
    
    private int oldGestureID;
    private int newGestureID;
    private int oldActionID;
    
    @PostConstruct
    public void init() {
        session = SessionUtils.getSession();
    }

    public String change() throws ClassNotFoundException {
        System.out.println("OLA");
        oldGestureID = getOldGestureID();
        oldActionID = getOldActionID();
        newGestureID = getNewGestureID();
        int perfilID = getSelectedProfileID();
        int userID = getUserID();
        DataDAO.updateGesture(oldGestureID, oldActionID, newGestureID, perfilID, userID);
        return gesture;
    }

    public Profile getSelectedProfile() {
        return selectedProfile;
    }

    public void setSelectedProfile(Profile selectedProfile) {
        this.selectedProfile = selectedProfile;
    }

    public Gesto getGesto() {
        return gesto;
    }

    public void setGesto(Gesto gesto) {
        this.gesto = gesto;
    }
   
    public void setOldGestureID(int oldGestureID) {
        this.oldGestureID = oldGestureID;
    }
    
    public void setNewGestureID(int newGestureID) {
        this.newGestureID = newGestureID;
    }

    public void setOldActionID(int oldActionID) {
        this.oldActionID = oldActionID;
    }
     
    public int getNewGestureID() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        newGestureID = Integer.parseInt(params.get("newgestureID"));
        return newGestureID;
    }

    public int getOldGestureID(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        oldGestureID = Integer.parseInt(params.get("oldgestureID"));
        return oldGestureID;
    }

    public int getOldActionID(){ 
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        oldActionID = Integer.parseInt(params.get("oldactionID"));
        return oldActionID;
    }

    private int getUserID() {
        String email = (String) session.getAttribute("email");
        return DataDAO.getUserId(email);     
    }

    private int getSelectedProfileID() {
        selectedProfile = (Profile) session.getAttribute("selectedProfile");
        return selectedProfile.getId();
    }
    
}
