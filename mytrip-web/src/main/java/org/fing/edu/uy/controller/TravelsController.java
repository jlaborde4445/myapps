/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fing.edu.uy.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import org.apache.commons.io.IOUtils;
import org.fing.edu.uy.context.Context;
import org.fing.edu.uy.entity.Travels;
import org.fing.edu.uy.entity.TravelsPlaces;
import org.fing.edu.uy.entity.TravelsPlacesHotels;
import org.fing.edu.uy.entity.TravelsPlacesTransports;
import org.fing.edu.uy.jpa.util.QueryParameter;
import org.fing.edu.uy.named.JPAServicesLocal;
import org.fing.edu.uy.named.JPATravelsPlacesHotels;
import org.fing.edu.uy.named.JPATravelsPlacesTransports;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author JORGE
 */
@ViewScoped
@ManagedBean
public class TravelsController implements Serializable {

    @Inject
    private JPAServicesLocal ejb;
    
    @Inject
    private JPATravelsPlacesHotels ejbTravelHotel;
    
    @Inject
    private JPATravelsPlacesTransports ejbTravelTransport;
    
    private Travels selectedItem;
    private TravelsPlaces selectedItemPlace;
    private TravelsPlacesHotels selectedItemTravelPlaceHotel;
    private TravelsPlacesTransports selectedItemTravelPlaceTransport;
    
    private boolean newItem;
    private boolean newItemPlace;
    private boolean newItemPlaceHotel;
    private boolean newItemPlaceTransport;

    public Travels getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Travels selectedItem) {
        this.selectedItem = selectedItem;
    }

    public boolean isNewItem() {
        return newItem;
    }

    public void setNewItem(boolean newItem) {
        this.newItem = newItem;
    }

    public boolean isNewItemPlace() {
        return newItemPlace;
    }

    public void setNewItemPlace(boolean newItemPlace) {
        this.newItemPlace = newItemPlace;
    }

    public boolean isNewItemPlaceHotel() {
        return newItemPlaceHotel;
    }

    public void setNewItemPlaceHotel(boolean newItemPlaceHotel) {
        this.newItemPlaceHotel = newItemPlaceHotel;
    }

    public boolean isNewItemPlaceTransport() {
        return newItemPlaceTransport;
    }

    public void setNewItemPlaceTransport(boolean newItemPlaceTransport) {
        this.newItemPlaceTransport = newItemPlaceTransport;
    }

    public TravelsPlaces getSelectedItemPlace() {
        return selectedItemPlace;
    }

    public void setSelectedItemPlace(TravelsPlaces selectedItemPlace) {
        this.selectedItemPlace = selectedItemPlace;
    }

    public TravelsPlacesHotels getSelectedItemTravelPlaceHotel() {
        return selectedItemTravelPlaceHotel;
    }

    public void setSelectedItemTravelPlaceHotel(TravelsPlacesHotels selectedItemTravelPlaceHotel) {
        this.selectedItemTravelPlaceHotel = selectedItemTravelPlaceHotel;
    }

    public TravelsPlacesTransports getSelectedItemTravelPlaceTransport() {
        return selectedItemTravelPlaceTransport;
    }

    public void setSelectedItemTravelPlaceTransport(TravelsPlacesTransports selectedItemTravelPlaceTransport) {
        this.selectedItemTravelPlaceTransport = selectedItemTravelPlaceTransport;
    }

    public List<Travels> getTravels() {
        return ejb.findWithNamedQuery(Travels.class, "Travels.findByIdUser",
                QueryParameter.with("id_user", Context.get().getUser()).
                parameters());
    }

    public void saveTravel() throws Exception {
        if (newItem) {
            selectedItem.setIdUser(Context.get().getUser());
            ejb.create(selectedItem);
        } else {
            ejb.update(selectedItem);
        }
        RequestContext.getCurrentInstance().execute("dialogInfo.hide()");
    }

    public void createTravel() {
        this.selectedItem = new Travels();
    }

    public void deleteTravel() throws Exception {
        ejb.remove(Travels.class, selectedItem.getIdTravel());
        this.selectedItem = null;
    }

    /**
     * Travels Places Functions 
     */
    public List<TravelsPlaces> getTravelsPlaces() {
        return ejb.findWithNamedQuery(TravelsPlaces.class, "TravelsPlaces.findByIdTravel",
                QueryParameter.with("idTravel", selectedItem).
                parameters());
    }

    public void saveTravelPlace() throws Exception {
        if (newItemPlace) {
            selectedItemPlace.setIdTravel(selectedItem);
            ejb.create(selectedItemPlace);
        } else {
            ejb.update(selectedItemPlace);
        }
        RequestContext.getCurrentInstance().execute("dialogInfo2.hide()");
    }

    public void createTravelPlace() {
        this.selectedItemPlace = new TravelsPlaces();
    }

    public void deleteTravelPlace() throws Exception {
        ejb.remove(TravelsPlaces.class, selectedItemPlace.getId());
        this.selectedItemPlace = null;
    }
    
    public void loadSelectPlace(){
        getTravelPlaceHotel();
        getTravelPlaceTransport();
    }
    
    /**
     * Travels Places Hotels Functions 
     */
    public void getTravelPlaceHotel() {
        try {
            selectedItemTravelPlaceHotel = ejb.findWithNamedQuerySingle(TravelsPlacesHotels.class, "TravelsPlacesHotels.findByIdTravelPlace",
                    QueryParameter.with("idTravelPlace", selectedItemPlace).
                    parameters());
        } catch (NoResultException ex) {
            selectedItemTravelPlaceHotel = null;
        }
    }

    public void saveTravelPlaceHotel() throws Exception {
        if (newItemPlaceHotel) {
            selectedItemTravelPlaceHotel.setIdTravelPlace(selectedItemPlace);
            selectedItemPlace.setTravelsPlacesHotels(selectedItemTravelPlaceHotel);
            ejb.update(selectedItemPlace);
        } else {
            ejb.update(selectedItemTravelPlaceHotel);
        }
        RequestContext.getCurrentInstance().update("panelHotelDetail");
        RequestContext.getCurrentInstance().execute("dialogInfoHotel.hide()");
    }

    public void createTravelPlaceHotel() {
        this.selectedItemTravelPlaceHotel = new TravelsPlacesHotels();
    }

    public void deleteTravelPlaceHotel() throws Exception {
        ejbTravelHotel.destroy(selectedItemTravelPlaceHotel.getId());
        this.selectedItemTravelPlaceHotel = null;
    }
    
    public void handleFileUploadHotel(FileUploadEvent event) throws Exception {
        selectedItemTravelPlaceHotel.setTicket(IOUtils.toByteArray(event.getFile().getInputstream()));
        ejb.update(selectedItemTravelPlaceHotel);
        FacesMessage msg = new FacesMessage("El archivo ", event.getFile().getFileName() + " fue subido correctamente.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public StreamedContent getFile() {
        byte[] fileBytes = selectedItemTravelPlaceHotel.getTicket();
        if(fileBytes != null){
            InputStream stream = new ByteArrayInputStream(fileBytes);
            return new DefaultStreamedContent(stream, "application/pdf", "ticketRef.pdf");
        }
        return null;
    }
    
    /**
     * Travels Places Transport Functions
     */
    public void getTravelPlaceTransport() {
        try {
            selectedItemTravelPlaceTransport = ejb.findWithNamedQuerySingle(TravelsPlacesTransports.class, "TravelsPlacesTransports.findByIdTravelPlace",
                    QueryParameter.with("idTravelPlace", selectedItemPlace).
                    parameters());
        } catch (NoResultException ex) {
            selectedItemTravelPlaceTransport = null;
        }
    }

    public void saveTravelPlaceTransport() throws Exception {
        if (newItemPlaceTransport) {            
            selectedItemTravelPlaceTransport.setIdTravelPlace(selectedItemPlace);
            selectedItemPlace.setTravelsPlacesTransports(selectedItemTravelPlaceTransport);
            ejb.update(selectedItemPlace);
        } else {
            ejb.update(selectedItemTravelPlaceTransport);
        }
        RequestContext.getCurrentInstance().update("panelTransportDetail");
        RequestContext.getCurrentInstance().execute("dialogInfoTransport.hide()");
    }

    public void createTravelPlaceTransport() {
        this.selectedItemTravelPlaceTransport = new TravelsPlacesTransports();
    }

    public void deleteTravelPlaceTransport() throws Exception {
        ejbTravelTransport.destroy(selectedItemTravelPlaceTransport.getId());
        this.selectedItemTravelPlaceTransport = null;
    }
    
    public void handleFileUploadTransport(FileUploadEvent event) throws Exception {
        selectedItemTravelPlaceTransport.setTicket(IOUtils.toByteArray(event.getFile().getInputstream()));
        ejb.update(selectedItemTravelPlaceTransport);
        FacesMessage msg = new FacesMessage("El archivo ", event.getFile().getFileName() + " fue subido correctamente.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public StreamedContent getFileTransport() {
        byte[] fileBytes = selectedItemTravelPlaceTransport.getTicket();
        if(fileBytes != null){
            InputStream stream = new ByteArrayInputStream(fileBytes);
            return new DefaultStreamedContent(stream, "application/pdf", "ticketRefTransport.pdf");
        }
        return null;
    }
    
}