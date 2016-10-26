package com.gardecote.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Dell on 23/10/2016.
 */
public class qNation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="idNation", nullable=false)
    private Integer idNation;
    @Column(name="designation", nullable=false)
    private String designation;

    public qNation(String designation) {
        this.designation = designation;
    }

    public Integer getIdNation() {
        return idNation;
    }

    public void setIdNation(Integer idNation) {
        this.idNation = idNation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
