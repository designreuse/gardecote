package com.gardecote.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Dell on 06/03/2017.
 */
public class qBateauPK implements Serializable {

    private String     numimm;

    private enumLegalitePeche     legalite;

    public String getNumimm() {
        return numimm;
    }

    public void setNumimm(String numimm) {
        this.numimm = numimm;
    }

    public enumLegalitePeche getLegalite() {
        return legalite;
    }

    public void setLegalite(enumLegalitePeche legalite) {
        this.legalite = legalite;
    }

    public qBateauPK(String numimm, enumLegalitePeche legalite) {
        this.numimm = numimm;
        this.legalite = legalite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qBateauPK)) return false;

        qBateauPK qBateauPK = (qBateauPK) o;

        if (!getNumimm().equals(qBateauPK.getNumimm())) return false;
        return getLegalite() == qBateauPK.getLegalite();

    }

    @Override
    public int hashCode() {
        int result = getNumimm().hashCode();
        result = 31 * result + getLegalite().hashCode();
        return result;
    }

    public qBateauPK() {
    }
}
