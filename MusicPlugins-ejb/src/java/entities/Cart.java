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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zuzu
 */
@Entity
@Table(name = "CART")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c")
    , @NamedQuery(name = "Cart.findById", query = "SELECT c FROM Cart c WHERE c.id = :id")
    , @NamedQuery(name = "Cart.findByFullprice", query = "SELECT c FROM Cart c WHERE c.fullprice = :fullprice")})
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FULLPRICE")
    private int fullprice;

    public Cart() {
    }

    public Cart(Integer id) {
        this.id = id;
    }

    public Cart(Integer id, int fullprice) {
        this.id = id;
        this.fullprice = fullprice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getFullprice() {
        return fullprice;
    }

    public void setFullprice(int fullprice) {
        this.fullprice = fullprice;
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
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cart[ id=" + id + " ]";
    }
    
}
