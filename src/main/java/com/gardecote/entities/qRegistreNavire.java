package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 22/10/2016.
 */
@Entity
@Table(name="RegistreNavire", schema="dbo", catalog="DSPCM_DB" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="RegistreNavire.countAll", query="SELECT COUNT(x) FROM RegistreNavire x" )
} )
public class qRegistreNavire implements Serializable {
    private static final long serialVersionUID = 1L;
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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

    public String getNumimm() {
        return numimm;
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
