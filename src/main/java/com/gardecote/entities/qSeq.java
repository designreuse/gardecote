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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qSeq)) return false;

        qSeq qSeq = (qSeq) o;

        if (!prefix.equals(qSeq.prefix)) return false;
        if (!debut.equals(qSeq.debut)) return false;
        if (Fin != null ? !Fin.equals(qSeq.Fin) : qSeq.Fin != null) return false;
        return !(qdoc != null ? !qdoc.equals(qSeq.qdoc) : qSeq.qdoc != null);

    }

    @Override
    public int hashCode() {
        int result = prefix.hashCode();
        result = 31 * result + debut.hashCode();
        result = 31 * result + (Fin != null ? Fin.hashCode() : 0);
        result = 31 * result + (qdoc != null ? qdoc.hashCode() : 0);
        return result;
    }

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
