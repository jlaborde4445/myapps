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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "`Travels`")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Travels.findAll", query = "SELECT t FROM Travels t"),
    @NamedQuery(name = "Travels.findByIdTravel", query = "SELECT t FROM Travels t WHERE t.idTravel = :idTravel"),
    @NamedQuery(name = "Travels.findByDescription", query = "SELECT t FROM Travels t WHERE t.description = :description"),
    @NamedQuery(name = "Travels.findByName", query = "SELECT t FROM Travels t WHERE t.name = :name"),
    @NamedQuery(name = "Travels.findByIdUser", query = "SELECT t FROM Travels t WHERE t.idUser = :id_user")})
public class Travels implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_travel")
    private Integer idTravel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private Users idUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTravel")
    private List<TravelsPlaces> travelsPlacesList;

    public Travels() {
    }

    public Travels(Integer idTravel) {
        this.idTravel = idTravel;
    }

    public Travels(Integer idTravel, String description, String name) {
        this.idTravel = idTravel;
        this.description = description;
        this.name = name;
    }

    public Integer getIdTravel() {
        return idTravel;
    }

    public void setIdTravel(Integer idTravel) {
        this.idTravel = idTravel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public List<TravelsPlaces> getTravelsPlacesList() {
        return travelsPlacesList;
    }

    public void setTravelsPlacesList(List<TravelsPlaces> travelsPlacesList) {
        this.travelsPlacesList = travelsPlacesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTravel != null ? idTravel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Travels)) {
            return false;
        }
        Travels other = (Travels) object;
        if ((this.idTravel == null && other.idTravel != null) || (this.idTravel != null && !this.idTravel.equals(other.idTravel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fing.edu.uy.entity.Travels[ idTravel=" + idTravel + " ]";
    }
    
}
