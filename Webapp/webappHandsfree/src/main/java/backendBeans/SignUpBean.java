/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendBeans;

import connectionDB.SignDAO;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import entities.Pessoa;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Andreia Patrocínio, Carolina Albuquerque, Diogo Jorge, Nuno Silva,
 * Pedro Pires
 *
 */
@ManagedBean(name = "signupbean")
@SessionScoped
public class SignUpBean implements Serializable {

    private final static String SIGNUP = "signup.xhtml";
    private final static String INDEX = "index.xhtml";

    private Pessoa user;

    private static int id = 0;

    @ManagedProperty(value = "#{email}")
    private String email;
    @ManagedProperty(value = "#{fullname}")
    private String fullname;
    @ManagedProperty(value = "#{password}")
    private String password;

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

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String register() throws ClassNotFoundException {
        id++;
        boolean valid = SignDAO.Sign(fullname, email, password);
        if (!valid) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Error",
                    "Please enter another email or another password..."));
            return SIGNUP;

        } else {
            return INDEX;
        }
    }

}
