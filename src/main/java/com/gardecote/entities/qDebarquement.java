package com.gardecote.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 23/10/2016.
 */

@Entity
@DiscriminatorValue("DEBARQUEMENT")

public class qDebarquement extends qDoc {
    private static final long serialVersionUID = 1L;
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------
    @Id
    @Column(name="Depart", nullable=false, length=10)
    private Date depart       ;

    @Column(name="Retour", nullable=false, length=10)
    private Date     retour       ;

    qConcession qconcessionconcernee;

    @OneToOne
    private qRegistreNavire    qnavire;

    private qCategRessource    qcategconcernee;

    private List<qPageDebarquement> pages;

    public qDebarquement(Date depart, Date retour, qConcession qconcessionconcernee, qRegistreNavire qnavire, qCategRessource qcategconcernee) {
        this.depart = depart;
        this.retour = retour;
        this.qconcessionconcernee = qconcessionconcernee;
        this.qnavire = qnavire;
        this.qcategconcernee = qcategconcernee;
    }

    public Date getDepart() {
        return depart;
    }

    public void setDepart(Date depart) {
        this.depart = depart;
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
}
