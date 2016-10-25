package com.gardecote.entities;

import java.io.Serializable;
import javax.persistence.Id;
/**
 * Created by Dell on 23/10/2016.
 */
public class qSeq implements Serializable {
    @Id
    Long idqlistseq;
    Long debut;
    Long Fin;

    public Long getIdqlistseq() {
        return idqlistseq;
    }

    public void setIdqlistseq(Long idqlistseq) {
        this.idqlistseq = idqlistseq;
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
