package com.gardecote.entities;

import javax.persistence.*;

/**
 * Created by Dell on 25/10/2016.
 */
@Entity
@IdClass(qEspeceTypeePK.class)
public class qEspeceTypee {
    @Id
    private qEspece qespece;
    @Id
    private enumEspType enumesptype;
    @Id
    private qTypeConcession qtypeconcession;

    @ManyToOne
 //   @JoinColumns({
   //         @JoinColumn(name = "modelesp_segment", referencedColumnName = "segPeche"),
    //        @JoinColumn(name = "modelesp_categorie", referencedColumnName = "categorie")
 //   })
    private qModelJP modelesp;

    public qEspeceTypee(qEspece qespece, enumEspType enumesptype, qTypeConcession qtypeconcession) {
        this.qespece = qespece;
        this.enumesptype = enumesptype;
        this.qtypeconcession = qtypeconcession;
    }

    public qEspeceTypee() {
    }

    public qEspece getQespece() {
        return qespece;
    }

    public void setQespece(qEspece qespece) {
        this.qespece = qespece;
    }

    public enumEspType getEnumesptype() {
        return enumesptype;
    }

    public void setEnumesptype(enumEspType enumesptype) {
        this.enumesptype = enumesptype;
    }

    public qTypeConcession getQtypeconcession() {
        return qtypeconcession;
    }

    public void setQtypeconcession(qTypeConcession qtypeconcession) {
        this.qtypeconcession = qtypeconcession;
    }

    public qModelJP getModelesp() {
        return modelesp;
    }

    public void setModelesp(qModelJP modelesp) {
        this.modelesp = modelesp;
    }
}