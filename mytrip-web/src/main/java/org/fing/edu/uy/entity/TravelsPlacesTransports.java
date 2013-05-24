/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fing.edu.uy.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JORGE
 */
@Entity
@Table(name = "`Travels_Places_Transports`")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TravelsPlacesTransports.findAll", query = "SELECT t FROM TravelsPlacesTransports t"),
    @NamedQuery(name = "TravelsPlacesTransports.findById", query = "SELECT t FROM TravelsPlacesTransports t WHERE t.id = :id"),
    @NamedQuery(name = "TravelsPlacesTransports.findByType", query = "SELECT t FROM TravelsPlacesTransports t WHERE t.type = :type"),
    @NamedQuery(name = "TravelsPlacesTransports.findByCompany", query = "SELECT t FROM TravelsPlacesTransports t WHERE t.company = :company"),
    @NamedQuery(name = "TravelsPlacesTransports.findByCost", query = "SELECT t FROM TravelsPlacesTransports t WHERE t.cost = :cost"),
    @NamedQuery(name = "TravelsPlacesTransports.findByStartingTime", query = "SELECT t FROM TravelsPlacesTransports t WHERE t.startingTime = :startingTime"),
    @NamedQuery(name = "TravelsPlacesTransports.findByArrivalTime", query = "SELECT t FROM TravelsPlacesTransports t WHERE t.arrivalTime = :arrivalTime"),
    @NamedQuery(name = "TravelsPlacesTransports.findByPaymentDescription", query = "SELECT t FROM TravelsPlacesTransports t WHERE t.paymentDescription = :paymentDescription"),
    @NamedQuery(name = "TravelsPlacesTransports.findByIdTravelPlace", query = "SELECT t FROM TravelsPlacesTransports t WHERE t.idTravelPlace = :idTravelPlace")})
public class TravelsPlacesTransports implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "company")
    private String company;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cost")
    private double cost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "starting_time")
    @Temporal(TemporalType.TIME)
    private Date startingTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "arrival_time")
    @Temporal(TemporalType.TIME)
    private Date arrivalTime;
    @Size(max = 255)
    @Column(name = "payment_description")
    private String paymentDescription;
    @Lob
    @Column(name = "ticket")
    private byte[] ticket;
    @JoinColumn(name = "id_travel_place", referencedColumnName = "id")
    @OneToOne(optional = false)
    private TravelsPlaces idTravelPlace;

    public TravelsPlacesTransports() {
    }

    public TravelsPlacesTransports(Integer id) {
        this.id = id;
    }

    public TravelsPlacesTransports(Integer id, String type, String company, double cost, Date startingTime, Date arrivalTime) {
        this.id = id;
        this.type = type;
        this.company = company;
        this.cost = cost;
        this.startingTime = startingTime;
        this.arrivalTime = arrivalTime; 
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TravelsPlacesTransports)) {
            return false;
        }
        TravelsPlacesTransports other = (TravelsPlacesTransports) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fing.edu.uy.entity.TravelsPlacesTransports[ id=" + id + " ]";
    }
    
}
