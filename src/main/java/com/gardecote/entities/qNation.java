package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dell on 23/10/2016.
 */
@Entity
@Table(name="qNation", schema="dbo", catalog="DSPCM_DB" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qNation.countAll", query="SELECT COUNT(x) FROM qNation x" )
} )
public class qNation implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="idNation", nullable=false)
    private Integer idNation;
    @Column(name="designation", nullable=false)
    private String designation;

    public qNation(String designation) {
        this.designation = designation;
    }

    public qNation() {
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
