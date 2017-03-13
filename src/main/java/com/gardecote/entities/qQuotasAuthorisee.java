package com.gardecote.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Dell on 23/10/2016.
 */
@Entity
@Table(name="qQuotasAuthorisee", schema="dbo", catalog="GCM8" )
@IdClass(qQuotasAuthPK.class)
public class qQuotasAuthorisee implements Serializable {
    @Id
    qCategRessource qcategressource;
    @Id
    qConcession qconcession;

    Double qQuantiteAuthorisee;

    public qQuotasAuthorisee(qCategRessource qcategressource, qConcession qconcession, Double qQuantiteAuthorisee) {
        this.qcategressource = qcategressource;
        this.qconcession = qconcession;
        this.qQuantiteAuthorisee = qQuantiteAuthorisee;
    }

    public qQuotasAuthorisee() {
    }

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
