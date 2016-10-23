package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 22/10/2016.
 */
public class qRegistreNavire implements Serializable {
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="numimm", nullable=false)
    private String     numimm;

    private qLicenceBatLast qlicencebatlastdernier;
    @OneToMany(mappedBy="qnavire", targetEntity=qLicenceBatLast.class)
    private List<qLicenceBatLast>  qlicences;

    public String getNumimm() {
        return numimm;
    }

    public void setNumimm(String numimm) {
        this.numimm = numimm;
    }

    public qLicenceBatLast getQlicencebatlastdernier() {
        return qlicencebatlastdernier;
    }

    public void setQlicencebatlastdernier(qLicenceBatLast qlicencebatlastdernier) {
        this.qlicencebatlastdernier = qlicencebatlastdernier;
    }

    public List<qLicenceBatLast> getQlicences() {
        return qlicences;
    }

    public void setQlicences(List<qLicenceBatLast> qlicences) {
        this.qlicences = qlicences;
    }
}
