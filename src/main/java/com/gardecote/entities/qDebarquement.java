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
    @ManyToMany(targetEntity = qEnginPecheDebar.class,cascade = CascadeType.ALL)
    @JoinTable(name = "qAssocDebarqEnginPecheBIS")
    private List<qEnginPecheDebar> Engins;

    @ManyToMany(targetEntity = qCategDeb.class,cascade = CascadeType.ALL)
    @JoinTable(name = "qAssocDebarqCategoriesBIS")
    private List<qCategDeb> categories;

    private enumTypeDebarquement typeDeb;

    @OneToMany(targetEntity=qPageDebarquement.class)
    @JoinTable(name = "qAssocDebarqPagesBIS")
    private List<qPageDebarquement> pages;

    public List<qEnginPecheDebar> getEngins() {
        return Engins;
    }

    public void setEngins(List<qEnginPecheDebar> engins) {
        Engins = engins;
    }


    public enumTypeDebarquement getTypeDeb() {
        return typeDeb;
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

    public void setPages(List<qPageDebarquement> pages) {
        this.pages = pages;
    }

    public qDebarquement(enumTypeDoc enumtypedoc,  Date depart, Date retour,qSeq qseq,qNavire qnavire,qUsine qusine,  List<qEnginPecheDebar> qEngins, enumTypeDebarquement typeDeb,qConcession qconcess,List<qCategDeb> categories, List<qPageDebarquement> pages) {
        super(enumtypedoc, depart, retour,qseq, qnavire,qusine,qconcess);
        this.categories=categories;
        this.Engins=qEngins;
        this.typeDeb = typeDeb;
        this.pages = pages;
    }



    public qDebarquement() {

    }


}
