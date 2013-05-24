/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fing.edu.uy.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JORGE
 */
@Entity
@Table(name = "`Travels_Places_Hotels`")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TravelsPlacesHotels.findAll", query = "SELECT t FROM TravelsPlacesHotels t"),
    @NamedQuery(name = "TravelsPlacesHotels.findByCostPerNight", query = "SELECT t FROM TravelsPlacesHotels t WHERE t.costPerNight = :costPerNight"),
    @NamedQuery(name = "TravelsPlacesHotels.findByPayoutPercentage", query = "SELECT t FROM TravelsPlacesHotels t WHERE t.payoutPercentage = :payoutPercentage"),
    @NamedQuery(name = "TravelsPlacesHotels.findById", query = "SELECT t FROM TravelsPlacesHotels t WHERE t.id = :id"),
    @NamedQuery(name = "TravelsPlacesHotels.findByIdTravelPlace", query = "SELECT t FROM TravelsPlacesHotels t WHERE t.idTravelPlace = :idTravelPlace")})
public class TravelsPlacesHotels implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cost_per_night")
    private double costPerNight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "payout_percentage")
    private int payoutPercentage;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "payment_description")
    private String paymentDescription;
    @Lob
    @Column(name = "ticket")
    private byte[] ticket;
    @JoinColumn(name = "id_travel_place", referencedColumnName = "id")
    @OneToOne(optional = false)
    private TravelsPlaces idTravelPlace;
    @JoinColumn(name = "id_hotel", referencedColumnName = "id_hotel")
    @ManyToOne(optional = false)
    private Hotels idHotel;

    public TravelsPlacesHotels() {
    }

    public TravelsPlacesHotels(Integer id) {
        this.id = id;
    }

    public TravelsPlacesHotels(Integer id, double costPerNight, int payoutPercentage) {
        this.id = id;
        this.costPerNight = costPerNight;
        this.payoutPercentage = payoutPercentage;
    }

    public double getCostPerNight() {
        return costPerNight;
    }

    public void setCostPerNight(double costPerNight) {
        this.costPerNight = costPerNight;
    }

    public int getPayoutPercentage() {
        return payoutPercentage;
    }

    public void setPayoutPercentage(int payoutPercentage) {
        this.payoutPercentage = payoutPercentage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public byte[] getTicket() {
        return ticket;
    }

    public void setTicket(byte[] ticket) {
        this.ticket = ticket;
    }

    public TravelsPlaces getIdTravelPlace() {
        return idTravelPlace;
    }

    public void setIdTravelPlace(TravelsPlaces idTravelPlace) {
        this.idTravelPlace = idTravelPlace;
    }

    public Hotels getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Hotels idHotel) {
        this.idHotel = idHotel;
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
        if (!(object instanceof TravelsPlacesHotels)) {
            return false;
        }
        TravelsPlacesHotels other = (TravelsPlacesHotels) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fing.edu.uy.entity.TravelsPlacesHotels[ id=" + id + " ]";
    }
    
}
