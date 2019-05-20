/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendBeans;

import connectionDB.DataDAO;
import entities.Profile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Carolina
 */
@ManagedBean
@ViewScoped
public class DataListViewBean implements Serializable {

    private List<Profile> profiles;

    private Profile selectedProfile;

    @PostConstruct
    public void init() {
        profiles=addProfileData();
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public Profile getSelectedCar() {
        return selectedProfile;
    }

    public void setSelectedCar(Profile selectedProfile) {
        this.selectedProfile = selectedProfile;
    }

    public List<Profile> addProfileData() {
        return DataDAO.listProfilesNames();
    }

}
