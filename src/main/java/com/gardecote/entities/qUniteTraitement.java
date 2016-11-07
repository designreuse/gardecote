package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dell on 25/10/2016.
 */
@Entity
@Table(name="qUniteTraitement", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries( {
        @NamedQuery( name="qUniteTraitement.countAll", query="SELECT COUNT(x) FROM qUniteTraitement x" )
} )
public class qUniteTraitement implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id_utrait", nullable=false)
    private Long idTraitement        ;
    private qEspece esp;
    private Long qte;

    public qUniteTraitement() {
    }

    @OneToMany
    private List<qPageTraitement> pagesTraitement;

    public qUniteTraitement(qEspece esp, Long qte) {
        this.esp = esp;
        this.qte = qte;
    }

    public qEspece getEsp() {
        return esp;
    }

    public void setEsp(qEspece esp) {
        this.esp = esp;
    }

    public Long getQte() {
        return qte;
    }

    public void setQte(Long qte) {
        this.qte = qte;
    }
}
