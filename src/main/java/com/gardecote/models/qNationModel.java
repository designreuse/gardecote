package com.gardecote.models;

import javax.persistence.*;
import java.io.Serializable;

public class qNationModel implements Serializable {


    private Integer idNation;

    private String designation;

    public qNationModel(String designation) {
        this.designation = designation;
    }

    public qNationModel() {
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
