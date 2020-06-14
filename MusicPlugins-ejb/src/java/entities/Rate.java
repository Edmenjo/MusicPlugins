/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author zuzu
 */
@Embeddable
public class Rate implements Serializable {

    @Column(name = "RATE")
    private Integer rate;

    public Rate() {
    }

    public Rate(Integer rate) {
        this.rate = rate;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
