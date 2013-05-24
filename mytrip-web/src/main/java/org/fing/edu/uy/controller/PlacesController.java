/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fing.edu.uy.controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.fing.edu.uy.entity.Places;
import org.fing.edu.uy.named.JPAServicesLocal;
import org.primefaces.context.RequestContext;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author JORGE
 */
@ViewScoped
@ManagedBean
public class PlacesController implements Serializable {
    
    @Inject
    private JPAServicesLocal ejb;
    
    private Places selectedItem;
    private MapModel simpleModel;
    private boolean newItem;
    
    public Places getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Places selectedItem) {
        this.selectedItem = selectedItem;
    }

    public boolean isNewItem() {
        return newItem;
    }

    public void setNewItem(boolean newItem) {
        this.newItem = newItem;
    }

    public List<Places> getPlaces(){
        return ejb.findWithNamedQuery(Places.class, "Places.findAll");
    }
    
    public MapModel getMapModel() {
        return simpleModel;
    }
    
    /**
     * Ajax Events from View 
     **/  
    public void onPointSelect(PointSelectEvent event) {  
        LatLng latlng = event.getLatLng();
        selectedItem.setLatitude(latlng.getLat());
        selectedItem.setLongitude(latlng.getLng());
        simpleModel = new DefaultMapModel();
        simpleModel.addOverlay(new Marker(latlng, selectedItem.getCountry() + " / "+ selectedItem.getPlace()));
    }

    /**
     * Actions from View 
     **/
    public void updateMapView(){
        simpleModel = new DefaultMapModel();
        
        if(selectedItem.getLatitude() != 0 && selectedItem.getLongitude() != 0){
            LatLng latlng = new LatLng(selectedItem.getLatitude(), selectedItem.getLongitude());
            simpleModel.addOverlay(new Marker(latlng, selectedItem.getCountry() + " / "+ selectedItem.getPlace()));
        }
        
        RequestContext.getCurrentInstance().execute("dlg.show();");
    }
    
    public void savePalce() throws Exception {
        if(selectedItem.getLatitude() != 0 && selectedItem.getLatitude() != 0){
            if(newItem){
                ejb.create(selectedItem);
            } else {
                ejb.update(selectedItem);
            }
            RequestContext.getCurrentInstance().execute("dialogInfoPlaces.hide()");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Debe seleccionar la geolocalización del lugar en el mapa."));
        }
    }
    
    public void createPlace() {
        this.selectedItem = new Places();
    }
    
    public void deletePlace() throws Exception {
        ejb.remove(Places.class, selectedItem.getIdPlace());
        this.selectedItem = null;
    }

}