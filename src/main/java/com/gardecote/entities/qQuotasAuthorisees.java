package com.gardecote.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Dell on 23/10/2016.
 */
@IdClass(qQuotasAuthPK.class)
public class qQuotasAuthorisees {
    @Id
    qCategRessource qcategressource;
    @Id
    qConcession qconcession;

    Double qQuantiteAuthorisee;

    public qCategRessource getQcategressource() {
        return qcategressource;
    }

    public void setQcategressource(qCategRessource qcategressource) {
        this.qcategressource = qcategressource;
    }

    public qConcession getQconcession() {
        return qconcession;
    }

    public void setQconcession(qConcession qconcession) {
        this.qconcession = qconcession;
    }

    public Double getqQuantiteAuthorisee() {
        return qQuantiteAuthorisee;
    }

    public void setqQuantiteAuthorisee(Double qQuantiteAuthorisee) {
        this.qQuantiteAuthorisee = qQuantiteAuthorisee;
    }
}
