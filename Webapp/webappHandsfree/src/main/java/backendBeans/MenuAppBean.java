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
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carolina
 */
@ManagedBean(name = "menuappbean")
@ViewScoped
public class MenuAppBean implements Serializable {

    private String email;
    
    private List<App> apps;

    private App selectedApp;
    
    private Profile selectedProfile;

    private HttpSession session;

    @PostConstruct
    public void init() {
        session = SessionUtils.getSession();
        apps = addAppData();
    }

    public Profile getSelectedProfile() {
        return selectedProfile;
    }

    public void setSelectedProfile(Profile selectedProfile) {
        this.selectedProfile = selectedProfile;
    }

    
    
    public List<App> getApps() {
        return apps;
    }

    public App getSelectedApp() {
        return selectedApp;
    }

    public void setSelectedApp(App selectedApp) {
        this.selectedApp = selectedApp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<App> addAppData() {
        String email = (String) session.getAttribute("email");
        return DataDAO.listApps(email);
    }

    public String showGestureCommands() {
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("selectedApp", selectedApp);
        session.setAttribute("email", email);
        session.setAttribute("profile", selectedProfile);
        return "profiles_gesture.xhtml";
    }

    public String showVoiceCommands() {
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("email", email);
        session.setAttribute("app", selectedApp);
        session.setAttribute("profile", selectedProfile);
        return "profiles_voice.xhtml";
    }

}
