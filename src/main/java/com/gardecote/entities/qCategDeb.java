package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 17/11/2016.
 */
@Entity
@Table(name="qCategDeb4", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries( {
        @NamedQuery( name="qCategDeb.countAll", query="SELECT COUNT(x) FROM qCategDeb x" )
} )

public class qCategDeb implements Serializable {
    @Id
    private String numimm;
    @Id
    private Date   dateDepart;
    @Id
    private Integer idCat;

    @OneToOne
    private qCategRessource cat;

    @ManyToOne
    private qDebarquement debar;

    private boolean flag;

    public qCategDeb(String numimm,Date dep,qCategRessource cat, boolean flag) {
        this.numimm=numimm;
        this.dateDepart=dep;
        this.idCat =  cat.getIdtypeConcession();
        this.cat = cat;
        this.flag = flag;
    }

    public String getNumimm() {
        return numimm;
    }

    public void setNumimm(String numimm) {
        this.numimm = numimm;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Integer getIdCat() {
        return idCat;
    }

    public void setIdCat(Integer idCat) {
        this.idCat = idCat;
    }

    public qCategRessource getCat() {
        return cat;
    }

    public void setCat(qCategRessource cat) {
        this.cat = cat;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public qCategDeb() {
    }
}
