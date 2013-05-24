/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fing.edu.uy.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JORGE
 */
@Entity
@Table(name = "`Places`")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Places.findAll", query = "SELECT p FROM Places p order by p.country"),
    @NamedQuery(name = "Places.findByIdPlace", query = "SELECT p FROM Places p WHERE p.idPlace = :idPlace"),
    @NamedQuery(name = "Places.findByCountry", query = "SELECT p FROM Places p WHERE p.country = :country"),
    @NamedQuery(name = "Places.findByPlace", query = "SELECT p FROM Places p WHERE p.place = :place"),
    @NamedQuery(name = "Places.findByLatitude", query = "SELECT p FROM Places p WHERE p.latitude = :latitude"),
    @NamedQuery(name = "Places.findByLongitude", query = "SELECT p FROM Places p WHERE p.longitude = :longitude"),
    @NamedQuery(name = "Places.findByIdPlaceWithOut", query = "SELECT p FROM Places p WHERE p.idPlace <> :idPlace order by p.country")})
public class Places implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_place")
    private Integer idPlace;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "place")
    private String place;
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude")
    private double latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude")
    private double longitude;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlace")
    private List<Hotels> hotelsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlaceOutput")
    private List<TravelsPlaces> travelsPlacesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlaceArrival")
    private List<TravelsPlaces> travelsPlacesList1;

    public Places() {
    }

    public Places(Integer idPlace) {
        this.idPlace = idPlace;
    }

    public Places(Integer idPlace, String country, String place, double latitude, double longitude) {
        this.idPlace = idPlace;
        this.country = country;
        this.place = place;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(Integer idPlace) {
        this.idPlace = idPlace;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @XmlTransient
    public List<Hotels> getHotelsList() {
        return hotelsList;
    }

    public void setHotelsList(List<Hotels> hotelsList) {
        this.hotelsList = hotelsList;
    }

    @XmlTransient
    public List<TravelsPlaces> getTravelsPlacesList() {
        return travelsPlacesList;
    }

    public void setTravelsPlacesList(List<TravelsPlaces> travelsPlacesList) {
        this.travelsPlacesList = travelsPlacesList;
    }

    @XmlTransient
    public List<TravelsPlaces> getTravelsPlacesList1() {
        return travelsPlacesList1;
    }

    public void setTravelsPlacesList1(List<TravelsPlaces> travelsPlacesList1) {
        this.travelsPlacesList1 = travelsPlacesList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlace != null ? idPlace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Places)) {
            return false;
        }
        Places other = (Places) object;
        if ((this.idPlace == null && other.idPlace != null) || (this.idPlace != null && !this.idPlace.equals(other.idPlace))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fing.edu.uy.entity.Places[ idPlace=" + idPlace + " ]";
    }
    
}
