/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fing.edu.uy.controller;

import java.util.Collections;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.fing.edu.uy.context.Context;
import org.fing.edu.uy.entity.Hotels;
import org.fing.edu.uy.entity.Places;
import org.fing.edu.uy.jpa.util.QueryParameter;
import org.fing.edu.uy.named.JPAServicesLocal;

/**
 *
 * @author JORGE
 */
@Named(value = "requestController")
@RequestScoped
public class RequestController {

    @Inject
    private JPAServicesLocal ejb;

    public List<Places> getAllPlaces() {
        return ejb.findWithNamedQuery(Places.class, "Places.findAll");
    }

    public List<Places> getPlacesWithOut(int without) {
        if(without > 0){
            return ejb.findWithNamedQuery(Places.class, "Places.findByIdPlaceWithOut", 
                    QueryParameter.with("idPlace", without).parameters());
        }
        return Collections.<Places>emptyList();
    }
    
    public List<Hotels> getHotelByPlace(Places item) {
        if(item != null){
            return ejb.findWithNamedQuery(Hotels.class, "Hotels.findByPlace", 
                    QueryParameter.with("idPlace", item).parameters());
        }
        return Collections.<Hotels>emptyList();
    }
}
