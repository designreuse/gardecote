package com.gardecote.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
/**
 * Created by Dell on 25/10/2016.
 */
@Entity

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_CONCESSION", discriminatorType=DiscriminatorType.STRING, length=20)
@Table(name="qTypeConcession", schema="dbo", catalog="GCM11")
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qTypeConcession.countAll", query="SELECT COUNT(x) FROM qTypeConcession x" )
} )
public class qTypeConcession implements Serializable,Comparable<qTypeConcession> {
    @Id
    @Column(name = "qtypeconcessionpk",unique=true, nullable = false)
     private Integer qtypeconcessionpk;

    @NotNull
    @Column(name="prefixModel", nullable=false, length=5)
    private String prefixNum;

    private enumTypeDoc typeDoc;

    @OneToOne
    @NotNull
    private qPrefix    prefix;
    private String  designation;

    public qTypeConcession() {
        super();
    }

    public qTypeConcession(Integer qtypeconcessionpk, qPrefix prefix, String designation) {
        this.qtypeconcessionpk = qtypeconcessionpk;
        this.prefixNum = prefix.getPrefix();
        this.typeDoc = prefix.getTypeDoc();
        this.prefix = prefix;
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getQtypeconcessionpk() {
        return qtypeconcessionpk;
    }

    public void setQtypeconcessionpk(Integer libelle) {
        this.qtypeconcessionpk = libelle;
    }

    public String getPrefixNum() {
        return prefixNum;
    }

    public void setPrefixNum(String prefixNum) {
        this.prefixNum = prefixNum;
    }

    public enumTypeDoc getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(enumTypeDoc typeDoc) {
        this.typeDoc = typeDoc;
    }

    public qPrefix getPrefix() {
        return prefix;
    }

    public void setPrefix(qPrefix prefix) {
        this.prefix = prefix;
    }

    @Override
    public int compareTo(qTypeConcession o) {
        return (this.getQtypeconcessionpk()- o.getQtypeconcessionpk());
    }

    @Override
    public String toString() {
        return designation;
    }
}
