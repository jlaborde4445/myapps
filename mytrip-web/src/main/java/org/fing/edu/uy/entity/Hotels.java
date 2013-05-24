/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fing.edu.uy.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JORGE
 */
@Entity
@Table(name = "`Hotels`")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hotels.findAll", query = "SELECT h FROM Hotels h order by h.name"),
    @NamedQuery(name = "Hotels.findByIdHotel", query = "SELECT h FROM Hotels h WHERE h.idHotel = :idHotel order by h.name"),
    @NamedQuery(name = "Hotels.findByName", query = "SELECT h FROM Hotels h WHERE h.name = :name order by h.name"),
    @NamedQuery(name = "Hotels.findByPlace", query = "SELECT h FROM Hotels h WHERE h.idPlace = :idPlace order by h.name")})
public class Hotels implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_hotel")
    private Integer idHotel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_from_check_in")
    @Temporal(TemporalType.TIME)
    private Date timeFromCheckIn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_to_check_in")
    @Temporal(TemporalType.TIME)
    private Date timeToCheckIn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_from_check_out")
    @Temporal(TemporalType.TIME)
    private Date timeFromCheckOut;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_to_check_out")
    @Temporal(TemporalType.TIME)
    private Date timeToCheckOut;
    @Size(max = 2147483647)
    @Column(name = "link")
    private String link;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHotel")
    private List<TravelsPlacesHotels> travelsPlacesHotelsList;
    @JoinColumn(name = "id_place", referencedColumnName = "id_place")
    @ManyToOne(optional = false)
    private Places idPlace;

    public Hotels() {
    }

    public Hotels(Integer idHotel) {
        this.idHotel = idHotel;
    }

    public Hotels(Integer idHotel, String name, String address, Date timeFromCheckIn, Date timeToCheckIn, Date timeFromCheckOut, Date timeToCheckOut) {
        this.idHotel = idHotel;
        this.name = name;
        this.address = address;
        this.timeFromCheckIn = timeFromCheckIn;
        this.timeToCheckIn = timeToCheckIn;
        this.timeFromCheckOut = timeFromCheckOut;
        this.timeToCheckOut = timeToCheckOut;
    }

    public Integer getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Integer idHotel) {
        this.idHotel = idHotel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getTimeFromCheckIn() {
        return timeFromCheckIn;
    }

    public void setTimeFromCheckIn(Date timeFromCheckIn) {
        this.timeFromCheckIn = timeFromCheckIn;
    }

    public Date getTimeToCheckIn() {
        return timeToCheckIn;
    }

    public void setTimeToCheckIn(Date timeToCheckIn) {
        this.timeToCheckIn = timeToCheckIn;
    }

    public Date getTimeFromCheckOut() {
        return timeFromCheckOut;
    }

    public void setTimeFromCheckOut(Date timeFromCheckOut) {
        this.timeFromCheckOut = timeFromCheckOut;
    }

    public Date getTimeToCheckOut() {
        return timeToCheckOut;
    }

    public void setTimeToCheckOut(Date timeToCheckOut) {
        this.timeToCheckOut = timeToCheckOut;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @XmlTransient
    public List<TravelsPlacesHotels> getTravelsPlacesHotelsList() {
        return travelsPlacesHotelsList;
    }

    public void setTravelsPlacesHotelsList(List<TravelsPlacesHotels> travelsPlacesHotelsList) {
        this.travelsPlacesHotelsList = travelsPlacesHotelsList;
    }

    public Places getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(Places idPlace) {
        this.idPlace = idPlace;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHotel != null ? idHotel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hotels)) {
            return false;
        }
        Hotels other = (Hotels) object;
        if ((this.idHotel == null && other.idHotel != null) || (this.idHotel != null && !this.idHotel.equals(other.idHotel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fing.edu.uy.entity.Hotels[ idHotel=" + idHotel + " ]";
    }
    
}
