package com.gardecote.entities;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * Created by Dell on 23/10/2016.
 */
@Entity
@DynamicUpdate
@DiscriminatorValue("DEBARQUEMENT")
public class qDebarquement extends qDoc implements Serializable {
    private static final long serialVersionUID = 1L;
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------
    private enumModePeche modeDocument;

    @ManyToMany(targetEntity = qEnginPecheDebar.class,cascade = CascadeType.ALL)
    @JoinTable(name = "qAssocDebarqEnginPecheBIS")
    private List<qEnginPecheDebar> Engins;

    @ManyToMany(targetEntity = qCategDeb.class,cascade = CascadeType.ALL)
    @JoinTable(name = "qAssocDebarqCategoriesBIS")
    private List<qCategDeb> categories;

    @ManyToMany(targetEntity = qTypeConcession.class)
    @JoinTable(name = "qAssocDebarqTypeConcessionBIS")
    private List<qTypeConcession> typesConcession;

    private enumTypeDebarquement typeDeb;

    @OneToMany(targetEntity=qPageDebarquement.class)
    @JoinTable(name = "qAssocDebarqPagesBIS")
        private List<qPageDebarquement> pages;
    @Column(name="totalCaptures", nullable=false, length=10)
    private float totalCapturs;



    public List<qEnginPecheDebar> getEngins() {
        return Engins;
    }

    public void setEngins(List<qEnginPecheDebar> engins) {
        Engins = engins;
    }

    public enumModePeche getModeDocument() {
        return modeDocument;
    }

    public void setModeDocument(enumModePeche modeDocument) {
        this.modeDocument = modeDocument;
    }

    public enumTypeDebarquement getTypeDeb() {
        return typeDeb;
    }

    public float getTotalCapturs() {
        return totalCapturs;
    }

    public void setTotalCapturs(float totalCapturs) {
        this.totalCapturs = totalCapturs;
    }

    public void setTypeDeb(enumTypeDebarquement typeDeb) {
        this.typeDeb = typeDeb;
    }

    public List<qCategDeb> getCategories() {
        return categories;
    }

    public void setCategories(List<qCategDeb> categories) {
        this.categories = categories;
    }

    public List<qPageDebarquement> getPages() {
        return pages;
    }

    public List<qTypeConcession> getTypesConcession() {
        return typesConcession;
    }

    public void setTypesConcession(List<qTypeConcession> typesConcession) {
        this.typesConcession = typesConcession;
    }

    public void setPages(List<qPageDebarquement> pages) {
        this.pages = pages;
    }

    public qDebarquement(enumTypeDoc enumtypedoc,  Date depart, Date retour,qSeq qseq,qNavireLegale qnavire,qUsine qusine,  List<qEnginPecheDebar> qEngins, enumTypeDebarquement typeDeb,qConcession qconcess,List<qCategDeb> categories, List<qPageDebarquement> pages,float totCap,enumModePeche modeDoc) {
        super(enumtypedoc, depart, retour,qseq, qnavire,qusine,qconcess);
        this.categories=categories;
        this.Engins=qEngins;
        this.typeDeb = typeDeb;
        this.pages = pages;
        this.totalCapturs=totCap;
        this.modeDocument=modeDoc;

    }



    public qDebarquement() {

    }


}
