package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 22/10/2016.
 */
@Entity
@Table(name="qRegistreNavire", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qRegistreNavire.countAll", query="SELECT COUNT(x) FROM qRegistreNavire x" )
} )
public class qRegistreNavire implements Serializable {
    private static final long serialVersionUID = 1L;
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id

    @Column(name="numimm", nullable=false)
    private String     numimm;

    private qLicence qlicencedernier;

    public qRegistreNavire(qLicence qlicencedernier,String numimm) {
        this.qlicencedernier = qlicencedernier;
        this.numimm=numimm;
    }

    public qRegistreNavire() {
    }

    @OneToMany(mappedBy="qnavire", targetEntity=qLicence.class)
    private List<qLicence>  qlicences;

    @OneToMany(mappedBy="qnavire", targetEntity=qCarnet.class)
    private List<qCarnet>  qcarnets;

    public String getNumimm() {
        return numimm;
    }

    public qLicence getQlicencedernier() {
        return qlicencedernier;
    }

    public void setQlicencedernier(qLicence qlicencedernier) {
        this.qlicencedernier = qlicencedernier;
    }

    public List<qCarnet> getQcarnets() {
        return qcarnets;
    }

    public void setQcarnets(List<qCarnet> qcarnets) {
        this.qcarnets = qcarnets;
    }

    public void setNumimm(String numimm) {
        this.numimm = numimm;
    }

    public qLicence getQlicencebatlastdernier() {
        return qlicencedernier;
    }

    public void setQlicencebatlastdernier(qLicence qlicencedernier) {
        this.qlicencedernier = qlicencedernier;
    }

    public List<qLicence> getQlicences() {
        return qlicences;
    }

    public void setQlicences(List<qLicence> qlicences) {
        this.qlicences = qlicences;
    }
}
