package com.gardecote.entities;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 25/10/2016.
 */
@Entity
@Table(name="qOpTraitement21", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries( {
        @NamedQuery( name="qOpTraitement.countAll", query="SELECT COUNT(x) FROM qOpTraitement x" )
} )
public class qOpTraitement implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long IdOpTraitement;
  //  @OneToOne(targetEntity = qEspece.class,cascade = {CascadeType.PERSIST,CascadeType.REFRESH })
   // @NotFound(action = NotFoundAction.IGNORE)
  @OneToOne(targetEntity = qEspece.class,cascade = CascadeType.ALL)
    private qEspece esp;
    private Long qte;

    public qOpTraitement() {
    }

    @OneToOne
    private qPageTraitement pageTraitement;

    public qOpTraitement(qPageTraitement tr,qEspece esp, Long qte) {
        this.pageTraitement=tr;

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

    public Long getIdOpTraitement() {
        return IdOpTraitement;
    }

    public void setIdOpTraitement(Long idOpTraitement) {
        IdOpTraitement = idOpTraitement;
    }

    public qPageTraitement getPageTraitement() {
        return pageTraitement;
    }

    public void setPageTraitement(qPageTraitement pageTraitement) {
        this.pageTraitement = pageTraitement;
    }
}
