/*
 * Created on 8 oct. 2016 ( Time 01:00:05 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

/**
 * Persistent class for entity stored in table "quotaJourCaptJours"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="qCapture", schema="dbo", catalog="DSPCM_DB" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="qCaptue.countAll", query="SELECT COUNT(x) FROM qCapture x" )
} )
public class qCapture implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idCapture", nullable=false)
    private Long      idCapture ;

    private qEspece espece;
    private Integer quantite;

    @ManyToOne
    private qJourMere jourMere;
    @ManyToOne
    private qJourDeb jourDeb;

    public Long getIdCapture() {
        return idCapture;
    }

    public void setIdCapture(Long idCapture) {
        this.idCapture = idCapture;
    }

    public qEspece getEspece() {
        return espece;
    }

    public void setEspece(qEspece espece) {
        this.espece = espece;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public qJourMere getJourMere() {
        return jourMere;
    }

    public void setJourMere(qJourMere jourMere) {
        this.jourMere = jourMere;
    }

    public qJourDeb getJourDeb() {
        return jourDeb;
    }

    public void setJourDeb(qJourDeb jourDeb) {
        this.jourDeb = jourDeb;
    }
}