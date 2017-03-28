package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dell on 04/03/2017.
 */
@Entity
@Table(name="qNavireHistoriqueChangements", schema="dbo", catalog="GCM11" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qNavireHistoriqueChangements.countAll", query="SELECT COUNT(x) FROM qNavireHistoriqueChangements x" )
})
@IdClass(qNavireHistoriqueChangementsPK.class)
public class qNavireHistoriqueChangements implements Serializable {
    @Id
    private String numimm;
    @Id
    private enumHistoriqueNavire typeHystaurique;
    @Id
    private String numLic;
   @OneToOne
    private qNavireLegale navireConcernee;
    @OneToOne
    private qLic licenceDeBase;
    private String descriptif;

    public qNavireHistoriqueChangements() {
    }

    public qNavireHistoriqueChangements(enumHistoriqueNavire typeHystaurique, qNavireLegale navireConcernee, qLic licenceDeBase) {
        this.typeHystaurique = typeHystaurique;
        this.navireConcernee = navireConcernee;
        this.licenceDeBase = licenceDeBase;
        this.numimm=navireConcernee.getNumimm();
        this.numLic=licenceDeBase.getNumlic();
    }

    public String getNumimm() {
        return numimm;
    }

    public void setNumimm(String numimm) {
        this.numimm = numimm;
    }

    public enumHistoriqueNavire getTypeHystaurique() {
        return typeHystaurique;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public void setTypeHystaurique(enumHistoriqueNavire typeHystaurique) {
        this.typeHystaurique = typeHystaurique;
    }

    public String getNumLic() {
        return numLic;
    }

    public void setNumLic(String numLic) {
        this.numLic = numLic;
    }

    public qNavireLegale getNavireConcernee() {
        return navireConcernee;
    }

    public void setNavireConcernee(qNavireLegale navireConcernee) {
        this.navireConcernee = navireConcernee;
    }

    public qLic getLicenceDeBase() {
        return licenceDeBase;
    }

    public void setLicenceDeBase(qLic licenceDeBase) {
        this.licenceDeBase = licenceDeBase;
    }
}
