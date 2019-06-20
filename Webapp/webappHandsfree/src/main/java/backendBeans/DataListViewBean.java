/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendBeans;

import connectionDB.DataDAO;
import connectionDB.SessionUtils;
import entities.Gesto;
import entities.Profile;
import entities.Voice;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import services.GestoService;

/**
 *
 * @author Carolina
 */
@ManagedBean(name = "dataListView")
@ViewScoped
public class DataListViewBean implements Serializable {

    private List<Profile> profiles;
    private Profile selectedProfile;

    private HttpSession session;

    @PostConstruct
    public void init() {
        session = SessionUtils.getSession();
        profiles = addProfileData();
    }

    public Profile getSelectedProfile() {
        return selectedProfile;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }
    
    public void setSelectedProfile(Profile selectedProfile) {   
        this.selectedProfile = selectedProfile;
    }

    public List<Profile> addProfileData() {
        String email = (String) session.getAttribute("email");
        System.out.println("******** Session " + email);
        return DataDAO.listProfiles(email);
    }

    public List<Gesto> addGestos() throws ClassNotFoundException {
        return GestoService.getGestos();
    }

}
