package org.fing.edu.uy.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.fing.edu.uy.constants.SessionAttName;
import org.fing.edu.uy.context.UserContext;
import org.fing.edu.uy.entity.Users;
import org.fing.edu.uy.jpa.util.QueryParameter;
import org.fing.edu.uy.named.JPAServicesLocal;
import org.fing.edu.uy.utils.FacesUtils;

@RequestScoped
@ManagedBean
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private JPAServicesLocal ejb;
    
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String checkAccess() {
        try {
            Users user = ejb.findWithNamedQuerySingle(Users.class, "Users.findByUsernamePassword",
                    QueryParameter.with("username", username).and("password", password).parameters());
            setLogin(user);
            return "home?faces-redirect=true";
        } catch (Exception ex) {
            displayLoginError();
            return null;
        }
    }

    private void displayLoginError() {
        FacesUtils.addSev("Invalid username or password, verify the data and try again.");
    }

    private void setLogin(Users user) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserContext context = new UserContext();
        context.setUser(user);
        session.setAttribute(SessionAttName.USER_CONTEXT, context);
    }
    
}
