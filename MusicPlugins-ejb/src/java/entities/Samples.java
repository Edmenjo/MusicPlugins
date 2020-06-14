/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "SAMPLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Samples.findAll", query = "SELECT s FROM Samples s")
    , @NamedQuery(name = "Samples.findById", query = "SELECT s FROM Samples s WHERE s.id = :id")
    , @NamedQuery(name = "Samples.findByName", query = "SELECT s FROM Samples s WHERE s.name = :name")
    , @NamedQuery(name = "Samples.findByDescription", query = "SELECT s FROM Samples s WHERE s.description = :description")
    , @NamedQuery(name = "Samples.findByPrice", query = "SELECT s FROM Samples s WHERE s.price = :price")})
public class Samples implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NAME")
    private String name;
    @Size(max = 50)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private int price;

    public Samples() {
    }

    public Samples(Integer id) {
        this.id = id;
    }

    public Samples(Integer id, String name, int price) {
        this.id = id;
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Samples)) {
            return false;
        }
        Samples other = (Samples) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Samples[ id=" + id + " ]";
    }
    
}
