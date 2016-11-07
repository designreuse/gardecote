package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 23/10/2016.
 */

@Entity
@Table(name="qDoc", schema="dbo", catalog="GCM1" )
@DiscriminatorValue("DEBARQUEMENT")

public class qDebarquement extends qDoc implements Serializable {
    private static final long serialVersionUID = 1L;
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------


    // la concession deja lu a travers de page
    @OneToOne
    private qConcession qconcessionDeb;


    @ManyToMany(targetEntity = qEnginPecheDeb.class,cascade = CascadeType.ALL)
    @JoinTable(name = "qAssocDebarqEnginPeche")
    private List<qEnginPecheDeb> Engins;

    // selected licence par l utilisateur
    // une zones liee a cette licence
    // un navire liee a cette licence
    // list des engins a remplir

    // list des zones deroulante liees a cette concession  a selectionner

    // list des licenses deroulante liees a cette concession  a selectionner
    @ManyToMany(targetEntity = qCategRessource.class,cascade=CascadeType.ALL)
    @JoinTable(name = "qAssocDebarqCategRessources")
   private List<qCategRessource>    qcategconcernees;

    private enumTypeDebarquement typeDeb;

    @OneToMany(targetEntity=qPageDebarquement.class,cascade = CascadeType.ALL)
    @JoinTable(name = "qAssocDebarqPages")
    private List<qPageDebarquement> pages;



    public List<qEnginPecheDeb> getEngins() {
        return Engins;
    }

    public void setEngins(List<qEnginPecheDeb> engins) {
        Engins = engins;
    }

    public qConcession getQconcessionDeb() {
        return qconcessionDeb;
    }

    public void setQconcessionDeb(qConcession qconcessionconcernee) {
        this.qconcessionDeb = qconcessionconcernee;
    }

    public enumTypeDebarquement getTypeDeb() {
        return typeDeb;
    }

    public void setTypeDeb(enumTypeDebarquement typeDeb) {
        this.typeDeb = typeDeb;
    }

    public List<qCategRessource> getQcategconcernees() {
        return qcategconcernees;
    }

    public void setQcategconcernees(List<qCategRessource> qcategconcernees) {
        this.qcategconcernees = qcategconcernees;
    }



    public List<qPageDebarquement> getPages() {
        return pages;
    }

    public void setPages(List<qPageDebarquement> pages) {
        this.pages = pages;
    }

    public qDebarquement(enumTypeDoc enumtypedoc,  Date depart, Date retour,qSeq qseq,qRegistreNavire qnavire, qConcession qconcessionconcernee, List<qEnginPecheDeb> qEngins, List<qCategRessource> qcategconcernees, enumTypeDebarquement typeDeb, List<qPageDebarquement> pages) {
        super(enumtypedoc, depart, retour,qseq, qnavire);
        this.qconcessionDeb = qconcessionconcernee;
        this.Engins=qEngins;
        this.qcategconcernees = qcategconcernees;
        this.typeDeb = typeDeb;
        this.pages = pages;
    }



    public qDebarquement() {

    }


}
