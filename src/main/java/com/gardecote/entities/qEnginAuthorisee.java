package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dell on 03/03/2017.
 */
@Entity
@Table(name="qEnginAuthorisee", schema="dbo", catalog="GCM5" )
// Define named queries here
@NamedQueries( {
        @NamedQuery( name="qEnginAuthorisee.countAll", query="SELECT COUNT(x) FROM qEnginAuthorisee x" )
} )
@IdClass(qEnginAuthoriseePK.class)
public class qEnginAuthorisee implements Serializable {
    @Id
    private String numlic;
    @Id
    private enumEngin EnginMar;
    @Id
    private enumEnginDeb EnginDeb;
    @OneToOne
    private qCategRessource categorieLicence;
    @OneToOne(targetEntity = qEnginsLicence.class)
    private qEnginsLicence enginAuthorise;
    @OneToOne
    private qLic licence;

    private Integer maillageAuthorise;

    public qEnginsLicence getEnginAuthorise() {
        return enginAuthorise;
    }
    public qEnginAuthoriseePK getEnginAuthoriseePK(){
    return new qEnginAuthoriseePK(numlic, EnginMar,EnginDeb);
         }
    public void setEnginAuthorise(qEnginsLicence enginAuthorise) {
        this.enginAuthorise = enginAuthorise;
    }

    public Integer getMaillageAuthorise() {
        return maillageAuthorise;
    }

    public qEnginAuthorisee() {
    }

    public qEnginAuthorisee(qCategRessource categorieLicence, qEnginsLicence enginAuthorise, qLic licence, Integer maillageAuthorise) {
        this.categorieLicence = categorieLicence;
        this.enginAuthorise = enginAuthorise;
        this.licence = licence;
        this.maillageAuthorise = maillageAuthorise;
        this.EnginDeb=enginAuthorise.getEnginDeb();
        this.EnginMar=enginAuthorise.getEnginMar();
        this.numlic=licence.getNumlic();
    }

    public void setMaillageAuthorise(Integer maillageAuthorise) {
        this.maillageAuthorise = maillageAuthorise;
    }

}
