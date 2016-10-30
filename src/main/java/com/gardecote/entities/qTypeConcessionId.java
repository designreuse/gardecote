package com.gardecote.entities;

import java.io.Serializable;

/**
 * Created by Dell on 29/10/2016.
 */
public class qTypeConcessionId implements Serializable {
    private Integer qtypeconcession;

    public Integer getQtypeconcession() {
        return qtypeconcession;
    }

    public void setQtypeconcession(Integer qtypeconcession) {
        this.qtypeconcession = qtypeconcession;
    }

    public qTypeConcessionId(Integer qtypeconcession) {
        this.qtypeconcession = qtypeconcession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qTypeConcessionId)) return false;

        qTypeConcessionId that = (qTypeConcessionId) o;

        return qtypeconcession.equals(that.qtypeconcession);

    }

    @Override
    public int hashCode() {
        return qtypeconcession.hashCode();
    }
}
