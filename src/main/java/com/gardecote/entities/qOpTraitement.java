package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 25/10/2016.
 */
@Entity
@Table(name="qOpTraitement20", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries( {
        @NamedQuery( name="qOpTraitement.countAll", query="SELECT COUNT(x) FROM qOpTraitement x" )
} )
public class qOpTraitement implements Serializable {
    @Id
    String refAgrement;
    @Id
    Date dateTraitement;
    private qEspece esp;
    private Long qte;

    public qOpTraitement() {
    }

    @OneToOne
    private qPageTraitement pageTraitement;

    public qOpTraitement(qTraitement tr,qEspece esp, Long qte) {
        this.refAgrement=tr.getNumImm();
        this.dateTraitement=tr.getDepart();
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

    public String getRefAgrement() {
        return refAgrement;
    }

    public void setRefAgrement(String refAgrement) {
        this.refAgrement = refAgrement;
    }

    public Date getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(Date dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public qPageTraitement getPageTraitement() {
        return pageTraitement;
    }

    public void setPageTraitement(qPageTraitement pageTraitement) {
        this.pageTraitement = pageTraitement;
    }
}
