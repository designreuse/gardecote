package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dell on 25/10/2016.
 */
@Entity

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_CONCESSION", discriminatorType=DiscriminatorType.STRING, length=20)
@Table(name="qTypeConcession", schema="dbo", catalog="DSPCM_DB" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qTypeConcession.countAll", query="SELECT COUNT(x) FROM qTypeConcession x" )
} )
public class qTypeConcession implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="idtypeconcession", nullable=false)

    private Long idTypeConcession;

    private String libelle;

    public qTypeConcession() {
        super();
    }

    public qTypeConcession(String libelle) {
        this.libelle = libelle;
    }

    public Long getIdTypeConcession() {
        return idTypeConcession;
    }

    public void setIdTypeConcession(Long idTypeConcession) {
        this.idTypeConcession = idTypeConcession;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
