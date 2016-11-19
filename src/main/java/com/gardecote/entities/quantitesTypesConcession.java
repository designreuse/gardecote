package com.gardecote.entities;

import javax.persistence.*;

/**
 * Created by Dell on 08/11/2016.
 */
@Entity
@Table(name="quantitesTypesConcession4", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries( {
        @NamedQuery( name="quantitesTypesConcession.countAll", query="SELECT COUNT(x) FROM quantitesTypesConcession x" )
} )
public class quantitesTypesConcession {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="idQTC", nullable=false)
    private Integer idQuantitesTypeConcession;
    private enumSegUsine segusine;
    private Long quantite;

    public quantitesTypesConcession(enumSegUsine segusine, Long quantite) {
        this.segusine = segusine;
        this.quantite = quantite;
    }

    public Integer getIdQuantitesTypeConcession() {
        return idQuantitesTypeConcession;
    }

    public void setIdQuantitesTypeConcession(Integer idQuantitesTypeConcession) {
        this.idQuantitesTypeConcession = idQuantitesTypeConcession;
    }

    public enumSegUsine getSegusine() {
        return segusine;
    }

    public void setSegusine(enumSegUsine segusine) {
        this.segusine = segusine;
    }

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }
}
