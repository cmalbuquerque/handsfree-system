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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carolina
 */
@ManagedBean(name = "profilesbean")
@ViewScoped
public class ProfilesView implements Serializable {
    
    private String email;

    private App selectedApp;
    
    private List<Profile> profilesApp;

    private HttpSession session;

    
    @PostConstruct
    public void init() {
        session = SessionUtils.getSession();
        System.out.println("INIT");
        profilesApp = addProfilesOfApp();
    }
    
    
    public List<Profile> getProfilesApp() {
        return profilesApp;
    }

    public void setProfilesApp(List<Profile> profilesApp) {
        this.profilesApp = profilesApp;
    }

    public void setSelectedApp(App selectedApp) {
        this.selectedApp = selectedApp;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<Profile> addProfilesOfApp() {
        System.out.println("ENTREI AQUI");
        email = (String) session.getAttribute("email");
        selectedApp = (App) session.getAttribute("selectedApp");
        System.out.println(email + "" + selectedApp);
        return DataDAO.listProfilesOfApp(selectedApp, email);
    }

}
