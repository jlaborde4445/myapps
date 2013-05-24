/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fing.edu.uy.named;

import java.io.Serializable;
import javax.persistence.EntityNotFoundException;
import org.fing.edu.uy.entity.TravelsPlaces;
import org.fing.edu.uy.entity.Hotels;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.fing.edu.uy.entity.TravelsPlacesHotels;
import org.fing.edu.uy.persistence.exceptions.IllegalOrphanException;
import org.fing.edu.uy.persistence.exceptions.NonexistentEntityException;
import org.fing.edu.uy.persistence.exceptions.RollbackFailureException;

/**
 *
 * @author JORGE
 */
@Named
public class JPATravelsPlacesHotels implements Serializable {

    @PersistenceContext(unitName = "mytrip_PU")
    private EntityManager em;
    
    @Resource
    UserTransaction utx;

    public void create(TravelsPlacesHotels travelsPlacesHotels) throws Exception {
        List<String> illegalOrphanMessages = null;
        TravelsPlaces idTravelPlaceOrphanCheck = travelsPlacesHotels.getIdTravelPlace();
        if (idTravelPlaceOrphanCheck != null) {
            TravelsPlacesHotels oldTravelsPlacesHotelsOfIdTravelPlace = idTravelPlaceOrphanCheck.getTravelsPlacesHotels();
            if (oldTravelsPlacesHotelsOfIdTravelPlace != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The TravelsPlaces " + idTravelPlaceOrphanCheck + " already has an item of type TravelsPlacesHotels whose idTravelPlace column cannot be null. Please make another selection for the idTravelPlace field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        try {
            utx.begin();
            TravelsPlaces idTravelPlace = travelsPlacesHotels.getIdTravelPlace();
            if (idTravelPlace != null) {
                idTravelPlace = em.getReference(idTravelPlace.getClass(), idTravelPlace.getId());
                travelsPlacesHotels.setIdTravelPlace(idTravelPlace);
            }
            Hotels idHotel = travelsPlacesHotels.getIdHotel();
            if (idHotel != null) {
                idHotel = em.getReference(idHotel.getClass(), idHotel.getIdHotel());
                travelsPlacesHotels.setIdHotel(idHotel);
            }
            em.persist(travelsPlacesHotels);
            if (idTravelPlace != null) {
                idTravelPlace.setTravelsPlacesHotels(travelsPlacesHotels);
                em.merge(idTravelPlace);
            }
            if (idHotel != null) {
                idHotel.getTravelsPlacesHotelsList().add(travelsPlacesHotels);
                em.merge(idHotel);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        }
    }

    public void edit(TravelsPlacesHotels travelsPlacesHotels) throws Exception {
        try {
            utx.begin();
            TravelsPlacesHotels persistentTravelsPlacesHotels = em.find(TravelsPlacesHotels.class, travelsPlacesHotels.getId());
            TravelsPlaces idTravelPlaceOld = persistentTravelsPlacesHotels.getIdTravelPlace();
            TravelsPlaces idTravelPlaceNew = travelsPlacesHotels.getIdTravelPlace();
            Hotels idHotelOld = persistentTravelsPlacesHotels.getIdHotel();
            Hotels idHotelNew = travelsPlacesHotels.getIdHotel();
            List<String> illegalOrphanMessages = null;
            if (idTravelPlaceNew != null && !idTravelPlaceNew.equals(idTravelPlaceOld)) {
                TravelsPlacesHotels oldTravelsPlacesHotelsOfIdTravelPlace = idTravelPlaceNew.getTravelsPlacesHotels();
                if (oldTravelsPlacesHotelsOfIdTravelPlace != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The TravelsPlaces " + idTravelPlaceNew + " already has an item of type TravelsPlacesHotels whose idTravelPlace column cannot be null. Please make another selection for the idTravelPlace field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTravelPlaceNew != null) {
                idTravelPlaceNew = em.getReference(idTravelPlaceNew.getClass(), idTravelPlaceNew.getId());
                travelsPlacesHotels.setIdTravelPlace(idTravelPlaceNew);
            }
            if (idHotelNew != null) {
                idHotelNew = em.getReference(idHotelNew.getClass(), idHotelNew.getIdHotel());
                travelsPlacesHotels.setIdHotel(idHotelNew);
            }
            travelsPlacesHotels = em.merge(travelsPlacesHotels);
            if (idTravelPlaceOld != null && !idTravelPlaceOld.equals(idTravelPlaceNew)) {
                idTravelPlaceOld.setTravelsPlacesHotels(null);
                idTravelPlaceOld = em.merge(idTravelPlaceOld);
            }
            if (idTravelPlaceNew != null && !idTravelPlaceNew.equals(idTravelPlaceOld)) {
                idTravelPlaceNew.setTravelsPlacesHotels(travelsPlacesHotels);
                em.merge(idTravelPlaceNew);
            }
            if (idHotelOld != null && !idHotelOld.equals(idHotelNew)) {
                idHotelOld.getTravelsPlacesHotelsList().remove(travelsPlacesHotels);
                idHotelOld = em.merge(idHotelOld);
            }
            if (idHotelNew != null && !idHotelNew.equals(idHotelOld)) {
                idHotelNew.getTravelsPlacesHotelsList().add(travelsPlacesHotels);
                em.merge(idHotelNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = travelsPlacesHotels.getId();
                if (findTravelsPlacesHotels(id) == null) {
                    throw new NonexistentEntityException("The travelsPlacesHotels with id " + id + " no longer exists.");
                }
            }
            throw ex;
        }
    }

    public void destroy(Integer id) throws Exception {
        try {
            utx.begin();
            TravelsPlacesHotels travelsPlacesHotels;
            try {
                travelsPlacesHotels = em.getReference(TravelsPlacesHotels.class, id);
                travelsPlacesHotels.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The travelsPlacesHotels with id " + id + " no longer exists.", enfe);
            }
            TravelsPlaces idTravelPlace = travelsPlacesHotels.getIdTravelPlace();
            if (idTravelPlace != null) {
                idTravelPlace.setTravelsPlacesHotels(null);
                em.merge(idTravelPlace);
            }
            Hotels idHotel = travelsPlacesHotels.getIdHotel();
            if (idHotel != null) {
                idHotel.getTravelsPlacesHotelsList().remove(travelsPlacesHotels);
                em.merge(idHotel);
            }
            em.createQuery("DELETE FROM TravelsPlacesHotels t WHERE t.id = " + id).executeUpdate();
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        }
            
    }

    public TravelsPlacesHotels findTravelsPlacesHotels(Integer id) {
        return em.find(TravelsPlacesHotels.class, id);
    }
    
}
