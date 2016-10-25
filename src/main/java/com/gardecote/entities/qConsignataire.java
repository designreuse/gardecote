package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dell on 09/10/2016.
 */

@Entity
@Table(name="qConsignataire", schema="dbo", catalog="DSPCM_DB" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qConsignataire.countAll", query="SELECT COUNT(x) FROM qConsignataire x" )
} )
public class qConsignataire implements Serializable {

    @Id
    private String Consignataire;
    @OneToMany(mappedBy ="concessionaire" ,targetEntity =qConcession.class )
    private List<qConcession> concession;

    public qConsignataire(String consignataire, List<qConcession> concession) {
        Consignataire = consignataire;
        this.concession = concession;
    }

    public qConsignataire() { super();
    }

    public qConsignataire(String consignataire) {
        Consignataire = consignataire;
    }



    public String getConsignataire() {
        return Consignataire;
    }

    public void setConsignataire(String consignataire) {
        Consignataire = consignataire;
    }

    public List<qConcession> getConcession() {
        return concession;
    }

    public void setConcession(List<qConcession> concession) {
        this.concession = concession;
    }
}
