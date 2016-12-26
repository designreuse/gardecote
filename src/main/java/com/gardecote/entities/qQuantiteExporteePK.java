package com.gardecote.entities;

import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Dell on 20/12/2016.
 */
public class qQuantiteExporteePK {

    String refAgrement;

    Date dateTraitement;

    private enumZonOrientation enumZonOrientationPeche;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qQuantiteExporteePK)) return false;

        qQuantiteExporteePK that = (qQuantiteExporteePK) o;

        if (!getRefAgrement().equals(that.getRefAgrement())) return false;
        if (!getDateTraitement().equals(that.getDateTraitement())) return false;
        return getEnumZonOrientationPeche() == that.getEnumZonOrientationPeche();

    }

    @Override
    public int hashCode() {
        int result = getRefAgrement().hashCode();
        result = 31 * result + getDateTraitement().hashCode();
        result = 31 * result + getEnumZonOrientationPeche().hashCode();
        return result;
    }

    public String getRefAgrement() {
        return refAgrement;
    }

    public void setRefAgrement(String refAgrement) {
        this.refAgrement = refAgrement;
    }

    public Date getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(Date dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public enumZonOrientation getEnumZonOrientationPeche() {
        return enumZonOrientationPeche;
    }

    public void setEnumZonOrientationPeche(enumZonOrientation enumZonOrientationPeche) {
        this.enumZonOrientationPeche = enumZonOrientationPeche;
    }
}
