/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Carolina
 */


public class Voice {
    
    private int id;
    
    private String voice;
    
    private Action action;

    public Voice(int id, String voice) {
        this.id = id;
        this.voice = voice;
    }  
    
    public Voice(int id, String voice, Action action) {
        this.id = id;
        this.voice = voice;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Voice other = (Voice) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Voice{" + "id=" + id + ", voice=" + voice + ", action=" + action + '}';
    }
    
    
    
    
    
    
    
    
    

    
}
