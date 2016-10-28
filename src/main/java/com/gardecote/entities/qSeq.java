package com.gardecote.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Created by Dell on 23/10/2016.
 */
@Entity
@Table(name="qSeq", schema="dbo", catalog="DSPCM_DB" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qSeq.countAll", query="SELECT COUNT(x) FROM qSeq x" )
} )
@IdClass(qSeqPK.class)
public class qSeq implements Serializable {
    @Id
    String  prefix;
    @Id
    Long debut;

    Long Fin;
    @OneToOne
    private qDoc qdoc;

    public qSeq(String prefix, Long debut, Long fin) {
        this.prefix = prefix;
        this.debut = debut;
        Fin = fin;
    }

    public qSeq() {
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Long getDebut() {
        return debut;
    }

    public void setDebut(Long debut) {
        this.debut = debut;
    }

    public Long getFin() {
        return Fin;
    }

    public void setFin(Long fin) {
        Fin = fin;
    }
}
