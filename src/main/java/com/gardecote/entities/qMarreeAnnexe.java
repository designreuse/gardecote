package com.gardecote.entities;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 21/12/2016.
 */

@Entity
@Table(name="qMarreeAnnexe20", schema="dbo", catalog="GCM1")
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qMarreeAnnexe.countAll", query="SELECT COUNT(x) FROM qMarreeAnnexe x" )
} )

@IdClass(qDocPK.class)
public class qMarreeAnnexe  implements Serializable {
    @Id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date depart;
    @Id
    private String numImm;
    @OneToOne
    private qMarree marreePrincipal;
    @OneToMany(targetEntity=qPageAnnexe.class,cascade = CascadeType.ALL)
    @JoinTable(name = "qAssocMareesAnnexPages")
    private List<qPageAnnexe> pages;
    private String navireReceveur;
    private String IMO;
    private String Nationalite;
    private String indicatifRadio;

    public qMarreeAnnexe(Date depart, String numImm, qMarree marreePrincipal, List<qPageAnnexe> pages, String navireReceveur, String IMO, String nationalite, String indicatifRadio) {
        this.depart = depart;
        this.numImm = numImm;
        this.marreePrincipal = marreePrincipal;
        this.pages = pages;
        this.navireReceveur = navireReceveur;
        this.IMO = IMO;
        Nationalite = nationalite;
        this.indicatifRadio = indicatifRadio;
    }
   public qDocPK getqDocPK(){
      qDocPK dpk=new qDocPK(this.numImm,this.depart);
      return dpk;
  }

    public qMarreeAnnexe() {
    }

    public qMarree getMarreePrincipal() {
        return marreePrincipal;
    }

    public void setMarreePrincipal(qMarree marreePrincipal) {
        this.marreePrincipal = marreePrincipal;
    }

    public List<qPageAnnexe> getPages() {
        return pages;
    }

    public void setPages(List<qPageAnnexe> pages) {
        this.pages = pages;
    }

    public String getNavireReceveur() {
        return navireReceveur;
    }

    public void setNavireReceveur(String navireReceveur) {
        this.navireReceveur = navireReceveur;
    }

    public String getIMO() {
        return IMO;
    }

    public void setIMO(String IMO) {
        this.IMO = IMO;
    }

    public String getNationalite() {
        return Nationalite;
    }

    public void setNationalite(String nationalite) {
        Nationalite = nationalite;
    }

    public String getIndicatifRadio() {
        return indicatifRadio;
    }

    public void setIndicatifRadio(String indicatifRadio) {
        this.indicatifRadio = indicatifRadio;
    }

    public Date getDepart() {
        return depart;
    }

    public void setDepart(Date depart) {
        this.depart = depart;
    }

    public String getNumImm() {
        return numImm;
    }

    public void setNumImm(String numImm) {
        this.numImm = numImm;
    }
}
