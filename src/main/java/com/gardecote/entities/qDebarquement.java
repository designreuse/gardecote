package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 23/10/2016.
 */

@Entity
@DiscriminatorValue("DEBARQUEMENT")

public class qDebarquement extends qDoc implements Serializable {
    private static final long serialVersionUID = 1L;
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------

    @Column(name="Retour", nullable=false, length=10)
    private Date     retour       ;

    private qConcession qconcessionconcernee;



    private qCategRessource    qcategconcernee;
    @OneToMany(mappedBy="qdebarquement", targetEntity=qPageDebarquement.class)
    private List<qPageDebarquement> pages;

    public qDebarquement(enumTypeDoc enumtypedoc, List<qSeq> qlistseq) {
        super(enumtypedoc, qlistseq);
    }





    public Date getRetour() {
        return retour;
    }

    public void setRetour(Date retour) {
        this.retour = retour;
    }

    public List<qPageDebarquement> getPages() {
        return pages;
    }

    public void setPages(List<qPageDebarquement> pages) {
        this.pages = pages;
    }

    public qDebarquement(Date retour, qConcession qconcessionconcernee, qCategRessource qcategconcernee, List<qPageDebarquement> pages) {
        this.retour = retour;
        this.qconcessionconcernee = qconcessionconcernee;
        this.qcategconcernee = qcategconcernee;
        this.pages = pages;
    }
}
