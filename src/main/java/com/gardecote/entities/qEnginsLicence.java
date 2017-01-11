package com.gardecote.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dell on 16/11/2016.
 */
@Entity
@Table(name="qEnginsLicence30", schema="dbo", catalog="GCM1" )

@IdClass(qEnginsLicencePK.class)
public class qEnginsLicence implements Serializable {
    @Id
    private enumEnginDeb EnginDeb;
    @Id
    private enumEngin EnginMar;
    @Id
    private Integer maillage;




    @ManyToMany(mappedBy = "Engins",cascade = CascadeType.PERSIST)

    @JsonBackReference
    private List<qCategRessource> categressource;

    public qEnginsLicence() {
    }

    public qEnginsLicence(enumEnginDeb enginDeb, enumEngin enginMar, Integer maillage) {
        EnginDeb = enginDeb;
        EnginMar = enginMar;
        this.maillage = maillage;
    }
    public qEnginsLicencePK  getLicencePK(){
        qEnginsLicencePK enginsLicencePK=new qEnginsLicencePK(this.EnginDeb,this.getEnginMar(),this.maillage);
        return  enginsLicencePK;
    }
    public enumEnginDeb getEnginDeb() {
        return EnginDeb;
    }

    public void setEnginDeb(enumEnginDeb enginDeb) {
        EnginDeb = enginDeb;
    }

    public enumEngin getEnginMar() {
        return EnginMar;
    }

    public void setEnginMar(enumEngin enginMar) {
        EnginMar = enginMar;
    }

    public Integer getMaillage() {
        return maillage;
    }

    public void setMaillage(Integer maillage) {
        this.maillage = maillage;
    }
}
