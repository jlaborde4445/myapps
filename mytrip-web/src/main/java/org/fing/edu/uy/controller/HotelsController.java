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
import org.fing.edu.uy.entity.Hotels;
import org.fing.edu.uy.named.JPAServicesLocal;
import org.primefaces.context.RequestContext;

/**
 *
 * @author JORGE
 */
@ViewScoped
@ManagedBean
public class HotelsController implements Serializable {
    
    @Inject
    private JPAServicesLocal ejb;
    
    private Hotels selectedItem;
    private boolean newItem;
    
    public Hotels getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Hotels selectedItem) {
        this.selectedItem = selectedItem;
    }

    public boolean isNewItem() {
        return newItem;
    }

    public void setNewItem(boolean newItem) {
        this.newItem = newItem;
    }

    public List<Hotels> getHotels(){
        return ejb.findWithNamedQuery(Hotels.class, "Hotels.findAll");
    }

    /**
     * Actions from View 
     **/
    public void saveHotel() throws Exception {
        if(newItem){
            ejb.create(selectedItem);
        } else {
            ejb.update(selectedItem);
        }
        RequestContext.getCurrentInstance().execute("dialogInfoHotels.hide()");
    }
    
    public void createHotel() {
        this.selectedItem = new Hotels();
    }
    
    public void deleteHotel() throws Exception {
        ejb.remove(Hotels.class, selectedItem.getIdHotel());
        this.selectedItem = null;
    }

}