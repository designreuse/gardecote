package com.gardecote.entities;

import java.io.Serializable;

/**
 * Created by Dell on 23/10/2016.
 */
public class qQuotasAuthPK  implements Serializable {

    qCategRessource qcategressource;

    qConcession qconcession;

    public qQuotasAuthPK(qCategRessource qcategressource, qConcession qconcession) {
        this.qcategressource = qcategressource;
        this.qconcession = qconcession;
    }

    public qQuotasAuthPK() {
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
}
