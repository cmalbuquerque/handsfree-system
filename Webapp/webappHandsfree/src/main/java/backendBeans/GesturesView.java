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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import services.GestoService;

/**
 *
 * @author nunos
 */
@ManagedBean(name = "gesturesview")
@ViewScoped
public class GesturesView implements Serializable {

    private List<Gesto> listGesto;
    private List<Gesto> allGestos;
    private List<Gesto> listAllUnsedGestos;
    
    private Profile selectedProfile;
    
    private int oldActionID, oldGestoID, newGestoID;

    
    private List<Gesto> listed = new ArrayList<Gesto>();

    private Gesto gesto;
    
    private HttpSession session;

    @ManagedProperty("#{gestoService}")
    private GestoService service;

    @PostConstruct
    public void init() {
        try {
            
            session = SessionUtils.getSession();
            listGesto = new ArrayList<>();
            allGestos = service.getAllGestos();
            listAllUnsedGestos = getUnsed();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GesturesView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public List<Gesto> getUnsed(){
        List<Gesto> unsed = new ArrayList<Gesto>();
        for(Gesto g : allGestos){
            if(!listGesto.contains(g))
                unsed.add(g);
        }
        return unsed;
    }

    public List<Gesto> getListAllUnsedGestos() {
        return listAllUnsedGestos;
    }

    public void setListAllUnsedGestos(List<Gesto> listAllUnsedGestos) {
        this.listAllUnsedGestos = listAllUnsedGestos;
    }

    public Profile getSelectedProfile() {
        return selectedProfile;
    }

    public void setSelectedProfile(Profile selectedProfile) {
        this.selectedProfile = selectedProfile;
    }
    
    
    
    

    public List<String> getListNome() {
        List<String> listName = listGesto.stream().map(Gesto::getNome).collect(Collectors.toList());
        //um de cada vez-->listName.stream().forEach(strGestureName -> System.out.println(strGestureName));
        return listName;
    }

    public List<Gesto> getAllGestos() {
        return allGestos;
    }

    public void setAllGestos(List<Gesto> allGestos) {
        this.allGestos = allGestos;
    }
    
    
    
    public List<Gesto> getListGesto() throws ClassNotFoundException {
        selectedProfile = (Profile) session.getAttribute("profile");
        return service.getGestosPerfil(selectedProfile.getId());
    }
    

    public Integer getNumberGestures() {
        return listGesto.size();
    }

    public List<Gesto> getListedGesto() {
        return listed;
    }


    public void setService(GestoService service) {
        this.service = service;
    }

    public Gesto getGesto() {
        return gesto;
    }

    public void setSelectedGesto(Gesto gesto) {
        this.gesto = gesto;
    }

    public int getOldActionID() {
        return oldActionID;
    }

    public void setOldActionID(int oldActionID) {
        this.oldActionID = oldActionID;
    }

    public int getOldGestoID() {
        return oldGestoID;
    }

    public void setOldGestoID(int oldGestoID) {
        this.oldGestoID = oldGestoID;
    }

    public int getNewGestoID() {
        return newGestoID;
    }

    public void setNewGestoID(int newGestoID) {
        this.newGestoID = newGestoID;
    }
    
    
    
    public String change() throws ClassNotFoundException {
        DataDAO.Update(oldActionID, oldGestoID, newGestoID);       
        return "profiles_gesture.xhtml";
    }
    

}
