/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fing.edu.uy.controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.fing.edu.uy.entity.Users;
import org.fing.edu.uy.named.JPAServicesLocal;
import org.primefaces.context.RequestContext;

/**
 *
 * @author JORGE
 */
@ViewScoped
@ManagedBean
public class UserController implements Serializable {
    
    @Inject
    private JPAServicesLocal ejb;
    
    private Users selectedItem;
    private boolean newItem;

    public Users getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Users selectedItem) {
        this.selectedItem = selectedItem;
    }

    public boolean isNewItem() {
        return newItem;
    }

    public void setNewItem(boolean newItem) {
        this.newItem = newItem;
    }

    public List<Users> getUsers(){
        return ejb.findWithNamedQuery(Users.class, "Users.findAll");
    }

    public void saveUser() throws Exception {
        if(newItem){
            ejb.create(selectedItem);
        } else {
            ejb.update(selectedItem);
        }
        RequestContext.getCurrentInstance().execute("dialogInfoUser.hide()");
    }
    
    public void createUser() {
        this.selectedItem = new Users();
    }
    
    public void deleteUser() throws Exception {
        ejb.remove(Users.class, selectedItem.getIdUser());
        this.selectedItem = null;
    }

}