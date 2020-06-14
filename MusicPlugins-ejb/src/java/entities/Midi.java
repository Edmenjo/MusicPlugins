/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zuzu
 */
@Entity
@Table(name = "MIDI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Midi.findAll", query = "SELECT m FROM Midi m")
    , @NamedQuery(name = "Midi.findById", query = "SELECT m FROM Midi m WHERE m.id = :id")
    , @NamedQuery(name = "Midi.findByName", query = "SELECT m FROM Midi m WHERE m.name = :name")
    , @NamedQuery(name = "Midi.findByDescription", query = "SELECT m FROM Midi m WHERE m.description = :description")
    , @NamedQuery(name = "Midi.findByPrice", query = "SELECT m FROM Midi m WHERE m.price = :price")})
public class Midi implements Serializable {

    @Embedded
    private Rate rate;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private int price;

    public Midi() {
    }

    public Midi(Integer id) {
        this.id = id;
    }

    public Midi(Integer id, String name, String description, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
    // --------------------------------------------------------------------
    
    
    
    public Rate getRate(){
        return rate;
    }
    
    
    public void setRate(Rate rate){
        this.rate=rate;
    }
        
    
    // -------------------------------------------------------------------

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Midi)) {
            return false;
        }
        Midi other = (Midi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Midi[ id=" + id + " ]";
    }
    
}
