package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dell on 03/03/2017.
 */
@Entity
@Table(name="qEnginAuthorisee", schema="dbo", catalog="GCM8" )
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
    @OneToOne(targetEntity = qConcession.class)
    private qConcession concession;
    @OneToOne
    private qLic licence;

    public qConcession getConcession() {
        return concession;
    }

    public void setConcession(qConcession concession) {
        this.concession = concession;
    }

    @OneToOne
    private qNavireLegale navire;

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

    public qNavireLegale getNavire() {
        return navire;
    }

    public void setNavire(qNavireLegale navire) {
        this.navire = navire;
    }

    public String getNumlic() {
        return numlic;
    }

    public void setNumlic(String numlic) {
        this.numlic = numlic;
    }

    public enumEngin getEnginMar() {
        return EnginMar;
    }

    public void setEnginMar(enumEngin enginMar) {
        EnginMar = enginMar;
    }

    public enumEnginDeb getEnginDeb() {
        return EnginDeb;
    }

    public void setEnginDeb(enumEnginDeb enginDeb) {
        EnginDeb = enginDeb;
    }

    public qCategRessource getCategorieLicence() {
        return categorieLicence;
    }

    public void setCategorieLicence(qCategRessource categorieLicence) {
        this.categorieLicence = categorieLicence;
    }

    public qLic getLicence() {
        return licence;
    }

    public void setLicence(qLic licence) {
        this.licence = licence;
    }

    public Integer getMaillageAuthorise() {
        return maillageAuthorise;
    }

    public qEnginAuthorisee() {
    }

    public qEnginAuthorisee(qCategRessource categorieLicence, qEnginsLicence enginAuthorise, qLic licence,qConcession concession, Integer maillageAuthorise) {
        this.categorieLicence = categorieLicence;
        this.enginAuthorise = enginAuthorise;
        this.licence = licence;
        this.concession=concession;
        this.maillageAuthorise = maillageAuthorise;
        this.EnginDeb=enginAuthorise.getEnginDeb();
        this.EnginMar=enginAuthorise.getEnginMar();
        this.numlic=licence.getNumlic();
    }

    public void setMaillageAuthorise(Integer maillageAuthorise) {
        this.maillageAuthorise = maillageAuthorise;
    }

}
