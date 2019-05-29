/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendBeans;

import entities.Gesto;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import services.GestoService;

/**
 *
 * @author nunos
 */
@ManagedBean(name = "gesturesbean")
@ViewScoped
public class GesturesBean implements Serializable {
     
    private List<Gesto> listGesto;
     
    private Gesto gesto;
     
    @ManagedProperty("#{gestoService}")
    private GestoService service;
     
    
    @PostConstruct
    public void init() {
        try {
            listGesto = service.getGestos();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GesturesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Gesto> getListGesto() {
        //listGesto
        return service.getList();
    }
 
    public void setService(GestoService service) {
        this.service = service;
    }
 
    public Gesto getGesto() {
        return gesto;
    }
 
    public void setSelectedCar(Gesto gesto) {
        this.gesto = gesto;
    }
}
