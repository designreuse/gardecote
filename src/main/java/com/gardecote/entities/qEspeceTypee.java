package com.gardecote.entities;

import javax.persistence.*;

/**
 * Created by Dell on 25/10/2016.
 */
@Entity
@IdClass(qEspeceTypeePK.class)
public class qEspeceTypee {
    @Id
    private Integer qespeceId;
    @Id
    private enumEspType enumesptype;

    @Id
    private String idmodel;



    @OneToOne(cascade = CascadeType.ALL)
    private qEspece qespece;
    @OneToOne(cascade = CascadeType.ALL)
    private qModelJP modeljp;
    @ManyToOne
 //   @JoinColumns({
   //         @JoinColumn(name = "modelesp_segment", referencedColumnName = "segPeche"),
    //        @JoinColumn(name = "modelesp_categorie", referencedColumnName = "categorie")
 //   })
    @JoinColumn(name = "modelesp")
    private qModelJP modelesp;



    public qEspeceTypee(enumEspType enumesptype, qEspece qespece, qModelJP modelesp) {
        this.enumesptype = enumesptype;


        this.qespece = qespece;
        this.modelesp = modelesp;

        this.qespeceId = qespece.getCodeEsp();
       this.idmodel= modelesp.getPrefixModel().toString();


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

    public Integer getQespeceId() {
        return qespeceId;
    }

    public void setQespeceId(Integer qespeceId) {
        this.qespeceId = qespeceId;
    }

    public String getIdmodel() {
        return idmodel;
    }

    public void setIdmodel(String idmodel) {
        this.idmodel = idmodel;
    }

    public qModelJP getModeljp() {
        return modeljp;
    }

    public void setModeljp(qModelJP modeljp) {
        this.modeljp = modeljp;
    }

    public qModelJP getModelesp() {
        return modelesp;
    }

    public void setModelesp(qModelJP modelesp) {
        this.modelesp = modelesp;
    }
}
