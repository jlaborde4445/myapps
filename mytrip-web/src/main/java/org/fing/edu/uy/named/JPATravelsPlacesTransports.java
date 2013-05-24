/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fing.edu.uy.named;

import java.io.Serializable;
import javax.persistence.EntityNotFoundException;
import org.fing.edu.uy.entity.TravelsPlaces;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.fing.edu.uy.entity.TravelsPlacesTransports;
import org.fing.edu.uy.persistence.exceptions.IllegalOrphanException;
import org.fing.edu.uy.persistence.exceptions.NonexistentEntityException;
import org.fing.edu.uy.persistence.exceptions.RollbackFailureException;

/**
 *
 * @author JORGE
 */
@Named
public class JPATravelsPlacesTransports implements Serializable {

    @PersistenceContext(unitName = "mytrip_PU")
    private EntityManager em;
    
    @Resource
    UserTransaction utx;

    public void create(TravelsPlacesTransports travelsPlacesTransports) throws Exception {
        List<String> illegalOrphanMessages = null;
        TravelsPlaces idTravelPlaceOrphanCheck = travelsPlacesTransports.getIdTravelPlace();
        if (idTravelPlaceOrphanCheck != null) {
            TravelsPlacesTransports oldTravelsPlacesTransportsOfIdTravelPlace = idTravelPlaceOrphanCheck.getTravelsPlacesTransports();
            if (oldTravelsPlacesTransportsOfIdTravelPlace != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The TravelsPlaces " + idTravelPlaceOrphanCheck + " already has an item of type TravelsPlacesTransports whose idTravelPlace column cannot be null. Please make another selection for the idTravelPlace field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        try {
            utx.begin();
            TravelsPlaces idTravelPlace = travelsPlacesTransports.getIdTravelPlace();
            if (idTravelPlace != null) {
                idTravelPlace = em.getReference(idTravelPlace.getClass(), idTravelPlace.getId());
                travelsPlacesTransports.setIdTravelPlace(idTravelPlace);
            }
            em.persist(travelsPlacesTransports);
            if (idTravelPlace != null) {
                idTravelPlace.setTravelsPlacesTransports(travelsPlacesTransports);
                em.merge(idTravelPlace);
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

    public void edit(TravelsPlacesTransports travelsPlacesTransports) throws Exception {
        try {
            utx.begin();
            TravelsPlacesTransports persistentTravelsPlacesTransports = em.find(TravelsPlacesTransports.class, travelsPlacesTransports.getId());
            TravelsPlaces idTravelPlaceOld = persistentTravelsPlacesTransports.getIdTravelPlace();
            TravelsPlaces idTravelPlaceNew = travelsPlacesTransports.getIdTravelPlace();
            List<String> illegalOrphanMessages = null;
            if (idTravelPlaceNew != null && !idTravelPlaceNew.equals(idTravelPlaceOld)) {
                TravelsPlacesTransports oldTravelsPlacesTransportsOfIdTravelPlace = idTravelPlaceNew.getTravelsPlacesTransports();
                if (oldTravelsPlacesTransportsOfIdTravelPlace != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The TravelsPlaces " + idTravelPlaceNew + " already has an item of type TravelsPlacesTransports whose idTravelPlace column cannot be null. Please make another selection for the idTravelPlace field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTravelPlaceNew != null) {
                idTravelPlaceNew = em.getReference(idTravelPlaceNew.getClass(), idTravelPlaceNew.getId());
                travelsPlacesTransports.setIdTravelPlace(idTravelPlaceNew);
            }
            travelsPlacesTransports = em.merge(travelsPlacesTransports);
            if (idTravelPlaceOld != null && !idTravelPlaceOld.equals(idTravelPlaceNew)) {
                idTravelPlaceOld.setTravelsPlacesTransports(null);
                idTravelPlaceOld = em.merge(idTravelPlaceOld);
            }
            if (idTravelPlaceNew != null && !idTravelPlaceNew.equals(idTravelPlaceOld)) {
                idTravelPlaceNew.setTravelsPlacesTransports(travelsPlacesTransports);
                em.merge(idTravelPlaceNew);
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
                Integer id = travelsPlacesTransports.getId();
                if (findTravelsPlacesTransports(id) == null) {
                    throw new NonexistentEntityException("The travelsPlacesTransports with id " + id + " no longer exists.");
                }
            }
            throw ex;
        }
    }

    public void destroy(Integer id) throws Exception {
        try {
            utx.begin();
            TravelsPlacesTransports travelsPlacesTransports;
            try {
                travelsPlacesTransports = em.getReference(TravelsPlacesTransports.class, id);
                travelsPlacesTransports.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The travelsPlacesTransports with id " + id + " no longer exists.", enfe);
            }
            TravelsPlaces idTravelPlace = travelsPlacesTransports.getIdTravelPlace();
            if (idTravelPlace != null) {
                idTravelPlace.setTravelsPlacesTransports(null);
                em.merge(idTravelPlace);
            }
            em.remove(travelsPlacesTransports);
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

    public TravelsPlacesTransports findTravelsPlacesTransports(Integer id) {
        return em.find(TravelsPlacesTransports.class, id);
    }
    
}
