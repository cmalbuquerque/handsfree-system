/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendBeans;

import connectionDB.DataDAO;
import connectionDB.SessionUtils;
import entities.App;
import java.io.Serializable;
import java.util.List;
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

    private List<String> apps;

    private List<String> profilesApp;

    private App selectedApp;

    private HttpSession session;

    @PostConstruct
    public void init() {
        session = SessionUtils.getSession();
        apps = addAppData();
        //profilesApp = addProfilesOfApp();
    }

    public List<String> getApps() {
        return apps;
    }

    public App getSelectedApp() {
        return selectedApp;
    }

    public List<String> getProfilesApp() {
        return profilesApp;
    }

    public void setProfilesApp(List<String> profilesApp) {
        this.profilesApp = profilesApp;
    }

    public void setSelectedCar(App selectedApp) {
        this.selectedApp = selectedApp;
    }

    public List<String> addAppData() {
        String email = (String) session.getAttribute("email");
        return DataDAO.listAppsNames(email);
    }

    public List<String> addProfilesOfApp() {
        String email = (String) session.getAttribute("email");
        return DataDAO.listProfilesOfApp(selectedApp, email);
    }

    public String showDetails() {
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("app", selectedApp);
        return "profiles_gesture.xhtml";
    }

}
