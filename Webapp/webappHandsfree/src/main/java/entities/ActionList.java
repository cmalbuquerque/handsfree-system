/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Carolina
 */
public class ActionList {
    
    private int id;
    private Action action;
    private Voice voice;
    private Gesto gesto;

    
    public ActionList(){}

    public ActionList(int id, Action action, Voice voice) {
        this.id = id;
        this.action = action;
        this.voice = voice;
    }

    public ActionList(int id, Action action, Gesto gesto) {
        this.id = id;
        this.action = action;
        this.gesto = gesto;
    }
    
    public ActionList(int id, Action action, Voice voice, Gesto gesto) {
        this.id = id;
        this.action = action;
        this.voice = voice;
        this.gesto = gesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    public Gesto getGesto() {
        return gesto;
    }

    public void setGesto(Gesto gesto) {
        this.gesto = gesto;
    }
    
    

    @Override
    public String toString() {
        return "ActionList{" + "action=" + action + ", voice=" + voice + ", gesto=" + gesto + '}';
    }
    
    
    
    
    
    
}
