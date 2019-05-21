/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendBeans;

import connectionDB.LoginDAO;
import connectionDB.SessionUtils;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import entities.Pessoa;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andreia Patrocínio, Carolina Albuquerque, Diogo Jorge, Nuno Silva,
 * Pedro Pires
 *
 */
@ManagedBean(name = "authenticationbean")
@SessionScoped
public class AuthenticationBean implements Serializable {
    
    private Pessoa user;
    @ManagedProperty(value = "#{email}")
    private String email;
    @ManagedProperty(value = "#{password}")
    private String password;

    private final static String INDEX = "index.xhtml";
    private final static String HOME = "home.xhtml";

    @PostConstruct
    private void init() {
        user = new Pessoa();
    }

    public Pessoa getUser() {
        return user;
    }

    public void setUser(Pessoa user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String validate() throws ClassNotFoundException {
        boolean valid = LoginDAO.validate(email, password);
        if (valid) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("email", email);
            return HOME;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Email and Passowrd",
                            "Please enter correct username and Password"));
            return INDEX;
        }
    }

    //logout event, invalidate session
    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (null != session) 
            session.invalidate();
        return INDEX;
    }

}
