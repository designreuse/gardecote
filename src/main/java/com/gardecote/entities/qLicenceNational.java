/*
 * Created on 8 oct. 2016 ( Time 01:00:05 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.gardecote.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.swing.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

/**
 * Persistent class for entity stored in table "licences_bat_last"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="qlicence4", schema="dbo", catalog="GCM1" )
// Define named queries here
@DiscriminatorValue("LICENCENATIONAL")
@NamedQueries ( {
  @NamedQuery ( name="qLicenceNational.countAll", query="SELECT COUNT(x) FROM  qLicenceNational x")
} )
public class qLicenceNational extends qLic implements Serializable
{
    private static final long serialVersionUID = 1L;
    //----------------------------------------------------------------------

    @ManyToOne
    //  @NotNull
    @JoinColumn(name="qConcessionid")
    @JsonBackReference
    private qConcession     qconcession ;

    public qLicenceNational(qTypeLic qtypnav, qZone zone, qNation qNation, List<qCategRessource> qcatressources, qNavire qnavire, enumTypeBat typb, Date dateDebutAuth, Date dateFinAuth, String numlic,  qConcession qconcession) {
        super(qtypnav, zone, qNation, qcatressources, qnavire, typb, dateDebutAuth, dateFinAuth, qnavire.getAnneeconstr(), qnavire.getBalise(), qnavire.getCalpoids(), qnavire.getCount(),qnavire.getEff(),qnavire.getGt(), qnavire.getImo(), qnavire.getKw(), qnavire.getLarg(), qnavire.getLongg(), qnavire.getNbrhomm(), qnavire.getNomar(), qnavire.getNomnav(), numlic, qnavire.getPort(), qnavire.getPuimot(), qnavire.getRadio(), qnavire.getTjb());
         this.qconcession = qconcession;
    }

    public qLicenceNational() {
    }

    public qConcession getQconcession() {
        return qconcession;
    }

    public void setQconcession(qConcession qconcession) {
        this.qconcession = qconcession;
    }
}