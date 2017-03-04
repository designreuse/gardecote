package com.gardecote.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dell on 16/11/2016.
 */
@Entity
@Table(name="qEnginsCategRessources", schema="dbo", catalog="GCM5" )

@IdClass(qEnginsLicencePK.class)
public class qEnginsLicence implements Serializable {
    @Id
    private enumEnginDeb EnginDeb;
    @Id
    private enumEngin EnginMar;

    @ManyToMany(mappedBy = "Engins",cascade = CascadeType.PERSIST)

    @JsonBackReference
    private List<qCategRessource> categressource;

    public qEnginsLicence() {
    }


    public List<qCategRessource> getCategressource() {
        return categressource;
    }

    public void setCategressource(List<qCategRessource> categressource) {
        this.categressource = categressource;
    }

    public qEnginsLicence(enumEnginDeb enginDeb, enumEngin enginMar, Integer maillage,boolean flag) {
        EnginDeb = enginDeb;
        EnginMar = enginMar;

    }
    public qEnginsLicencePK  getLicencePK(){
        qEnginsLicencePK enginsLicencePK=new qEnginsLicencePK(this.EnginDeb,this.getEnginMar());
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


}
