/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendBeans;

import connectionDB.DataDAO;
import connectionDB.SessionUtils;
import entities.Profile;
import entities.Voice;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carolina
 */

@ManagedBean(name = "voicesbean")
@ViewScoped
public class VoicesBean {

    private HttpSession session;

    private List<Voice> voices;
    private Voice selectedVoice;

    @PostConstruct
    public void init() {
        session = SessionUtils.getSession();
        voices=addVoices();
    }

    public List<Voice> getVoices() {
        return voices;
    }

    public void setVoices(List<Voice> voices) {
        this.voices = voices;
    }

    public Voice getSelectedVoice() {
        return selectedVoice;
    }

    public void setSelectedVoice(Voice selectedVoice) {
        this.selectedVoice = selectedVoice;
    }
   
    public List<Voice> addVoices() {
        Profile selectedProfile = (Profile) session.getAttribute("profile");
        System.out.println(selectedProfile.getId());
        return DataDAO.voiceCommands(selectedProfile);
    }

}
