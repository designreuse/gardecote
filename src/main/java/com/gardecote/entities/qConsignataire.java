package com.gardecote.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dell on 09/10/2016.
 */

@Entity
@Table(name="qConsignataire33", schema="dbo", catalog="GCM4" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qConsignataire.countAll", query="SELECT COUNT(x) FROM qConsignataire x" )
} )
public class qConsignataire implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idconsignataire", nullable=false)
    private Integer  idconsignataire;

    @Column(name="nomconsignataire", nullable=false)
    private String  nomconsignataire;
    //mappedBy ="concessionaire" ,  ,targetEntity =qConcession.class,cascade = CascadeType.ALL

    @OneToMany(mappedBy ="qconsignataire",targetEntity =qConcession.class,cascade = CascadeType.PERSIST)
  //@JoinColumn(name="qpagedeb",insertable = false,updatable = false)
  //@JoinTable(name="LiaisonConcessionConcessionaire",joinColumns=@JoinColumn(name="consignataire_fk"),inverseJoinColumns = @JoinColumn(name="concession_fk"))
  //@JoinColumn(name = "idconcession")
    private List<qConcession> qconcession;

    public String getNomconsignataire() {
        return nomconsignataire;
    } public void setNomconsignataire(String nomconsignataire) {
        this.nomconsignataire = nomconsignataire;
    } public List<qConcession> getQconcession() {
        return qconcession;
    } public void setQconcession(List<qConcession> qconcession) {
        this.qconcession = qconcession;
    }

    public qConsignataire(String consignataire, List<qConcession> concession) {
        nomconsignataire = consignataire;
        this.qconcession = concession;
    }

    public qConsignataire() { super();
    }

    public qConsignataire(String consignataire) {
        nomconsignataire = consignataire;
    }


    public String getNomConsignataire() {
        return nomconsignataire;
    }

    public void setNomConsignataire(String nomConsignataire) {
        this.nomconsignataire = nomConsignataire;
    }

    public List<qConcession> getConcession() {
        return qconcession;
    }

    public void setConcession(List<qConcession> concession) {
        this.qconcession = concession;
    }
}
