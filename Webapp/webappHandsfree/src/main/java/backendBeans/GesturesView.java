/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendBeans;

import entities.Gesto;
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
    
    private int oldActionID, oldGestoID, newGestoID;

    
    private List<Gesto> listed = new ArrayList<Gesto>();

    private Gesto gesto;

    @ManagedProperty("#{gestoService}")
    private GestoService service;

    @PostConstruct
    public void init() {
        try {
            listGesto = service.getGestos();
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
    
    
    
    public List<Gesto> getListGesto() {
        return listGesto;
    }
    

    public Integer getNumberGestures() {
        return listGesto.size();
    }

    public List<Gesto> getListedGesto() {
        return listed;
    }

    /*
    public String getNome() {
        return service.getGestoNome();
    }   
     */
    //se for diferente no if, adicionar depois do if. os que vem a seguir sao diferentes
    //se for igual, eliminar no fim.
    public String getNome() {
    
        String nome = null;
        for (Gesto gesto : getListGesto()) {
            listed.add(gesto);
            for (Gesto done : listed) {
                if (gesto.getNome() == done.getNome()) {
                    nome = gesto.getNome();
                }
            }
        }
        return nome;
        
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
    
    

}
