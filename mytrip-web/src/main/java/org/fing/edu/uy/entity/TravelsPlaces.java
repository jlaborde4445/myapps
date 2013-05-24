/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fing.edu.uy.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JORGE
 */
@Entity
@Table(name = "`Travels_Places`")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TravelsPlaces.findAll", query = "SELECT t FROM TravelsPlaces t"),
    @NamedQuery(name = "TravelsPlaces.findById", query = "SELECT t FROM TravelsPlaces t WHERE t.id = :id"),
    @NamedQuery(name = "TravelsPlaces.findByIdTravel", query = "SELECT t FROM TravelsPlaces t WHERE t.idTravel = :idTravel order by t.startingDate")})
public class TravelsPlaces implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "output_date")
    @Temporal(TemporalType.DATE)
    private Date outputDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "arrival_date")
    @Temporal(TemporalType.DATE)
    private Date arrivalDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "starting_date")
    @Temporal(TemporalType.DATE)
    private Date startingDate;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idTravelPlace")
    private TravelsPlacesTransports travelsPlacesTransports;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idTravelPlace")
    private TravelsPlacesHotels travelsPlacesHotels;
    @JoinColumn(name = "id_travel", referencedColumnName = "id_travel")
    @ManyToOne(optional = false)
    private Travels idTravel;
    @JoinColumn(name = "id_place_output", referencedColumnName = "id_place")
    @ManyToOne(optional = false)
    private Places idPlaceOutput;
    @JoinColumn(name = "id_place_arrival", referencedColumnName = "id_place")
    @ManyToOne(optional = false)
    private Places idPlaceArrival;

    public TravelsPlaces() {
    }

    public TravelsPlaces(Integer id) {
        this.id = id;
    }

    public TravelsPlaces(Integer id, Date outputDate, Date arrivalDate, Date startingDate) {
        this.id = id;
        this.outputDate = outputDate;
        this.arrivalDate = arrivalDate;
        this.startingDate = startingDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOutputDate() {
        return outputDate;
    }

    public void setOutputDate(Date outputDate) {
        this.outputDate = outputDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public TravelsPlacesTransports getTravelsPlacesTransports() {
        return travelsPlacesTransports;
    }

    public void setTravelsPlacesTransports(TravelsPlacesTransports travelsPlacesTransports) {
        this.travelsPlacesTransports = travelsPlacesTransports;
    }

    public TravelsPlacesHotels getTravelsPlacesHotels() {
        return travelsPlacesHotels;
    }

    public void setTravelsPlacesHotels(TravelsPlacesHotels travelsPlacesHotels) {
        this.travelsPlacesHotels = travelsPlacesHotels;
    }

    public Travels getIdTravel() {
        return idTravel;
    }

    public void setIdTravel(Travels idTravel) {
        this.idTravel = idTravel;
    }

    public Places getIdPlaceOutput() {
        return idPlaceOutput;
    }

    public void setIdPlaceOutput(Places idPlaceOutput) {
        this.idPlaceOutput = idPlaceOutput;
    }

    public Places getIdPlaceArrival() {
        return idPlaceArrival;
    }

    public void setIdPlaceArrival(Places idPlaceArrival) {
        this.idPlaceArrival = idPlaceArrival;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TravelsPlaces)) {
            return false;
        }
        TravelsPlaces other = (TravelsPlaces) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fing.edu.uy.entity.TravelsPlaces[ id=" + id + " ]";
    }
    
}
