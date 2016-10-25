package com.gardecote.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by Dell on 23/10/2016.
 */
@IdClass(qSeqPK.class)
public class qSeq implements Serializable {
    @Id
    String  prefix;
    @Id
    Long debut;

    Long Fin;

    public qSeq(String prefix, Long debut, Long fin) {
        this.prefix = prefix;
        this.debut = debut;
        Fin = fin;
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
