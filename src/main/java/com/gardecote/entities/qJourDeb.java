/*
 * Created on 8 oct. 2016 ( Time 01:00:05 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.gardecote.entities;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

/**
 * Persistent class for entity stored in table "quotaJours"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="qJourDeb30", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="qJourDeb.countAll", query="SELECT COUNT(x) FROM qJourDeb x" )
} )

@IdClass(qJourPK.class)
public class qJourDeb implements Serializable
{
    private static final long serialVersionUID = 1L;
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="numimmJour", nullable=false)
    private String       numImm       ;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------
    @Id
    @Column(name="dateJour", nullable=false, length=10)

    @DateTimeFormat (pattern="yyyy-MM-dd")
    private Date  dateJour ;

   //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------


    @OneToOne
    private qNavire navire;

    @OneToMany(mappedBy = "jourDeb",targetEntity=qCapture.class,cascade = CascadeType.ALL)

    private List<qCapture> debarqDuJour;

    @ManyToOne

    private qPageDebarquement pagesDeb;

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public qJourDeb()
    {
		super();
    }

    public qJourPK getJourPK(){
        qJourPK qjp=new qJourPK(this.getDatejourDeb(),this.getNumImm());
        return qjp;
    }

    public qJourDeb(Date datejourDeb, qNavire navire, List<qCapture> debarqDuJour, qPageDebarquement pagesDeb) {
        this.numImm = navire.getNumimm();
        this.dateJour = datejourDeb;
        this.navire = navire;
        this.debarqDuJour = debarqDuJour;
        this.pagesDeb = pagesDeb;
    }

    public String getNumImm() {
        return numImm;
    }

    public void setNumImm(String numImm) {
        this.numImm = numImm;
    }

    public qNavire getNavire() {
        return navire;
    }

    public void setNavire(qNavire navire) {
        this.navire = navire;
    }


    public Date getDateJour() {
        return dateJour;
    }

    public void setDateJour(Date dateJour) {
        this.dateJour = dateJour;
    }

    public Date getDatejourDeb() {
        return dateJour;
    }

    public void setDatejourDeb(Date datejourDeb) {
        this.dateJour = datejourDeb;
    }

    public List<qCapture> getDebarqDuJour() {
        return debarqDuJour;
    }

    public void setDebarqDuJour(List<qCapture> debarqDuJour) {
        this.debarqDuJour = debarqDuJour;
    }

    public qPageDebarquement getPagesDeb() {
        return pagesDeb;
    }

    public void setPagesDeb(qPageDebarquement pagesDeb) {
        this.pagesDeb = pagesDeb;
    }
}