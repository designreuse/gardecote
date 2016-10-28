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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qQuotasAuthPK)) return false;

        qQuotasAuthPK that = (qQuotasAuthPK) o;

        if (!qcategressource.equals(that.qcategressource)) return false;
        return qconcession.equals(that.qconcession);

    }

    @Override
    public int hashCode() {
        int result = qcategressource.hashCode();
        result = 31 * result + qconcession.hashCode();
        return result;
    }
}
