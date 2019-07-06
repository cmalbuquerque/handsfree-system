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
    private List<Gesto> listAllGesto;

    private List<Gesto> listed = new ArrayList<Gesto>();

    private Gesto gesto;

    @ManagedProperty("#{gestoService}")
    private GestoService service;

    @PostConstruct
    public void init() {
        try {
            listGesto = service.getGestos();
            listAllGesto = service.getAllGestos();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GesturesView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> getListNome() {
        listGesto = service.getList();
        List<String> listName = listGesto.stream().map(Gesto::getNome).collect(Collectors.toList());
        //um de cada vez-->listName.stream().forEach(strGestureName -> System.out.println(strGestureName));
        return listName;
    }
    
    public List<Gesto> getListGesto() {
        listGesto = service.getList();
        return listGesto;
    }
    

    public Integer getNumberGestures() {
        listGesto = service.getList();
        return listGesto.size();
    }

    public List<Gesto> getListedGesto() {
        return listed;
    }

    public List<Gesto> getListAllGesto() {
        return listAllGesto;
    }

    public void setListAllGesto(List<Gesto> listAllGesto) {
        this.listAllGesto = listAllGesto;
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

    /*
    public String display() {
        String nome = "";

        for (Gesto gesto : listGesto) {
            System.out.println("name: " + gesto.getNome());
            System.out.println("" + gesto.getId() + ". " + gesto.getNome());
            nome += "\r\n ID:" + gesto.getId() + ". " + "Nome: " + gesto.getNome();
        }

        return nome;
    }
     */
    


    //string = ""
      //      string += cena de fazer paragrao mais valor

    /*
    String text = new String("<li>"+ name +"</li>");
    String htmlText = new String("<html><font color='red'>" + text + "</font></html>");
    JTextPane jTextPane =new JTextPane ();
    jTextPane.setContentType("text/html");
    jTextPane.setText(htmlText);
     */
    
    /*
    public String outro() {
        int size = getNumberGestures();
        System.out.println("SIZE: " + size);
        String nome = null;

        for (int i = 0; i < size; i++) {
            for (Gesto gesto : listGesto) {
                System.out.println("name: " + gesto.getNome());
                System.out.println("" + i + ". " + gesto.getNome());
                nome = "" + i + ". " + gesto.getNome();
                return nome;
            }
        }
        return nome;
    }
     */
}
