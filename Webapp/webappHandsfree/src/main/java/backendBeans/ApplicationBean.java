/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendBeans;

import connectionDB.DataDAO;
import connectionDB.SessionUtils;
import entities.App;
import entities.Profile;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carolina
 */
@ManagedBean(name = "applicationBean")
@ViewScoped
public class ApplicationBean implements Serializable {

    private HttpSession session;
    
    private Profile profileToAdd;

    List<Profile> unsedProfilesbyApp;

    @PostConstruct
    public void init() {
        session = SessionUtils.getSession();
        unsedProfilesbyApp = getUnsedProfilesOfApp();
    }

    public List<Profile> getUnsedProfilesbyApp() {
        return unsedProfilesbyApp;
    }

    public void setUnsedProfilesbyApp(List<Profile> unsedProfilesbyApp) {
        this.unsedProfilesbyApp = unsedProfilesbyApp;
    }

    public Profile getProfileToAdd() {
        return profileToAdd;
    }

    public void setProfileToAdd(Profile profileToAdd) {
        this.profileToAdd = profileToAdd;
    }
    
    

    private List<Profile> getUnsedProfilesOfApp() {
        App selectedApp = (App) session.getAttribute("selectedApp");
        String email = (String) session.getAttribute("email");
        List<Profile> list = new ArrayList<>();
        List<Profile> allProfiles = DataDAO.getAllProfilesUser(email);
        List<Profile> profilesUsed = DataDAO.listProfilesOfApp(selectedApp, email);
        for (Profile p : allProfiles) {
            if (!profilesUsed.contains(p)) {
                list.add(p);
            }
        }
        return list;

    }

    public String addProfile() throws ClassNotFoundException {
        App selectedApp = (App) session.getAttribute("selectedApp");
        String email = (String) session.getAttribute("email");
        int userID = DataDAO.getUserId(email);
        DataDAO.addProfiletoApp(selectedApp, profileToAdd, userID);
        return "profiles_gesture.xhtml";
    }


}
